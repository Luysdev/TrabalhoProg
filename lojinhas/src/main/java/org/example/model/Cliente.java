package org.example.model;

import org.example.db.Con;
import org.example.interfaces.ICrud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements ICrud<Cliente>{

    private int id;
    private String nome;
    private String email;
    private String cpf;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * CRUD - Create new cliente
     */
    @Override
    public void create() {
        Con con = new Con();
        try {
            String sql = "INSERT INTO lojinha.cliente(nome, email, cpf) VALUES (?, ?, ?)";
            // Criar um objeto que se comunica com o banco de dados através da conexão
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            // Atribuir valores aos parâmetros
            pstm.setString(1, this.getNome());
            pstm.setString(2, this.getEmail());
            pstm.setString(3, this.getCpf());
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            con.desconect();
        }
    }

    @Override
    public List<Cliente> read() {
        Con con = new Con();
        List<Cliente> clientes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM lojinha.cliente";
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setCpf(rs.getString("cpf"));
                clientes.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            con.desconect();
        }
        return clientes;
    }

    public Cliente readById(int id) {
        Con con = new Con();
        Cliente cliente = null;
        try {
            String sql = "SELECT * FROM lojinha.cliente WHERE id = ?";
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCpf(rs.getString("cpf"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por ID: " + e.getMessage());
        } finally {
            con.desconect();
        }
        return cliente;
    }


    @Override
    public void delete(int id) {
        Con con = new Con();
        try {
            String sql = "DELETE FROM lojinha.cliente WHERE id=?";
            // Criar um objeto que se comunica com o banco de dados através da conexão
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            // Atribuir valores aos parâmetros
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
            String sql = "UPDATE lojinha.cliente SET name=?, email=?, cpf=? WHERE id=?";
            // Criar um objeto que se comunica com o banco de dados através da conexão
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            // Atribuir valores aos parâmetros
            pstm.setString(1, this.getNome());
            pstm.setString(2, this.getEmail());
            pstm.setString(3, this.getCpf());
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            con.desconect();
        }
    }
}
