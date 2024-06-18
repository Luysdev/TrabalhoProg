package org.example.model;

import org.example.db.Con;
import org.example.interfaces.ICrud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido implements ICrud<Pedido> {
    private int id;
    private Date data;
    private int clienteId;
    private int usuarioId;
    private List<Produto> produtos;

    public Pedido() {
        this.data = new Date();
        this.produtos = new ArrayList<>();
    }

    public Pedido(Date data, int clienteId, int usuarioId) {
        this.data = data;
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.produtos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public void create() {
        Con con = new Con();
        try {
            String sql = "INSERT INTO lojinha.pedido (data, cliente_id, usuario_id) VALUES (?, ?, ?)";
            PreparedStatement pstm = con.conectar().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setDate(1, new java.sql.Date(this.getData().getTime()));
            pstm.setInt(2, this.getClienteId());
            pstm.setInt(3, this.getUsuarioId());
            pstm.execute();

            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                this.setId(rs.getInt(1));
            }

            for (Produto produto : this.getProdutos()) {
                String insertProdutoPedidoSql = "INSERT INTO lojinha.pedido_produto (pedido_id, produto_id, quantidade) VALUES (?, ?, ?)";
                PreparedStatement pstmProdutoPedido = con.conectar().prepareStatement(insertProdutoPedidoSql);
                pstmProdutoPedido.setInt(1, this.getId());
                pstmProdutoPedido.setInt(2, produto.getId());
                pstmProdutoPedido.setInt(3, 1);
                pstmProdutoPedido.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            con.desconect();
        }
    }

    @Override
    public List<Pedido> read() {
        Con con = new Con();
        List<Pedido> pedidos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM lojinha.pedido";
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setData(rs.getDate("data"));
                pedido.setClienteId(rs.getInt("cliente_id"));
                pedido.setUsuarioId(rs.getInt("usuario_id"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            con.desconect();
        }
        return pedidos;
    }

    @Override
    public void delete(int id) {
        Con con = new Con();
        try {
            String deleteProdutoPedidoSql = "DELETE FROM pedido_produto WHERE pedido_id=?";
            PreparedStatement pstmProdutoPedido = con.conectar().prepareStatement(deleteProdutoPedidoSql);
            pstmProdutoPedido.setInt(1, id);
            pstmProdutoPedido.execute();

            String sql = "DELETE FROM pedido WHERE id=?";
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            con.desconect();
        }
    }

    @Override
    public void update() {
        Con con = new Con();
        try {
            String sql = "UPDATE pedido SET data=?, cliente_id=?, usuario_id=? WHERE id=?";
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            pstm.setDate(1, new java.sql.Date(this.getData().getTime()));
            pstm.setInt(2, this.getClienteId());
            pstm.setInt(3, this.getUsuarioId());
            pstm.setInt(4, this.getId());
            pstm.execute();

            // Limpar a lista de produtos associados ao pedido
            String deleteProdutoPedidoSql = "DELETE FROM pedido_produto WHERE pedido_id=?";
            PreparedStatement pstmDeleteProdutoPedido = con.conectar().prepareStatement(deleteProdutoPedidoSql);
            pstmDeleteProdutoPedido.setInt(1, this.getId());
            pstmDeleteProdutoPedido.execute();

            for (Produto produto : this.getProdutos()) {
                String insertProdutoPedidoSql = "INSERT INTO pedido_produto (pedido_id, produto_id, quantidade) VALUES (?, ?, ?)";
                PreparedStatement pstmProdutoPedido = con.conectar().prepareStatement(insertProdutoPedidoSql);
                pstmProdutoPedido.setInt(1, this.getId());
                pstmProdutoPedido.setInt(2, produto.getId());
                pstmProdutoPedido.setInt(3, 1); // Defina a quantidade desejada
                pstmProdutoPedido.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            con.desconect();
        }
    }
}
