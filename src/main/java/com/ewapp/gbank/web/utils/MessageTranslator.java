package com.ewapp.gbank.web.utils;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class MessageTranslator {

  @Inject
  private MessageSource messageSource;

  private MessageSourceAccessor messageSourceAccessor;

  /**
   * Initialization of Controllers.
   */
  @PostConstruct
  private void init() {
    messageSourceAccessor = new MessageSourceAccessor(messageSource);
  }

  /**
   * Get message for given code and parameters.
   * 
   * @param code message label
   * @param parameters parameters set into the message
   * @return translated message
   */
  public String getMessage(String code, String... parameters) {
    return messageSourceAccessor.getMessage(code, parameters);
  }

  public String getMessageWithDefault(String code, String defaultMessage) {
    return messageSourceAccessor.getMessage(code, defaultMessage);
  }

}
