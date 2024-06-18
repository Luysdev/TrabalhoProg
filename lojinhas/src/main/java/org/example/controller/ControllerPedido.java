package org.example.controller;

import org.example.model.Pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

public class ControllerPedido extends Pedido {
    // Método para listar pedidos
    // Método para listar pedidos
    public List<Pedido> listaPedidos() {
        List<Pedido> pedidos = this.read();
        if (pedidos == null || pedidos.isEmpty()) {
            throw new RuntimeException("Não há pedidos cadastrados");
        }
        return pedidos;
    }

    // Método para incluir um novo pedido
    public boolean incluirPedido(Pedido pedido) {
        if (pedido.getClienteId() <= 0) {
            throw new IllegalArgumentException("O cliente do pedido deve ser válido");
        }
        if (pedido.getProdutos() == null || pedido.getProdutos().isEmpty()) {
            throw new IllegalArgumentException("O pedido deve conter pelo menos um produto");
        }
        if (pedido.getData() == null) {
            throw new IllegalArgumentException("A data do pedido não pode estar vazia");
        }
        pedido.create();
        return true;
    }
}


