package com.boldseas.porscheshop.common.utils;

import lombok.Data;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author feng
 */
@Data
public class ExcelHelper {

    private static String hyperlinkFlag ="https://";

    /**
     * 获取列名
     * @param obj
     * @return
     */
    public static List<String> getColumns(Object obj){
        Field[] fields = obj.getClass().getDeclaredFields();
        List<String> columns= new ArrayList<>();
        for (Field field : fields) {
            columns.add(field.getAnnotation(ExcelColumnName.class).value());
        }
        return columns;
    }

    /**
     * 获取对象的值
     * @param obj
     * @return
     */
    public static List<String> getValue(Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> values = new ArrayList<>();
        Field[] field = obj.getClass().getDeclaredFields();
        for (int j = 0; j < field.length; j++) {
            String name = field[j].getName();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            Method m = obj.getClass().getMethod("get" + name);
            String value = (String) m.invoke(obj);
            values.add(value);
        }
        return values;
    }

    /**
     * 获取Excel文件输出流
     */
    public static ByteArrayOutputStream getOutStream(List<Object> data)
            throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet1");
        List<String> columns = getColumns(data.get(0));

        CellStyle hyperStyle = getHyperStyle(workbook);

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns.get(i));
        }
        int rowNum = 1;
        for (Object obj : data) {
            Row row = sheet.createRow(rowNum++);
            List<String> values = getValue(obj);
            for (int i = 0; i < values.size(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(values.get(i));
                setHyperlink(hyperStyle,cell,workbook);
            }
        }
        for (int i = 0; i < columns.size(); i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        bos.close();
        return bos;
    }

    /**
     * 获取加密的Excel输出流
     * @param password
     * @return
     * @throws Exception
     */
    public ByteArrayOutputStream getOutStreamEncrypt(List<Object> data,String password) throws Exception {
        ByteArrayOutputStream bos = getOutStream(data);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        POIFSFileSystem fs = new POIFSFileSystem();
        EncryptionInfo info = new EncryptionInfo(EncryptionMode.standard);
        Encryptor enc = info.getEncryptor();
        enc.confirmPassword(password);
        OutputStream os = enc.getDataStream(fs);

        OPCPackage opc = OPCPackage.open(bis);
        opc.save(os);
        opc.close();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        fs.writeFilesystem(outputStream);
        outputStream.close();
        return outputStream;
    }

    /**
     * 超链接样式
     * @param workbook
     * @return
     */
    private static CellStyle getHyperStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setUnderline(Font.U_DOUBLE);
        font.setColor(IndexedColors.BLUE.getIndex());
        style.setFont(font);
        return style;
    }

    /**
     * 设置超链接
     * @param hyperStyle
     * @param cell
     * @param workbook
     */
    private static void setHyperlink(CellStyle hyperStyle, Cell cell, Workbook workbook) {
        if (cell.getStringCellValue().contains(hyperlinkFlag)) {
            Hyperlink hyperlink = workbook.getCreationHelper().createHyperlink(HyperlinkType.URL);
            hyperlink.setAddress(cell.getStringCellValue());
            cell.setHyperlink(hyperlink);
            cell.setCellStyle(hyperStyle);
        }
    }
}
