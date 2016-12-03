package com.ewapp.gbank.service;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ewapp.gbank.web.dto.ABaseDTO;

public interface ICrudService<T extends ABaseDTO> {
  /**
   * Create object
   * 
   * @param obj object to be created
   * @return created object
   */
  public T create(T obj);

  /**
   * Update object
   * 
   * @param obj object to be updated
   * @return updated object
   */
  public T update(T obj);

  /**
   * Find object by id
   * 
   * @param id id of the object
   * @return retrieved object
   */
  public T findById(Serializable id);

  /**
   * Find objects by filter by page
   * 
   * @param pageable page parameter, such as page size, current page number, etc.
   * @param filter filter to filter some data
   * @return objects by page
   */
  public Page<T> findPage(Pageable pageable, String filter);

  /**
   * delete object by it's id
   * 
   * @param id id of the object to be deleted
   */
  public void deleteById(Serializable id);

  /**
   * disable object by it's id
   * 
   * @param id id of the object to be disabled
   * @return disabled object
   */
  public T disableById(Serializable id);

  /**
   * number of objects
   * 
   * @return number of objects
   */
  public long count();
}
