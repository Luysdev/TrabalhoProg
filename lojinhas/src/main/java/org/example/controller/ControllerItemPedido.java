package org.example.controller;

import org.example.model.Cliente;
import org.example.model.ItemPedido;
import org.example.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class ControllerItemPedido extends ItemPedido {

    public List<Integer> buscaItensById(int id) {
        List<ItemPedido> itensPedido = this.readById(id);
        if (itensPedido == null || itensPedido.isEmpty()) {
            throw new RuntimeException("Itens de pedido não encontrados para o ID: " + id);
        }

        List<Integer> idsProdutos = new ArrayList<>();
        for (ItemPedido itemPedido : itensPedido) {
            idsProdutos.add(itemPedido.getProduto_id());
        }

        return idsProdutos;
    }
}