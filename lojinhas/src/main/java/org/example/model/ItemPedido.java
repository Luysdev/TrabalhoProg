package org.example.model;

import org.example.db.Con;
import org.example.interfaces.ICrud;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemPedido implements ICrud<ItemPedido>{
    private int produto_id;
    private int pedido_id;
    private int quantidade;

    public int getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(int produto_id) {
        this.produto_id = produto_id;
    }

    public int getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(int pedido_id) {
        this.pedido_id = pedido_id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public List<ItemPedido> read() {
        Con con = new Con();
        List<ItemPedido> itensPedido = new ArrayList<>();
        try {
            String sql = "SELECT * FROM lojinha.pedido_produto";
            Statement stm = con.conectar().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ItemPedido item = new ItemPedido();
                item.setProduto_id(rs.getInt("produto_id"));
                item.setPedido_id(rs.getInt("pedido_id"));
                item.setQuantidade(rs.getInt("quantidade"));
                itensPedido.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens de pedido: " + e.getMessage());
        } finally {
            con.desconect();
        }
        return itensPedido;
    }
    public List<ItemPedido> readById(int id) {
        Con con = new Con();
        List<ItemPedido> itensPedido = new ArrayList<>();
        try {
            String sql = "SELECT * FROM lojinha.pedido_produto WHERE pedido_id = ?";
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setPedido_id(rs.getInt("pedido_id"));
                itemPedido.setProduto_id(rs.getInt("produto_id"));
                itemPedido.setQuantidade(rs.getInt("quantidade"));
                itensPedido.add(itemPedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar item de pedido por ID: " + e.getMessage());
        } finally {
            con.desconect();
        }
        return itensPedido;
    }

    @Override
    public void update() {
        Con con = new Con();
        try {
            String sql = "UPDATE lojinha.pedido_produto SET produto_id = ?, pedido_id = ?, quantidade = ? WHERE produto_id = ?";
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            pstm.setInt(1, this.produto_id);
            pstm.setInt(2, this.pedido_id);
            pstm.setInt(3, this.quantidade);
            // Supondo que exista um atributo 'id' na classe ItemPedido para identificar o registro a ser atualizado
            // Pode ser necessário ajustar isso dependendo da sua implementação
            pstm.setInt(4, this.produto_id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar item de pedido: " + e.getMessage());
        } finally {
            con.desconect();
        }
    }
    @Override
    public void delete(int id) {
        Con con = new Con();
        try {
            String sql = "DELETE FROM lojinha.pedido_produto WHERE produto_id = ?";
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            pstm.setInt(1, this.produto_id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar item de pedido: " + e.getMessage());
        } finally {
            con.desconect();
        }
    }
    @Override
    public void create() {
        Con con = new Con();
        try {
            String sql = "INSERT INTO item_pedido (produto_id, pedido_id, quantidade) VALUES (?, ?, ?)";
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            pstm.setInt(1, this.produto_id);
            pstm.setInt(2, this.pedido_id);
            pstm.setInt(3, this.quantidade);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar item de pedido: " + e.getMessage());
        } finally {
            con.desconect();
        }
    }
}
