package com.universidad.productosservice.service;

import com.universidad.productosservice.domain.Producto;

import java.util.List;

public interface ProductoService {

	Producto crear(String nombre, Double precio, Integer stock);

	List<Producto> listarTodos();

	Producto buscarPorId(Long id);

	Producto actualizarStock(Long id, Integer nuevoStock);

	void eliminar(Long id);
}
