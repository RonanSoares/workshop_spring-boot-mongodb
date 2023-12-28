package com.ronansoares.workshopmongo.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ronansoares.workshopmongo.domain.Post;
import com.ronansoares.workshopmongo.domain.User;
import com.ronansoares.workshopmongo.dto.UserDTO;
import com.ronansoares.workshopmongo.resources.util.URL;
import com.ronansoares.workshopmongo.services.PostService;
import com.ronansoares.workshopmongo.services.UserService;

@RestController                   // Para falar que essa classe seja um Recurso Rest
@RequestMapping(value="/posts")   // Para definir o caminho do end point.
public class PostResource {
	
	@Autowired
	private PostService service;  //Controlador acessa service e service o repository. Injeção
	
		
	// Método para retornar um usuário por id
		@RequestMapping(value="/{id}", method = RequestMethod.GET)       // Para avisar que esse método é um end point para esse caminho
		public ResponseEntity<Post> findById(@PathVariable String id){      // Resp.Ent. para retornar resposta http completa com cabeçalhos erros etc
			Post obj = service.findById(id);						
			return ResponseEntity.ok().body(obj);        // Retorna resposta http com sucesso
	}
		
		@RequestMapping(value="/titlesearch", method=RequestMethod.GET)
	 	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
			text = URL.decodeParam(text);
			List<Post> list = service.findByTitle(text);
			return ResponseEntity.ok().body(list);
		}

		@RequestMapping(value="/fullsearch", method=RequestMethod.GET)
	 	public ResponseEntity<List<Post>> fullSearch(
	 			@RequestParam(value="text", defaultValue="") String text,
	 			@RequestParam(value="minDate", defaultValue="") String minDate,
	 			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
			text = URL.decodeParam(text);
			Date min = URL.convertDate(minDate, new Date(0L));
			Date max = URL.convertDate(maxDate, new Date());
			List<Post> list = service.fullSearch(text, min, max);
			return ResponseEntity.ok().body(list);
		}  
}
