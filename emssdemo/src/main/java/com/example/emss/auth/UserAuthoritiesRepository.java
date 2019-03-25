package com.example.emss.auth;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthoritiesRepository extends CrudRepository<UserAuthorities, Long> {

  @Query ("SELECT u FROM user_authorities u WHERE u.userName = ?1")
  UserAuthorities findByUserName (
      String userName);

}
