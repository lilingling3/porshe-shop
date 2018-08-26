package com.boldseas.porscheshop.interceptor.limit;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fei.ye
 * @version 2018/5/9.
 */
@Data
@NoArgsConstructor
public class Limit implements Serializable {

    public Limit(String ip, String path) {
        this.ip = ip;
        this.path = path;
    }

    /**
     * 访问的次数
     */
    private Integer times = 1;

    /**
     * ip
     */
    private String ip;

    /**
     * 访问的路径或者方法的全名称
     */
    private String path;
    /**
     * 过期秒
     */
    private Integer seconds;

    public String getKey() {
        return String.format("limit:%s-%s", ip, path);
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>(4);
        map.put("times", times.toString());
        map.put("ip", ip);
        map.put("path", path);
        map.put("seconds", seconds.toString());
        return map;
    }
}
