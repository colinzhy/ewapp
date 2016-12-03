package com.ewapp.gbank.service.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ewapp.gbank.model.User;
import com.ewapp.gbank.web.dto.UserDto;

@Component
public class UserMapper extends ABaseMapper<UserDto, User> {

  @Override
  public UserDto modelToDto(User user) {
    UserDto userDto = new UserDto();
    BeanUtils.copyProperties(user, userDto);
    return userDto;
  }

  @Override
  public User dtoToModel(UserDto userDto) {
    User user = new User();
    BeanUtils.copyProperties(userDto, user);
    return user;
  }

}
