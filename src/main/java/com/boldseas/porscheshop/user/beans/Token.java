package com.boldseas.porscheshop.user.beans;

import com.boldseas.porscheshop.dtos.CurrentUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Chen Jingxuan
 * @version 2018/5/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    /**
     * auth_token
     */
    private String authToken;
    /**
     * 登录用户的信息
     */
    private CurrentUserDTO currentUserDTO = new CurrentUserDTO();
    /**
     * token的过期时间
     */
    private long expire;


    public Map<String, String> getTokenEntries() {
        Map<String, String> tokenMap = new HashMap<>(3);
        tokenMap.put("authToken", this.authToken);
        tokenMap.put("userId", String.valueOf(this.currentUserDTO.getUserId()));
        tokenMap.put("phone", this.getCurrentUserDTO().getPhone());
        return tokenMap;
    }

    public Token(Map<String, String> tokenEntries) {
        this.authToken = tokenEntries.get("authToken");
        this.getCurrentUserDTO().setUserId(Long.parseLong(tokenEntries.get("userId")));
        this.getCurrentUserDTO().setPhone(tokenEntries.get("phone"));
    }
}
