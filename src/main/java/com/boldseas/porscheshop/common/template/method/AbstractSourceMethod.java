package com.boldseas.porscheshop.common.template.method;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author wushan
 * @version 16/8/21
 */
public abstract class AbstractSourceMethod implements TemplateMethodModelEx {

    /**
     * 资源的根目录配置
     * @return
     */
    protected abstract String getRootPath();

    @Override
    public Object exec(List list) throws TemplateModelException {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String v = list.size() > 1 ? String.valueOf(list.get(1)) : format.format(new Date());
        return String.format("%s/%s?v=%s", getRootPath(), list.get(0), v);
    }

}
