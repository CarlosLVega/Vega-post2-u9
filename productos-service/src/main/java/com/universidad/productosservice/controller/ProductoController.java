package com.universidad.productosservice.controller;

import com.universidad.productosservice.domain.Producto;
import com.universidad.productosservice.service.ProductoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	private final ProductoService productoService;

	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@Valid @RequestBody CrearProductoRequest request) {
		return productoService.crear(request.nombre(), request.precio(), request.stock());
	}

	@GetMapping
	public List<Producto> listarTodos() {
		return productoService.listarTodos();
	}

	@GetMapping("/{id}")
	public Producto buscarPorId(@PathVariable Long id) {
		return productoService.buscarPorId(id);
	}

	@PatchMapping("/{id}/stock")
	public Producto actualizarStock(@PathVariable Long id, @Valid @RequestBody ActualizarStockRequest request) {
		return productoService.actualizarStock(id, request.stock());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		productoService.eliminar(id);
	}

	public record CrearProductoRequest(
			@NotBlank String nombre,
			@NotNull @Positive Double precio,
			@NotNull @PositiveOrZero Integer stock) {
	}

	public record ActualizarStockRequest(@NotNull @PositiveOrZero Integer stock) {
	}
}
