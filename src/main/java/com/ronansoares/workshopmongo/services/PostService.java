package com.ronansoares.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronansoares.workshopmongo.domain.Post;
import com.ronansoares.workshopmongo.domain.User;
import com.ronansoares.workshopmongo.dto.UserDTO;
import com.ronansoares.workshopmongo.repository.PostRepository;
import com.ronansoares.workshopmongo.repository.UserRepository;
import com.ronansoares.workshopmongo.services.exception.ObjectNotFoundException;

@Service   //Avisa ao Spring que é uma classe de serviço para injeção em outras classes
public class PostService {
	
	//Método responsável para retornar todos os posts de usuários do BD
	@Autowired          // Instancia automaticamente um objeto UserRepository na classe serviço. Injeção automática
	private PostRepository repo; 
	
	public Post findById(String id) {
		Post user = repo.getReferenceById(id);
		if(user == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return user;
	} 
	
	public List<Post> findByTitle(String text){
		return repo.findByTitleContainingIgnoreCase(text);
	}
	
}