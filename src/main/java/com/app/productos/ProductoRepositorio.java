package com.app.productos;

import org.springframework.data.repository.CrudRepository;

public interface ProductoRepositorio extends CrudRepository<Producto, Integer>{
	
	public Producto findByNombre(String nombre);

}//en src/test/java ahí vamos a crear las clases para las pruebas unitarias
