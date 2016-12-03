package com.ewapp.gbank.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends ABaseDTO {

  private String userName;

  private String email;

  private String password;

  private String name;

  @Override
  public String getId() {
    return userName;
  }
}
