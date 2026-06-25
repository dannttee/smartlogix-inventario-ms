package com.smartlogix.inventario.messaging;

import com.smartlogix.inventario.config.RabbitMQConfig;
import com.smartlogix.inventario.event.PedidoCreadoEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consumirPedidoCreado(PedidoCreadoEvent event) {
        System.out.println("[RabbitMQ] 📦 Pedido recibido en inventario-ms:");
        System.out.println("  → Pedido ID  : " + event.getPedidoId());
        System.out.println("  → Producto ID: " + event.getProductoId());
        System.out.println("  → Cantidad   : " + event.getCantidad());
        System.out.println("  → Estado     : " + event.getEstado());
        // TODO: aquí va la lógica para descontar stock
        // productoService.descontarStock(event.getProductoId(), event.getCantidad());
    }
}
