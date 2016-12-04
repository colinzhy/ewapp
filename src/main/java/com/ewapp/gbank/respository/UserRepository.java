package com.ewapp.gbank.respository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ewapp.gbank.model.User;

@Repository
public interface UserRepository extends IRepository<User> {

  @Query("from User where userName=:criteria or email=:criteria")
  User findByUsernameOrEmail(@Param("criteria") String criteria);
}
