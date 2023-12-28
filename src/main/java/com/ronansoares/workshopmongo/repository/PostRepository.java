package com.ronansoares.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ronansoares.workshopmongo.domain.Post;

import java.util.Date;
import java.util.List;


@Repository   // Com essa anotação e o extends, o objeto UserRepository pode fazer as operações básicas do User
public interface PostRepository extends MongoRepository<Post, String>{

	Post getReferenceById(String id);
	
	// Método para buscar posts personalizados
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	// Método para buscar posts pelo titulo
	List<Post> findByTitleContainingIgnoreCase(String text); // IgCase para ignorar maiusculas
	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
