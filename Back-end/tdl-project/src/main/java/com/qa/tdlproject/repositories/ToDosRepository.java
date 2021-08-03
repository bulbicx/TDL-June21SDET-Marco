package com.qa.tdlproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.tdlproject.models.ToDos;

public interface ToDosRepository extends JpaRepository<ToDos, Long> {

	
}
