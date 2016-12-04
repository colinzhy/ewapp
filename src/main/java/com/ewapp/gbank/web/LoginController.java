package com.ewapp.gbank.web;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ewapp.gbank.web.utils.MessageTranslator;

@Controller
public class LoginController {

  @Inject
  private MessageTranslator messageTranslator;

  /**
   * redirect to login page.
   * 
   * @return login page view
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView login() {
    ModelAndView modelAndView = new ModelAndView("login");
    return modelAndView;
  }

  /**
   * redirect to error page.
   * 
   * @return error page view
   */
  @RequestMapping(value = "/login-error", method = RequestMethod.GET)
  public ModelAndView loginError(HttpSession session) {
    ModelAndView modelAndView = new ModelAndView("login");
    Object authException = session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    if (authException != null) {
      String msg = ((Exception) authException).getMessage();
      modelAndView.addObject("loginError", messageTranslator.getMessageWithDefault(msg, msg));
    }
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
