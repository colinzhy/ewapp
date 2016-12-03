package com.ewapp.gbank.web.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

import lombok.Data;

@Data
public abstract class ABaseDTO {

  private boolean enabled = true;

  private String createdBy;

  private Date createdDate;

  private String lastModifiedBy;

  private Date lastModifiedDate;

  private LinkedHashSet<Action> actions = new LinkedHashSet<>();

  public void addActions(Collection<Action> actionCollection) {
    actions.addAll(actionCollection);
  }

  public abstract Serializable getId();

}
