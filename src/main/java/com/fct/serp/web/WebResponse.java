package com.fct.serp.web;

import com.fct.serp.model.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebResponse<T> {

    private boolean success;
    private String message;
    private T obj;
    private PageInfo pageInfo;

    public static WebResponse<String> error(String message) {
        return new WebResponse<>(false, message, null, null);
    }

    public static <T> WebResponse<T> success(String msg, T user) {
        return new WebResponse<>(true, msg, user, null);
    }
}
