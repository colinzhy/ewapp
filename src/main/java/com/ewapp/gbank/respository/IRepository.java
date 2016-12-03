package com.ewapp.gbank.respository;

import java.io.Serializable;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ewapp.gbank.model.BaseModel;


@NoRepositoryBean
public interface IRepository<M extends BaseModel>
    extends PagingAndSortingRepository<M, Serializable>, QueryDslPredicateExecutor<M> {

}
