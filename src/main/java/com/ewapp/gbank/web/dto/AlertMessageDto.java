package com.ewapp.gbank.web.dto;

/**
 *
 * The class defines the type of AlertMessageDto and the description of message of AlertMessageDto.
 */
public class AlertMessageDto {

  /**
   * the type of message, {@link AlertMessageType}.
   */
  private String type;

  /**
   * the message to be present.
   */
  private String message;

  /**
   * create a AlertMessageDto.
   * 
   * @param type type of message
   * @param message message to be present
   */
  private AlertMessageDto(String type, String message) {
    this.type = type;
    this.message = message;
  }

  /**
   * create a info type AlertMessageDto.
   * 
   * @param message message to be present
   * @return info type AlertMessageDto
   */
  public static AlertMessageDto info(String message) {
    return new AlertMessageDto(AlertMessageType.info.name(), message);
  }

  /**
   * create a success type AlertMessageDto.
   * 
   * @param message message to be present
   * @return success type AlertMessageDto
   */
  public static AlertMessageDto success(String message) {
    return new AlertMessageDto(AlertMessageType.success.name(), message);
  }

  /**
   * create a warning type AlertMessageDto.
   * 
   * @param message message to be present
   * @return warning type AlertMessageDto
   */
  public static AlertMessageDto warning(String message) {
    return new AlertMessageDto(AlertMessageType.warning.name(), message);
  }

  /**
   * create a error type AlertMessageDto.
   * 
   * @param message message to be present
   * @return error type AlertMessageDto
   */
  public static AlertMessageDto error(String message) {
    return new AlertMessageDto(AlertMessageType.error.name(), message);
  }

  public String getType() {
    return type;
  }

  public void setType(String alertMessageType) {
    this.type = alertMessageType;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public enum AlertMessageType {
    success, warning, info, error;
  }

}
