package com.ewapp.gbank.service.mapper;

import java.util.Collection;
import java.util.stream.Collectors;

import com.ewapp.gbank.model.BaseModel;
import com.ewapp.gbank.web.dto.ABaseDTO;

/**
 * Mapper to convert model and dto.
 * 
 * @param <T> DTO
 * @param <M> BaseModel
 */
public abstract class ABaseMapper<T extends ABaseDTO, M extends BaseModel> {

  /**
   * Convert model to dto
   * 
   * @param model model to be converted
   * @return converted dto
   */
  public abstract T modelToDto(M model);

  /**
   * Convert dto to model
   * 
   * @param dto dto to be converted
   * @return converted model
   */
  public abstract M dtoToModel(T dto);

  /**
   * Convert model list to dto list
   * 
   * @param models model list to be converted
   * @return dto list
   */
  public Collection<T> modelsToDtos(Collection<M> models) {
    if (models == null) {
      return null;
    }
    return models.stream().map(model -> modelToDto(model)).collect(Collectors.toList());
  }

  /**
   * Convert dto list to model list
   * 
   * @param dtos dto list to be converted
   * @return model list
   */
  public Collection<M> dtosToModels(Collection<T> dtos) {
    if (dtos == null) {
      return null;
    }
    return dtos.stream().map(dto -> dtoToModel(dto)).collect(Collectors.toList());
  }
}
