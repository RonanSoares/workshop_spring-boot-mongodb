package com.ronansoares.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
