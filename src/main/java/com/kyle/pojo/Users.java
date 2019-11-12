package com.kyle.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    private Integer bsid;

    private String uname;

    private String uphone;

    private String uemail;

    private Integer ustatus;

    private Integer uvip;

    private Integer ugender;

    private String upassword;

    private String upic;

    private String uface;

    private Integer uticket;

    private BigDecimal ucoin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date uvipdate;

    private static final long serialVersionUID = 1L;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getBsid() {
        return bsid;
    }

    public void setBsid(Integer bsid) {
        this.bsid = bsid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone == null ? null : uphone.trim();
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail == null ? null : uemail.trim();
    }

    public Integer getUstatus() {
        return ustatus;
    }

    public void setUstatus(Integer ustatus) {
        this.ustatus = ustatus;
    }

    public Integer getUvip() {
        return uvip;
    }

    public void setUvip(Integer uvip) {
        this.uvip = uvip;
    }

    public Integer getUgender() {
        return ugender;
    }

    public void setUgender(Integer ugender) {
        this.ugender = ugender;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword == null ? null : upassword.trim();
    }

    public String getUpic() {
        return upic;
    }

    public void setUpic(String upic) {
        this.upic = upic == null ? null : upic.trim();
    }

    public String getUface() {
        return uface;
    }

    public void setUface(String uface) {
        this.uface = uface == null ? null : uface.trim();
    }

    public Integer getUticket() {
        return uticket;
    }

    public void setUticket(Integer uticket) {
        this.uticket = uticket;
    }

    public BigDecimal getUcoin() {
        return ucoin;
    }

    public void setUcoin(BigDecimal ucoin) {
        this.ucoin = ucoin;
    }
}