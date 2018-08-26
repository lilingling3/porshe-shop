package com.boldseas.porscheshop.common.template.method;

import com.boldseas.porscheshop.common.config.CdnConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ${image("goods/123.png",["version"])}
 *
 * @author wushan
 * @version 16/8/20
 */
@SuppressWarnings("ALL")
@Component
public class ImageMethod extends AbstractSourceMethod {

    private final CdnConfig cdnConfig;

    @Autowired
    public ImageMethod(CdnConfig cdnConfig) {
        this.cdnConfig = cdnConfig;
    }

    @Override
    protected String getRootPath() {
        return cdnConfig.getPath().getImage();
    }
}
