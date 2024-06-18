package org.example.model;

import org.example.db.Con;
import org.example.interfaces.ICrud;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements ICrud<Usuario> {
    private int id;
    private String login;
    private String password;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void create() {
        Con con = new Con();
        try {
            String sql = "INSERT INTO lojinha.usuario(login, password) VALUES (?, ?)";
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            pstm.setString(1, this.getLogin());
            pstm.setString(2, this.getPassword());
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            con.desconect();
        }
    }

    @Override
    public List<Usuario> read() {
        Con con = new Con();
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String sql = "SELECT * FROM lojinha.usuario";
            PreparedStatement pstm = con.conectar().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setLogin(rs.getString("login"));
                usuario.setPassword(rs.getString("password"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            con.desconect();
        }
        return usuarios;
    }

    @Override
    public void delete(int id) {
        Con con = new Con();
        try {
            String sql = "DELETE FROM lojinha.usuario WHERE id=?";
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
            String sql = "UPDATE lojinha.usuario SET login=?, password=? WHERE id=?";

            PreparedStatement pstm = con.conectar().prepareStatement(sql);

            pstm.setString(1, this.getLogin());
            pstm.setString(2, this.getPassword());
            pstm.setInt(3, this.getId());
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            con.desconect();
        }
    }
}
