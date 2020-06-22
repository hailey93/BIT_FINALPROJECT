package com.bit.house.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@Alias("sellerVO")
public class SellerVO {
    private String sellerId;
    private String sellerPw;
    private String sellerName;
    private String sellerRes;
    private String sellerUrl;
    private String sellerManager;
    private String managerTel;
    private String managerEmail;
    private String sellerAddr;
    private boolean permit;
    private Date applyDate;
    private Date permitDate;


}
