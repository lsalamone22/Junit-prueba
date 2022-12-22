package com.app.productos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // esto para que sea autoincrementable

	private Integer Id;

	@Column(length = 64)
	private String nombre;

	private float precio;// float (numeros reales de forma aproximada)

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Producto() {
		super();
	}

	public Producto(Integer id, String nombre, float precio) {
		super();
		Id = id;
		this.nombre = nombre;
		this.precio = precio;
	}

	public Producto(String nombre, float precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Producto [Id=" + Id + ", nombre=" + nombre + ", precio=" + precio + "]";
	}
	
	

}
