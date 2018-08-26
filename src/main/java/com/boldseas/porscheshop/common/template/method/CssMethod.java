package com.boldseas.porscheshop.common.template.method;

import com.boldseas.porscheshop.common.config.CdnConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * css("goods/style.css",["version"])
 *
 * @author wushan
 * @version 16/8/21
 */
@Component
public class CssMethod  extends AbstractSourceMethod {

    private final CdnConfig cdnConfig;

    @Autowired
    public CssMethod(CdnConfig cdnConfig) {
        this.cdnConfig = cdnConfig;
    }

    @Override
    protected String getRootPath() {
        return cdnConfig.getPath().getStyle();
    }
}
