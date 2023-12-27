package com.ronansoares.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ronansoares.workshopmongo.domain.Post;
import com.ronansoares.workshopmongo.domain.User;
import com.ronansoares.workshopmongo.repository.PostRepository;
import com.ronansoares.workshopmongo.repository.UserRepository;

// Classe auxiliar para municiar o BD com os objetos. Implements Comman....
@Configuration      // Anotação para o Spring entender q é uma classe de configuração.
public class Instantiation implements CommandLineRunner{

	
	// Injeta o UserRepository
	@Autowired
	private UserRepository userRepository;
	
	// Injeta o PostRepository
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));   // sdf recebe o horário de Greenwhich
		
		userRepository.deleteAll();   // Limpar a coleção de usuários no BD
		postRepository.deleteAll();   // Limpar a coleção de posts no BD
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		Post post1 = new Post(null, sdf.parse("21/03/2023"), "Partiu Viagem", "Vou viajar para São Paulo. Abraços!!", maria);
		Post post2 = new Post(null, sdf.parse("23/03/2023"), "Bom dia", "Acordeu Feliz hoje!!", maria);
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));	
		postRepository.saveAll(Arrays.asList(post1, post2));
	}
}
