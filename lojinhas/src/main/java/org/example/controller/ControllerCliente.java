package org.example.controller;

import org.example.model.Cliente;
import java.util.List;

public class ControllerCliente extends Cliente {

    public boolean cadastrarCliente(Cliente cliente) {
        cliente.create();
        return true;
    }
    public List<Cliente> listaClientes() {
        return this.read();
    }

    public Cliente buscaClienteById(int id) {
        Cliente cliente = this.readById(id);
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado para o ID: " + id);
        }
        return cliente;
    }
}
