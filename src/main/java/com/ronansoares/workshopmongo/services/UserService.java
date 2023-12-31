package com.ronansoares.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronansoares.workshopmongo.domain.User;
import com.ronansoares.workshopmongo.dto.UserDTO;
import com.ronansoares.workshopmongo.repository.UserRepository;
import com.ronansoares.workshopmongo.services.exception.ObjectNotFoundException;

@Service   //Avisa ao Spring que é uma classe de serviço para injeção em outras classes
public class UserService {
	
	//Método responsável para retornar todos os usuários do BD
	@Autowired          // Instancia automaticamente um objeto UserRepository na classe serviço. Injeção automática
	private UserRepository repo;  
	
	public List<User> findAll(){
		return repo.findAll();   // Retorna todos os usuários.
	}
	
	public User findById(String id) {
		User user = repo.getReferenceById(id);
		if(user == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return user;
	} 
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id){
		findById(id);        // Faz a busca primeiro para ver se existe o id.
		repo.deleteById(id);
		
	}
	
	public User Update(User obj) {
		User newObj = repo.getReferenceById(obj.getId());  //Busca o objeto no BD
		updateData(newObj, obj);  // Chama o metodo udateData. Copia o obj e passa p o newObj
		return repo.save(newObj); // Salva o novo newObj.
	}
	
	// Método para att o usuario
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());    // Copia o obj name para o newObj name.
		newObj.setEmail(obj.getEmail());
		// Id não copia.
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}

}
