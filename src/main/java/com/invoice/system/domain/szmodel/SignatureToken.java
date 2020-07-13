package com.invoice.system.domain.szmodel;

import java.io.Serializable;

/**
 * @Desoription:秘钥验证字段实体类
 * @Auther:panyong
 * @create:2020/6/29 14:25
 */
public class SignatureToken{

    private Integer time;
    private String token;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
