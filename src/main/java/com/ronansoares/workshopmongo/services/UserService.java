package com.ronansoares.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronansoares.workshopmongo.domain.User;
import com.ronansoares.workshopmongo.repository.UserRepository;

@Service   //Avisa ao Spring que é uma classe de serviço para injeção em outras classes
public class UserService {
	
	//Método responsável para retornar todos os usuários do BD
	@Autowired          // Instancia automaticamente um objeto UserRepository na classe serviço. Injeção automática
	private UserRepository repo;  
	
	public List<User> findAll(){
		return repo.findAll();   // Retorna todos os usuários.
	}
}
