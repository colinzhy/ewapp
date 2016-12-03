package com.ewapp.gbank.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ewapp.gbank.model.BaseModel;
import com.ewapp.gbank.respository.IRepository;
import com.ewapp.gbank.service.mapper.ABaseMapper;
import com.ewapp.gbank.web.dto.ABaseDTO;

/**
 * Common service for CRUD operation.
 * 
 * @param <T> DTO extends from {@link ABaseDTO}
 * @param <M> Data model extends from {@link BaseModel}
 */
public abstract class ACrudService<T extends ABaseDTO, M extends BaseModel> implements ICrudService<T> {

  private static final Logger log = LoggerFactory.getLogger(ACrudService.class);

  @Inject
  private IRepository<M> repos;

  @Inject
  private ABaseMapper<T, M> mapper;

  @Override
  public T create(T obj) {
    log.debug("Request to create [{}]", obj);
    M result = repos.save(mapper.dtoToModel(obj));
    return mapper.modelToDto(result);
  }

  @Override
  public T update(T obj) {
    log.debug("Request to update [{}]", obj);
    M result = repos.save(mapper.dtoToModel(obj));
    return mapper.modelToDto(result);
  }

  @Override
  public T findById(Serializable id) {
    log.debug("Request to find by Id [{}]", id);
    M result = repos.findOne(id);
    return mapper.modelToDto(result);
  }

  @Override
  public Page<T> findPage(Pageable pageable, String filter) {
    log.debug("Request to find data by page [{}], and filter [{}]", pageable, filter);
    Page<M> results = repos.findAll(pageable);
    return results.map(result -> mapper.modelToDto(result));
  }

  @Override
  public void deleteById(Serializable id) {
    log.debug("Request to delete data by Id [{}]", id);
    M model = repos.findOne(id);
    if (model == null) {
      // TODO: raise an error
    }
    repos.delete(id);
  }

  @Override
  public T disableById(Serializable id) {
    log.debug("Request to disable data by Id [{}]", id);
    M model = repos.findOne(id);
    if (model == null) {
      // TODO: raise an error
    }
    model.setEnabled(false);
    M result = repos.save(model);
    return mapper.modelToDto(result);
  }

  @Override
  public long count() {
    return repos.count();
  }

}
