package com.ronansoares.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ronansoares.workshopmongo.domain.User;
import com.ronansoares.workshopmongo.repository.UserRepository;

// Classe auxiliar para municiar o BD com os objetos. Implements Comman....
@Configuration      // Anotação para o Spring entender q é uma classe de configuração.
public class Instantiation implements CommandLineRunner{

	
	// Injeta o UserRepository
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();   // Limpar a coleção no BD
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));		
	}
}
