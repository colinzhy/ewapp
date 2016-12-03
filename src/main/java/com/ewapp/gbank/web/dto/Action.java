package com.ewapp.gbank.web.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * This class represent any action that can be made in System. It is supposed to be generated at runtime and sent back
 * to the client to represent an action that a user can make on an object.
 * 
 * This object is not saved in database.
 * 
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, of = {"base", "action", "objectId"})
public class Action implements Serializable {


  private static final long serialVersionUID = 486455554149408289L;

  /**
   * Represents the base of an action.
   */
  private String base;

  /**
   * Represents the action itself. For example: save / update / view
   */
  private String action;

  /**
   * Represents the class associated with the action
   */
  private String style;

  /**
   * Represents the icon associated with the action
   */
  private String icon;

  /**
   * Text displayed when hovering on the link
   */
  private String altText;

  /**
   * Represents the id of the object on which the action will be performed
   */
  private Serializable objectId;

  /**
   * Constructor
   * 
   * @param base
   * @param action
   * @param style
   * @param icon
   * @param altText
   */
  public Action(String base, String action, String style, String icon, String altText) {
    this(base, action, style, icon, altText, null);
  }

}
