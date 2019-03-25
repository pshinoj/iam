package com.example.emss.auth;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetailsImpl, Long> {

  @Query ("SELECT u FROM security_users u WHERE u.userName = ?1")
  UserDetailsImpl findByUserName (
      String userName);

}
