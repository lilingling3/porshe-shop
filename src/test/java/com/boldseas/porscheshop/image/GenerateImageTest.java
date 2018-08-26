package com.boldseas.porscheshop.image;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


/**
 * @author fei.ye
 * @version 2018/5/28.
 */

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GenerateImageTest {

    @Test
    public void generateImageTest() {
//        BufferedImage bufferedImage = graphicsGeneration("/Users/yefei/Pictures/Saved Pictures/bg.jpg");
//        File out = new File("/Users/yefei/Pictures/Saved Pictures/tttt.jpg");
//        writeJPEGBytes(bufferedImage, out);
    }

    /**
     * 画图
     * <p>
     * <p>参考文档：</p>
     * <p>https://www.cnblogs.com/azhqiang/p/7677844.html</p>
     *
     * @param templateImageUrl
     */
    private BufferedImage graphicsGeneration(String templateImageUrl) {
        BufferedImage templateImageBuffer = null;
        try {
            templateImageBuffer = ImageIO.read(new File(templateImageUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Font blackFont = new Font("黑体", Font.BOLD, 24);
        Font porscheFont = getPorscheFont();


        Graphics2D customerWords = templateImageBuffer.createGraphics();
        customerWords.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        customerWords.setFont(blackFont);
        customerWords.setColor(Color.black);
        customerWords.drawString("我是黑体：保时捷（北京）汽车销售有限公司", 1900, 50);
        customerWords.drawString("我是黑体：北京长安保时捷中心", 1900, 80);

        Graphics2D customerWords2 = templateImageBuffer.createGraphics();
        customerWords.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        customerWords2.setFont(porscheFont);
        customerWords2.setColor(Color.black);
        customerWords2.drawString("this font is porsche font: W34d35354dggTT", 1900, 110);
        customerWords2.drawString("this font is porsche font: W34d35354dggTT", 1900, 140);

        return templateImageBuffer;

    }

    /**
     * 将原图压缩生成jpeg格式的数据
     *
     * @param source
     * @return
     */
    public void writeJPEGBytes(BufferedImage source, File out) {
        writeBytes(source, "JPEG", out);
    }

    /**
     * * 将{@link BufferedImage}生成formatName指定格式的图像数据
     *
     * @param bufferedImage
     * @param formatName    图像格式名，图像格式名错误则抛出异常
     * @param bufferedImage
     * @return
     */
    private void writeBytes(BufferedImage bufferedImage, String formatName, File out) {
        try {
            ImageIO.write(bufferedImage, formatName, out);
        } catch (IOException e) {

        }
    }

    private Font getPorscheFont() {
        String porscheFont = "/Users/yefei/Documents/code-lib/boldseas/porsche-shop/src/main/resources/static/css/common/font/PorscheNextTT-Regular.ttf";
        Font font = null;
        try (FileInputStream fileInputStream = new FileInputStream(new File(porscheFont))) {
            font = Font.createFont(Font.TRUETYPE_FONT, fileInputStream).deriveFont(Font.BOLD).deriveFont(32.0f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        } finally {
            return font;
        }
    }
}
