package com.ronansoares.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ronansoares.workshopmongo.domain.Post;
import java.util.List;


@Repository   // Com essa anotação e o extends, o objeto UserRepository pode fazer as operações básicas do User
public interface PostRepository extends MongoRepository<Post, String>{

	Post getReferenceById(String id);
	
	// Método para buscar posts pelo titulo
	List<Post> findByTitleContainingIgnoreCase(String text); // IgCase para ignorar maiusculas
}
