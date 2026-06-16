package com.smartlogix.inventario.service;

import com.smartlogix.inventario.entity.Producto;
import com.smartlogix.inventario.repository.ProductoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto crearProducto(Long id, String nombre, String categoria, Integer stock) {
        Producto p = new Producto();
        p.setId(id);
        p.setNombre(nombre);
        p.setDescripcion("Descripcion prueba");
        p.setPrecio(100.0);
        p.setStock(stock);
        p.setCategoria(categoria);
        return p;
    }

    @Test
    @DisplayName("CP-001: Debe guardar producto correctamente")
    void debeGuardarProducto() {
        Producto nuevo = crearProducto(null, "Laptop", "Electronica", 10);
        Producto guardado = crearProducto(1L, "Laptop", "Electronica", 10);
        when(productoRepository.save(any(Producto.class))).thenReturn(guardado);

        Producto resultado = productoService.guardar(nuevo);

        assertNotNull(resultado.getId());
        assertEquals("Laptop", resultado.getNombre());
        assertEquals("Electronica", resultado.getCategoria());
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    @DisplayName("CP-002: Debe listar todos los productos")
    void debeListarTodos() {
        List<Producto> lista = Arrays.asList(
            crearProducto(1L, "Laptop", "Electronica", 10),
            crearProducto(2L, "Mouse", "Accesorios", 50)
        );
        when(productoRepository.findAll()).thenReturn(lista);

        List<Producto> resultado = productoService.listarTodos();

        assertEquals(2, resultado.size());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("CP-003: Debe buscar producto por ID existente")
    void debeBuscarPorIdExistente() {
        Producto producto = crearProducto(1L, "Laptop", "Electronica", 10);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = productoService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Laptop", resultado.get().getNombre());
        assertEquals("Electronica", resultado.get().getCategoria());
        verify(productoRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("CP-024: Debe retornar empty si producto no existe")
    void debeRetornarEmptyProductoNoExiste() {
        when(productoRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Producto> resultado = productoService.buscarPorId(999L);

        assertFalse(resultado.isPresent());
        verify(productoRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("CP-004: Debe actualizar producto correctamente")
    void debeActualizarProducto() {
        Producto actualizado = crearProducto(null, "Laptop Pro", "Electronica", 8);
        Producto resultadoGuardado = crearProducto(1L, "Laptop Pro", "Electronica", 8);
        when(productoRepository.save(any(Producto.class))).thenReturn(resultadoGuardado);

        Producto resultado = productoService.actualizar(1L, actualizado);

        assertEquals(1L, resultado.getId());
        assertEquals("Laptop Pro", resultado.getNombre());
        assertEquals("Electronica", resultado.getCategoria());
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    @DisplayName("CP-005: Debe eliminar producto")
    void debeEliminarProducto() {
        doNothing().when(productoRepository).deleteById(1L);

        assertDoesNotThrow(() -> productoService.eliminar(1L));
        verify(productoRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("CP-022: Debe buscar productos por categoria")
    void debeBuscarPorCategoria() {
        List<Producto> lista = Arrays.asList(
            crearProducto(1L, "Laptop", "Electronica", 10),
            crearProducto(2L, "Mouse", "Electronica", 50)
        );
        when(productoRepository.findByCategoria("Electronica")).thenReturn(lista);

        List<Producto> resultado = productoService.buscarPorCategoria("Electronica");

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().allMatch(p -> "Electronica".equals(p.getCategoria())));
        verify(productoRepository, times(1)).findByCategoria("Electronica");
    }

    @Test
    @DisplayName("CP-007: Debe buscar productos con stock bajo")
    void debeBuscarStockBajo() {
        List<Producto> lista = Arrays.asList(
            crearProducto(1L, "Mouse", "Accesorios", 3),
            crearProducto(2L, "Teclado", "Accesorios", 2)
        );
        when(productoRepository.findByStockLessThan(5)).thenReturn(lista);

        List<Producto> resultado = productoService.productosConStockBajo(5);

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().allMatch(p -> p.getStock() < 5));
        verify(productoRepository, times(1)).findByStockLessThan(5);
    }
}