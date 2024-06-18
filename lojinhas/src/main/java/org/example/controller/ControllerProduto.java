package org.example.controller;

import org.example.db.Con;
import org.example.model.Cliente;
import org.example.model.Produto;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ControllerProduto extends Produto {

    public boolean incluirProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode estar vazio");
        }
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero");
        }
        produto.create();
        return true;
    }

    public List<Produto> listaProdutos() {
        List<Produto> produtos = this.read();
        if (produtos == null || produtos.isEmpty()) {
            throw new RuntimeException("Não há produtos cadastrados");
        }
        return produtos;
    }

    public Produto buscarProdutoById(int id) {
        Produto produto = this.readById(id);
        if (produto == null) {
            throw new RuntimeException("Produto não encontrado para o ID: " + id);
        }
        return produto;
    }

    public boolean atualizaPedido(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode estar vazio");
        }
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero");
        }

        try {
            produto.update(); // Chama o método update() do objeto Produto
            return true;
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public int buscarIdProduto(Produto produto) {
        try {
            return produto.buscarIdProduto();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar ID do produto: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public boolean deletaProduto(int id) {
        this.delete(id);
        return true;
    }
}
