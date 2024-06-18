package org.example.model;

import org.example.db.Con;
import org.example.interfaces.ICrud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Produto implements ICrud<Produto> {
    private int id;
    private String nome;
    private String descricao;
    private double preco;

    public Produto() {
    }

    public Produto(String nome, String descricao, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public void create() {
        Con con = new Con();
        try {
            String sql = "INSERT INTO lojinha.produto (nome, descricao, preco) VALUES (?, ?, ?)";
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            pstm.setString(1, this.getNome());
            pstm.setString(2, this.getDescricao());
            pstm.setDouble(3, this.getPreco());
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            con.desconect();
        }
    }

    @Override
    public List<Produto> read() {
        Con con = new Con();
        List<Produto> produtos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM lojinha.produto";
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            con.desconect();
        }
        return produtos;
    }

    @Override
    public void delete(int id) {
        Con con = new Con();
        try {
            String sql = "DELETE FROM lojinha.produto WHERE id=?";
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
            String sql = "UPDATE lojinha.produto SET nome=?, descricao=?, preco=? WHERE id=?";
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            pstm.setString(1, this.getNome());
            pstm.setString(2, this.getDescricao());
            pstm.setDouble(3, this.getPreco());
            pstm.setInt(4, this.getId());
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            con.desconect();
        }
    }
    public Produto readById(int id) {
        Con con = new Con();
        Produto produto = null;
        try {
            String sql = "SELECT * FROM lojinha.produto WHERE id=?";
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto por ID: " + e.getMessage());
        } finally {
            con.desconect();
        }
        return produto;
    }

    public int buscarIdProduto() {
        Con con = new Con();
        int idProduto = 0; // Inicializa como 0 caso não encontre nenhum produto

        try {
            String sql = "SELECT id FROM lojinha.produto WHERE nome=? AND descricao=?";
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            pstm.setString(1, this.nome);
            pstm.setString(2, this.descricao);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                idProduto = rs.getInt("id");
            } else {
                throw new RuntimeException("Produto não encontrado com o nome '" + nome + "' e descrição '" + descricao + "'");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar ID do produto: " + e.getMessage());
        } finally {
            con.desconect();
        }

        return idProduto;
    }
}

