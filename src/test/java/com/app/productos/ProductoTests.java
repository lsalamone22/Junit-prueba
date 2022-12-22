package com.app.productos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest//esta anotación va a indicar que vamos hacer una prueba unitaria de una aentidad
@AutoConfigureTestDatabase(replace = Replace.NONE)//esta notación sirve para indicar que  me haga las aoperaciones, ya no a la bdd h2 (bdd en memoria),
//sino que las haga en la bdd real

//vamos a añadir la anotación de quien se ejecuta primero y quien despues
@TestMethodOrder(OrderAnnotation.class)//para pasarle un orden de que metodo se va a ejecutar primero
public class ProductoTests {
	
	@Autowired
	private ProductoRepositorio repositorio;
	
	@Test //el test es para indicar que el metodo de abajo es un emtod que vamos a probar, hacer una prueba
	@Rollback(false)//para que me guarde los datos, y no de forma temporal
	@Order(1)
	public void testGuardarProducto() {
		Producto producto = new Producto("Laptop HP", 2000);//vamos a probar este metodo
		Producto productoGuardado = repositorio.save(producto);//aqui en producto guardado estoy guardadno lo que guarde en repositorio
		
		//ahora hacemos una confirmación
		assertNotNull(productoGuardado);
		//yo voy a confirmar esta prueba unitaria siempre y cuando ese valor que le pasé por parentesisi
		//no sea nullo (no este vacio)
		//esto se haria en memoria, pero como le estoy clocando el @AutoConfigureTestDatabase se guarda en la bdd

	}
	
	@Test
	@Order(2)
	public void testBuscarProductoPorNombre() {
		String nombre = "HUAWEI Y9";//el nombre a buscar
		Producto producto = repositorio.findByNombre(nombre);//va a buscar ese nombre
		
		assertThat(producto.getNombre()).isEqualTo(nombre);
		//voy a confirmar si este product.getnombre es igual a Televisor Samsung HD me saldra bien
	}
	
	@Test
	@Order(3)
	public void testBuscarProductoPorNombreNoExistente() {
		String nombre = "Laptop HP";//el nombre a buscar
		Producto producto = repositorio.findByNombre(nombre);//va a buscar ese nombre
		
		assertNull(producto);
		//te va acomprobar si un valor noe xiste
		//loq ue haces es ---voy a confirmar si este valor de aqui es nulo
	}
	
	@Test //el test es para indicar que el metodo de abajo es un emtod que vamos a probar, hacer una prueba
	@Rollback(false)//para que me guarde los datos, y no de forma temporal
	@Order(4)
	public void testActualizarProducto() {
		String nombreProducto = "HUAWEI Y9";//el nuevo valor
		Producto producto = new Producto(nombreProducto, 1000);//aqui van a ir los nuevos datos osea lo voy a actualizar
		producto.setId(1);//id a actualizar será 1
		//si no encuntra el ID entonces lo va a insertar como un nuevo valor
		
		repositorio.save(producto);//guardar cambios
		Producto productoActualizado = repositorio.findByNombre(nombreProducto);
		
		assertThat(productoActualizado.getNombre()).isEqualTo(nombreProducto);
	}
	
	@Test
	@Order(5)
	public void testListarProductos() {
		List<Producto> productos = (List<Producto>) repositorio.findAll();
		
		for(Producto producto : productos) {
			System.out.println(producto);
		}
		assertThat(productos).size().isGreaterThan(0);
		//este metodo se va a confirmar si es que el tamaño de la lista de estos productos es mayor a 0
	
		//ahora quiero que me imprima los datos de que estan en la bdd
		//lo colocamos en el entity producto -...tostring
	}
	
	@Test 
	@Rollback(false)
	@Order(6)
	public void testEliminarProducto() {
		Integer id =2;
		boolean esExistenteAntesDeEliminar = repositorio.findById(id).isPresent();//is present es que si existe
		//yo voy a buscar el id y comoe s verdad(osea existe en la bdd) el boolean me retornará un true
		repositorio.deleteById(id);
		
		boolean noExistenteDespuesDeEliminar = repositorio.findById(id).isPresent();
		//una vez que lo elimine voy a comprobar y me dará false
		
		assertTrue(esExistenteAntesDeEliminar);
		//voy a confirmar si el valor de arriba me da un true
		assertFalse(noExistenteDespuesDeEliminar);
		//y tamb si el valor de abajo me da un false
		
	}
}