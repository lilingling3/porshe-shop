package com.boldseas.porscheshop.dtos;

/**
 * @author Chen Jingxuan
 * @version 2018/5/23
 */
public enum Gender {
    /**
     * 男
     */
    MALE("先生"),

    /**
     * 女
     */
    FEMALE("女士");

    Gender(String value) {
        this.value = value;
    }

    private String value;

    public String getValue(){
        return this.value;
    }
}
