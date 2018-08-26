package com.boldseas.porscheshop.dtos;

import lombok.Data;

/**
 * PMP 账户信息
 * @author feng
 */
@Data
public class AccountDTO {
  private Integer id;
  private Integer dealerId;
  private String dealerCode;
  private String dealerGroupName;
  private String loginName;
  private String nameCn;
  private String firstName;
  private String lastName;
  private String nameEn;
  private String title;
  private String avatarImageUrl;
  private String gender;
  private String mobile;
  private String tel;
  private String email;
  private String address;
  private String status;
}
