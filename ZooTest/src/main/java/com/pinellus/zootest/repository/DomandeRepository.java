package com.pinellus.zootest.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pinellus.zootest.domain.Domanda;

public interface DomandeRepository extends CrudRepository<Domanda, Long> {

    Domanda findById(int id);
    
    List<Domanda> findAll();
    
	// custom query example and return a stream
    @Query("select c from Domanda c where c.giusta = :giusta")
    Stream<Domanda> findByEmailReturnStream(@Param("giusta") String giusta);

}
