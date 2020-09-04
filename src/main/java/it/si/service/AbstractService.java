package it.si.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class AbstractService<R extends JpaRepository<E, Integer>, E> {
	
	@Autowired
	protected R repository;
	
	public List<E> findAll(){
		return repository.findAll();
	}
	
	public E findById(int id) {
		return repository.getOne(id);
	}
	@Transactional
	public void save(E entity) {
		repository.save(entity);
	}
	@Transactional
	public void delete(int id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
		}
	}
}
