package com.boldseas.porscheshop.ui;

import com.alibaba.fastjson.JSON;
import com.boldseas.porscheshop.common.utils.Md5Util;
import com.boldseas.porscheshop.common.utils.pdf.PdfReplace;
import com.boldseas.porscheshop.dtos.OrderDTO;
import com.boldseas.porscheshop.dtos.UserDTO;
import com.boldseas.porscheshop.interceptor.UserProvider;
import com.boldseas.porscheshop.order.OrderService;
import com.google.common.base.Strings;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.boldseas.porscheshop.common.constant.CommonConstants.FONT_PATH_PRE;
import static com.boldseas.porscheshop.common.constant.CommonConstants.MD5_SIGN;

/**
 * 账户
 *
 * @author yujie.li
 * @version 2018/5/11
 */
@Slf4j
@Controller
@RequestMapping("/account")
public class AccountController {
    private String templateUrl = "pdf/intent-letter-min.pdf";

    @Value("${font.path}")
    private String fontPath;

    @Autowired
    private UserProvider userProvider;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ModelMapper mapper;


    @GetMapping
    public String account(ModelMap modelMap) {
        UserDTO currentUser = userProvider.getCurrentUser();
        List<OrderDTO> userOrdersCompleteInfo = orderService.getUserOrdersCompleteInfo(currentUser.getId());
        modelMap.addAttribute("user", JSON.toJSONString(currentUser));
        modelMap.addAttribute("orders", JSON.toJSONString(userOrdersCompleteInfo));
        return "account/index";
    }

    /**
     * 购车意向书
     *
     * @param orderSn
     * @param modelMap
     * @return
     */
    @GetMapping("/intentionList")
    public String intentionList(String orderSn, ModelMap modelMap) {
        OrderDTO order = orderService.getOrderCompleteInfo(orderSn);
        modelMap.addAttribute("order", order);
        return "account/intention-list";
    }

    /**
     * 购车意向书下载
     *
     * @return
     * @throws IOException
     */
    @GetMapping("/pdf-download")
    public ResponseEntity download(String sn) throws IOException {
        InputStream inputStream = generatePdf(sn);
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/pdf");
        headers.add("Content-Disposition", String.format("attachment; filename=%s.pdf",sn));
        return new ResponseEntity<>(IOUtils.toByteArray(inputStream), headers, HttpStatus.OK);
    }

    /**
     * 购车意向书下载(无登录状态)
     * @return
     * @throws IOException
     */
    @GetMapping("/download/{sn}/{sign}")
    public ResponseEntity downloadWithoutLogin(@PathVariable String sn,@PathVariable String sign) throws IOException {
        String result = Md5Util.generateMd5(sn, MD5_SIGN);
        if (result.equals(sign)) {
            return download(sn);
        }
        return null;
    }

    private InputStream generatePdf(String orderSn) {
        Font simheiFont =getFont("simhei.ttf",10f);
        Font porscheFont=getFont("PorscheNextTT-Regular.ttf",10f);

        OrderDTO order = orderService.getOrderCompleteInfo(orderSn);

        try {
            PdfReplace pdfReplace = new PdfReplace(getPdfTemplate(templateUrl));
            pdfReplace.replaceText(360,530,68,20,"支付时间：",simheiFont);
            pdfReplace.replaceText(428,530,98,20,order.getPaymentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),porscheFont);
            pdfReplace.replaceText(360,550,68,20,"购车人身份证：",simheiFont);
            pdfReplace.replaceText(428,550,98,20,order.getIdCard(),porscheFont);
            pdfReplace.replaceText(360,570,68,20,"购车人姓名：",simheiFont);
            pdfReplace.replaceText(428,570,98,20,order.getConsignee(),simheiFont);
            pdfReplace.replaceText(360,590,68,20,"订单编号：",simheiFont);
            pdfReplace.replaceText(428,590,98,20,order.getOrderSn(),porscheFont);
            pdfReplace.replaceProcess(1);

            int x = getXpoint(order.getDealerName().length(),order.getCompanyCn().length());
            pdfReplace.replaceText(x, 260, 150, 20, order.getDealerName(), simheiFont);
            pdfReplace.replaceText(x, 240, 150, 20, order.getCompanyCn(), simheiFont);
            pdfReplace.replaceProcess(3);

            InputStream inputStream =  pdfReplace.getResult();
            return inputStream;
        } catch (IOException e) {
            log.error(orderSn + " pdf-download error:" + e);
        }
        return null;
    }

    private int getXpoint(int length1, int length2) {
        int max = length1 > length2 ? length1 : length2;
        return 560 - 10 * max;
    }

    private InputStream getPdfTemplate(String resourceUrl) {

        Resource resource = new ClassPathResource(resourceUrl);
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            log.error("read pdf template error!", e);
        }
        return null;
    }

    /**
     * 资源路径
     * @param name
     * @return
     * @throws IOException
     */
    private String getResourcesUrl(String name) {
        Resource resource = new ClassPathResource(FONT_PATH_PRE + name);
        try {
            return resource.getURL().getPath();
        } catch (IOException e) {
            log.error("getResourcesUrl error!", e);
        }
        return null;
    }

    private String getFontPath(String name){
        if(!Strings.isNullOrEmpty(fontPath)){
            log.info("font-path:" + fontPath + name);
            return fontPath + name;
        }
        return getResourcesUrl(name);
    }

    private Font getFont(String name,Float size){
        Font font = FontFactory.getFont(getFontPath(name), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        font.setSize(size);
        log.info("font-base:" + font.getBaseFont());
        return font;
    }

}


