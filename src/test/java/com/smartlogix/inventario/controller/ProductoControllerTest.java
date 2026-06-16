package com.smartlogix.inventario.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartlogix.inventario.entity.Producto;
import com.smartlogix.inventario.service.ProductoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Producto crearProducto(Long id, String nombre, String categoria) {
        Producto p = new Producto();
        p.setId(id);
        p.setNombre(nombre);
        p.setDescripcion("Descripcion prueba");
        p.setPrecio(100.0);
        p.setStock(10);
        p.setCategoria(categoria);
        return p;
    }

    @Test
    @DisplayName("CP-002: GET /api/productos retorna listado")
    void debeListarProductos() throws Exception {
        when(productoService.listarTodos()).thenReturn(Arrays.asList(
            crearProducto(1L, "Laptop", "Electronica"),
            crearProducto(2L, "Mouse", "Accesorios")
        ));

        mockMvc.perform(get("/api/productos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nombre").value("Laptop"))
            .andExpect(jsonPath("$[1].categoria").value("Accesorios"));
    }

    @Test
    @DisplayName("CP-001: POST /api/productos crea producto")
    void debeCrearProducto() throws Exception {
        Producto nuevo = crearProducto(null, "Teclado", "Accesorios");
        Producto creado = crearProducto(1L, "Teclado", "Accesorios");
        when(productoService.guardar(any(Producto.class))).thenReturn(creado);

        mockMvc.perform(post("/api/productos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(nuevo)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.nombre").value("Teclado"));
    }

    @Test
    @DisplayName("CP-003: GET /api/productos/{id} retorna producto existente")
    void debeObtenerProductoPorId() throws Exception {
        when(productoService.buscarPorId(1L)).thenReturn(Optional.of(
            crearProducto(1L, "Laptop", "Electronica")
        ));

        mockMvc.perform(get("/api/productos/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.nombre").value("Laptop"));
    }

    @Test
    @DisplayName("CP-024: GET /api/productos/{id} inexistente retorna vacío")
    void debeRetornarVacioSiNoExiste() throws Exception {
        when(productoService.buscarPorId(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/productos/99"))
            .andExpect(status().isOk())
            .andExpect(content().string(""));
    }

    @Test
    @DisplayName("CP-004: PUT /api/productos/{id} actualiza producto")
    void debeActualizarProducto() throws Exception {
        Producto actualizado = crearProducto(null, "Laptop Pro", "Electronica");
        Producto guardado = crearProducto(1L, "Laptop Pro", "Electronica");
        when(productoService.actualizar(any(Long.class), any(Producto.class))).thenReturn(guardado);

        mockMvc.perform(put("/api/productos/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actualizado)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Laptop Pro"));
    }

    @Test
    @DisplayName("CP-005: DELETE /api/productos/{id} elimina producto")
    void debeEliminarProducto() throws Exception {
        mockMvc.perform(delete("/api/productos/1"))
            .andExpect(status().isOk());
    }
}