package com.ewapp.gbank.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@MappedSuperclass
@EqualsAndHashCode
@ToString
public abstract class BaseModel {

  @Column(name = "enabled")
  private boolean enabled = true;

  @Version
  @Column(name = "version")
  private Long version;

  @CreatedBy
  @Column(name = "created_by")
  private String createdBy;

  @CreatedDate
  @Column(name = "created_date")
  private Date createdDate;

  @LastModifiedBy
  @Column(name = "last_modified_by")
  private String lastModifiedBy;

  @LastModifiedDate
  @Column(name = "last_modified_date")
  private Date lastModifiedDate;

}
