package com.ronansoares.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ronansoares.workshopmongo.domain.User;
import com.ronansoares.workshopmongo.dto.UserDTO;
import com.ronansoares.workshopmongo.services.UserService;

@RestController                   // Para falar que essa classe seja um Recurso Rest
@RequestMapping(value="/users")   // Para definir o caminho do end point.
public class UserResource {
	
	@Autowired
	private UserService service;  //Controlador acessa service e service o repository. Injeção
	
	// Método para retornar uma lista de usuários (busca todos)
	@RequestMapping(method = RequestMethod.GET)       // Para avisar que esse método é um end point para esse caminho
	public ResponseEntity<List<UserDTO>> findAll(){      // Resp.Ent. para retornar resposta http completa com cabeçalhos erros etc
		List<User> list = service.findAll();           // lista busca os usuário do BD e adiciona na lista
		// Converte a list do tipo User para UserDTO. Depois volta o stream para uma list.
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);        // Retorna resposta http com sucesso
	}
	
	// Método para retornar um usuário por id
		@RequestMapping(value="/{id}", method = RequestMethod.GET)       // Para avisar que esse método é um end point para esse caminho
		public ResponseEntity<UserDTO> findById(@PathVariable String id){      // Resp.Ent. para retornar resposta http completa com cabeçalhos erros etc
			User obj = service.findById(id);						
			return ResponseEntity.ok().body(new UserDTO(obj));        // Retorna resposta http com sucesso
	}
	
	// Método para adicionar um usuário
	@RequestMapping(method=RequestMethod.POST)
 	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		User obj = service.fromDTO(objDto);  // Converte DTO para User
		obj = service.insert(obj);           // Insere no DB
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
 	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	// Método para atualizar um usuário
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> udate(@RequestBody UserDTO objDto, @PathVariable String id) {
		User obj = service.fromDTO(objDto);  // Converte DTO para User
		obj.setId(id);                       // Para garantir que o objeto terá o id da requisição
		obj = service.Update(obj);           // Insere no DB
		return ResponseEntity.noContent().build();
	}
}
