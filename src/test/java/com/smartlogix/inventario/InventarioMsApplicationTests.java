package com.smartlogix.inventario;

import com.smartlogix.inventario.entity.Producto;
import com.smartlogix.inventario.repository.ProductoRepository;
import com.smartlogix.inventario.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventarioMsApplicationTests {

    @Mock
    private ProductoRepository productoRepository;

    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productoService = new ProductoService(productoRepository);
    }

    @Test
    void listarTodos_retornaLista() {
        Producto p = new Producto();
        p.setId(1L); p.setNombre("Laptop");
        when(productoRepository.findAll()).thenReturn(List.of(p));

        List<Producto> result = productoService.listarTodos();

        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getNombre());
    }

    @Test
    void buscarPorId_retornaProducto() {
        Producto p = new Producto();
        p.setId(1L); p.setNombre("Monitor");
        when(productoRepository.findById(1L)).thenReturn(Optional.of(p));

        Optional<Producto> result = productoService.buscarPorId(1L);

        assertTrue(result.isPresent());
        assertEquals("Monitor", result.get().getNombre());
    }

    @Test
    void buscarPorId_retornaVacioCuandoNoExiste() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Producto> result = productoService.buscarPorId(99L);

        assertFalse(result.isPresent());
    }

    @Test
    void guardar_retornaProductoGuardado() {
        Producto p = new Producto();
        p.setNombre("Teclado");
        when(productoRepository.save(p)).thenReturn(p);

        Producto result = productoService.guardar(p);

        assertNotNull(result);
        assertEquals("Teclado", result.getNombre());
    }

    @Test
    void eliminar_llamaDeleteById() {
        productoService.eliminar(1L);
        verify(productoRepository, times(1)).deleteById(1L);
    }

    @Test
    void buscarPorCategoria_retornaLista() {
        Producto p = new Producto();
        p.setCategoria("Electrónica");
        when(productoRepository.findByCategoria("Electrónica")).thenReturn(List.of(p));

        List<Producto> result = productoService.buscarPorCategoria("Electrónica");

        assertEquals(1, result.size());
    }
}