
package com.example.mvvmdemo.model.product;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {

    private String code;
    private List<Content> content = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

}
