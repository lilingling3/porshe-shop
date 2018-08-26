package com.boldseas.porscheshop.common.utils.pdf;

import com.itextpdf.text.Font;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author fei.ye
 * @version 2018/6/1.
 */
@Data
@AllArgsConstructor
public class ReplaceContent {
    private String text;
    private Font font;
}
