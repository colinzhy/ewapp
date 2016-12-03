package com.ewapp.gbank;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.ewapp.gbank.GBankApplication;
import com.ewapp.gbank.model.User;
import com.ewapp.gbank.respository.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = GBankApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  public void findByName() throws Exception {
    User user = new User();
    user.setEmail("colin@126.com");
    user.setPassword("123456");
    user.setUserName("colin");
    userRepository.save(user);
    User createdUser = userRepository.findOne("colin");
    Assert.assertEquals(user, createdUser);
  }

}
