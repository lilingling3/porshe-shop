package com.boldseas.porscheshop.common.template.method;

import com.boldseas.porscheshop.common.config.CdnConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * js("home/js.js",["version"])
 *
 * @author wushan
 * @version 16/8/21
 */
@Component
public class JsMethod extends AbstractSourceMethod {

    private final CdnConfig cdnConfig;

    @Autowired
    public JsMethod(CdnConfig cdnConfig) {
        this.cdnConfig = cdnConfig;
    }

    @Override
    protected String getRootPath() {
        return cdnConfig.getPath().getScripts();
    }
}
