package com.ronansoares.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ronansoares.workshopmongo.domain.User;

@Repository   // Com essa anotação e o extends, o objeto UserRepository pode fazer as operações básicas do User
public interface UserRepository extends MongoRepository<User, String>{	

}
