package com.kyle.pojo;

import java.io.Serializable;

public class Chapter implements Serializable {
    private Integer chid;

    private Integer bid;

    private String chnane;

    private Integer number;

    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getChid() {
        return chid;
    }

    public void setChid(Integer chid) {
        this.chid = chid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getChnane() {
        return chnane;
    }

    public void setChnane(String chnane) {
        this.chnane = chnane == null ? null : chnane.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}