package com.boldseas.porscheshop.common.utils.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.apache.poi.util.IOUtils.*;

/**
 * @author fei.ye
 * @version 2018/6/1.
 */
@Slf4j
public class PdfReplace {

    private PdfReader reader;
    private Map<String, ReplaceRegion> replaceRegionMap = new HashMap<>();
    private Map<String, ReplaceContent> replaceTextMap = new HashMap<>();
    private ByteArrayOutputStream outputStream;

    public InputStream getResult() throws IOException {
        reader.close();
        outputStream.close();
        return new ByteArrayInputStream(this.outputStream.toByteArray());
    }
    /**
     * 初始化
     *
     * @param inputStream
     * @throws IOException
     * @throws DocumentException
     */
    public PdfReplace(InputStream inputStream) throws IOException {
        this.reader = new PdfReader(toByteArray(inputStream));
    }

    /**
     * 添加替换内容及字体
     *
     * @param name
     * @param text
     * @param font
     */
    public void replaceText(String name, String text, Font font) {
        this.replaceTextMap.put(name, new ReplaceContent(text, font));
    }

    /**
     * 绝对位置替换
     *
     * @param x
     * @param y
     * @param w
     * @param h
     * @param text
     */
    public void replaceText(float x, float y, float w, float h, String text, Font font) {
        //用文本作为别名
        ReplaceRegion region = new ReplaceRegion(text);
        region.setH(h);
        region.setW(w);
        region.setX(x);
        region.setY(y);
        addReplaceRegion(region);
        this.replaceText(text, text, font);
    }

    /**
     * 添加替换区域
     *
     * @param replaceRegion
     */
    private void addReplaceRegion(ReplaceRegion replaceRegion) {
        this.replaceRegionMap.put(replaceRegion.getAliasName(), replaceRegion);
    }

    /**
     * 替换文本
     *
     * @return
     * @throws IOException
     */
    public void replaceProcess(Integer pageNumber) throws IOException {
        if(Objects.isNull(this.outputStream)){
            this.outputStream = new ByteArrayOutputStream();
        }
        try {
            PdfStamper stamper = new PdfStamper(reader, outputStream);
            PdfContentByte canvas = stamper.getOverContent(pageNumber);
            replace(canvas);
            stamper.close();
        } catch (DocumentException e) {
            log.error("replace process document error!", e);
        } finally {
            this.replaceRegionMap = new HashMap<>(8);
            this.replaceTextMap = new HashMap<>(8);
            this.reader = new PdfReader(toByteArray(getResult()));
        }

    }

    /**
     * 替换
     *
     * @param canvas
     * @throws IOException
     */
    private void replace(PdfContentByte canvas)  {
        replaceRegionMap.entrySet().forEach(entry -> {
            ReplaceRegion value = entry.getValue();
            canvas.saveState();
            canvas.setColorFill(BaseColor.WHITE);
            canvas.rectangle(value.getX(), value.getY(), value.getW(), value.getH());
            canvas.fill();
            canvas.restoreState();
            canvas.beginText();
            ReplaceContent content = replaceTextMap.get(value.getAliasName());
            canvas.setFontAndSize(content.getFont().getBaseFont(), content.getFont().getSize());
            /*修正背景与文本的相对位置*/
            canvas.setTextMatrix(value.getX(), value.getY() + 2);
            canvas.showText(content.getText());
            canvas.endText();
        });
    }

}
