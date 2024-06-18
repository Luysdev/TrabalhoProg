package org.example;

import org.example.controller.ControllerCliente;
import org.example.controller.ControllerItemPedido;
import org.example.controller.ControllerUsuario;
import org.example.db.Con;
import org.example.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
//        Con con = new Con();
//        Cliente cli2 = new Cliente();
//        cli2.setNome("Geraldo");
//        cli2.setEmail("geraldo@gmail.com");
//        cli2.setCpf("13539414932");
//        cli2.create();
//        ControllerCliente controllerCliente = new ControllerCliente();
//        controllerCliente.listaClientes();
//        System.out.println(controllerCliente.listaClientes());
//        ControllerUsuario controllerUser = new ControllerUsuario();
//        Usuario usuario = new Usuario();
//
//
//
//        Produto produto = new Produto();
//        produto.setNome("Produto 1");
//        produto.setDescricao("Produto 1");
//        produto.setPreco(22.55);
//        produto.create();
//        usuario.setLogin("luis22");
//        usuario.setPassword("123123");
//
//        Pedido pedido = new Pedido();
//        pedido.setProdutos(produto.read());
//        pedido.setClienteId(2);
//        pedido.setUsuarioId(2);
//        pedido.create();
//        for (Cliente c : controllerCliente.listaClientes()) {
//            System.out.println(c.getId());
//        }
        ControllerItemPedido itemPedido = new ControllerItemPedido();
        List<Integer> idsProdutos = itemPedido.buscaItensById(2); // Exemplo de ID de pedido

        System.out.println("IDs dos produtos associados ao pedido:");
        for (Integer idProduto : idsProdutos) {
            System.out.println(idProduto);
        }

    }
}
