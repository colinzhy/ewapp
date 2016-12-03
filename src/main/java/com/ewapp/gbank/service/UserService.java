package com.ewapp.gbank.service;

import org.springframework.stereotype.Service;

import com.ewapp.gbank.model.User;
import com.ewapp.gbank.web.dto.UserDto;

@Service
public class UserService extends ACrudService<UserDto, User> {

}
