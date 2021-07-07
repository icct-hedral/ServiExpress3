package com.empresa.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.empresa.demo.service.ProductoServiceImpl;

@SpringBootApplication
public class ServiExpress3Application implements CommandLineRunner {

	//llamamos al productoserviceimpl, para usar dos metodos
	@Autowired
	@Qualifier("productoServiceImpl")
	private ProductoServiceImpl productoServiceImpl;
	
	public static void main(String[] args) {
		SpringApplication.run(ServiExpress3Application.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		
		productoServiceImpl.deleteAll();
		productoServiceImpl.init();
		
	}

}
