package com.smartlogix.inventario.event;

import java.time.LocalDateTime;

public class PedidoCreadoEvent {

    private Long          pedidoId;
    private Long          productoId;
    private int           cantidad;
    private String        estado;
    private LocalDateTime fechaCreacion;

    public PedidoCreadoEvent() {}

    public Long          getPedidoId()      { return pedidoId; }
    public Long          getProductoId()    { return productoId; }
    public int           getCantidad()      { return cantidad; }
    public String        getEstado()        { return estado; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public void setPedidoId(Long v)          { pedidoId      = v; }
    public void setProductoId(Long v)        { productoId    = v; }
    public void setCantidad(int v)           { cantidad      = v; }
    public void setEstado(String v)          { estado        = v; }
    public void setFechaCreacion(LocalDateTime v) { fechaCreacion = v; }
}