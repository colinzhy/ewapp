package com.ewapp.gbank.web;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ewapp.gbank.model.User;
import com.ewapp.gbank.web.dto.Action;
import com.ewapp.gbank.web.dto.UserDto;

@Controller
@RequestMapping("/user")
public class UserController extends ACrudController<UserDto> {

  @Override
  protected void addExtraFormAttributeForCreatePage(ModelAndView modelAndView) {
    modelAndView.addObject("object", new User());
  }

  @Override
  protected void addExtraFormAttributeForEditPage(ModelAndView modelAndView) {
    // TODO Auto-generated method stub

  }

  @Override
  protected void addExtraFormAttributeForViewPage(ModelAndView modelAndView) {
    // TODO Auto-generated method stub

  }

  @Override
  protected String getBase() {
    return "user";
  }

  @Override
  protected Collection<Action> getActions(HttpServletRequest request) {
    Set<Action> actions = new LinkedHashSet<>();
    // if (request.isUserInRole("USER_EDIT")) {
    actions.add(createEditAction(getBase()));
    // }
    // if (request.isUserInRole("USER_DELETE")) {
    actions.add(createDeleteAction(getBase()));
    // }
    return actions;
  }

}
