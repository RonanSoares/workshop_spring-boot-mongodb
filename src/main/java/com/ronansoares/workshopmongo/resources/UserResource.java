package com.ronansoares.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ronansoares.workshopmongo.domain.User;

@RestController                   // Para falar que essa classe seja um Recurso Rest
@RequestMapping(value="/users") // Para definir o caminho do end point.
public class UserResource {
	
	// Método para retornar uma lista de usuários (busca todos)
	@RequestMapping(method = RequestMethod.GET)       // Para avisar que esse método é um end point para esse caminho
	public ResponseEntity<List<User>> findAll(){            // Resp.Ent. para retornar resposta http completa com cabeçalhos erros etc
		User maria = new User("1", "Maria Brown", "maria@gmail.com"); // Adiciona usuarios
		User alex = new User("2", "Alex Green", "alex@gmail.com");
		
		List<User> list = new ArrayList<>();            // Instancia a lista
		list.addAll(Arrays.asList(maria, alex));        // Adiciona os usuários na lista
		return ResponseEntity.ok().body(list);          // Retorna resposta http com sucesso
	}
}
