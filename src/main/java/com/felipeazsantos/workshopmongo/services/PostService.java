package com.felipeazsantos.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipeazsantos.workshopmongo.domain.Post;
import com.felipeazsantos.workshopmongo.repository.PostRepository;
import com.felipeazsantos.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public List<Post> findAll() {
		return repository.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	public List<Post> findByTitle(String text) {
		return repository.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date dateMin, Date dateMax) {
		dateMax = new Date(dateMax.getTime() + 24 * 60 * 60 * 1000);
		
		return repository.fullSearch(text, dateMin, dateMax);
		
	}
	
}
