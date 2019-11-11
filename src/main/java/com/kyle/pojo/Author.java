package com.kyle.pojo;

import java.io.Serializable;

public class Author implements Serializable {
    private Integer aid;

    private String aname;

    private String aphone;

    private String aemail;

    private Integer astatus;

    private String apic;

    private Long awallet;

    private static final long serialVersionUID = 1L;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname == null ? null : aname.trim();
    }

    public String getAphone() {
        return aphone;
    }

    public void setAphone(String aphone) {
        this.aphone = aphone == null ? null : aphone.trim();
    }

    public String getAemail() {
        return aemail;
    }

    public void setAemail(String aemail) {
        this.aemail = aemail == null ? null : aemail.trim();
    }

    public Integer getAstatus() {
        return astatus;
    }

    public void setAstatus(Integer astatus) {
        this.astatus = astatus;
    }

    public String getApic() {
        return apic;
    }

    public void setApic(String apic) {
        this.apic = apic == null ? null : apic.trim();
    }

    public Long getAwallet() {
        return awallet;
    }

    public void setAwallet(Long awallet) {
        this.awallet = awallet;
    }
}