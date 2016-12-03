package com.ewapp.gbank.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

  /**
   * redirect to login page.
   * 
   * @return login page view
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView login() {
    ModelAndView modelAndView = new ModelAndView("user/list");
    return modelAndView;
  }

  /**
   * redirect to error page.
   * 
   * @return error page view
   */
  @RequestMapping(value = "/login-error", method = RequestMethod.GET)
  public ModelAndView loginError() {
    ModelAndView modelAndView = new ModelAndView("login");
    modelAndView.addObject("loginError", true);
    return modelAndView;
  }

  /**
   * logout redirect to login page.
   * 
   * @return login page view
   */
  @RequestMapping(value = "/logged-out", method = RequestMethod.GET)
  public ModelAndView logout() {
    ModelAndView modelAndView = new ModelAndView("login");
    modelAndView.addObject("logout", true);
    return modelAndView;
  }

}
