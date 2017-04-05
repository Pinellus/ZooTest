package com.pinellus.zootest;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.pinellus.zootest.domain.Domanda;
import com.pinellus.zootest.repository.DomandeRepository;

import static java.lang.System.exit;

@SpringBootApplication
public class ZooTestApplication {
	
	@Autowired
    DataSource dataSource;

    @Autowired
    DomandeRepository domandeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ZooTestApplication.class, args);
		
	}
	
	/*
	@Transactional(readOnly = true)
    @Override
    public void run(String... args) throws Exception {

        System.out.println("DATASOURCE = " + dataSource);

        System.out.println("\n2.findById(String email)...");
        for (Domande domanda : domandeRepository.findById(200)) {
            System.out.println(domanda);
        }

        System.out.println("Done!");

        //exit(0);
    }
    */
}
