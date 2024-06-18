package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Con {
    private Connection con;
    public Connection conectar() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "123123");
            if (con != null) {
                System.out.println("Conectado ao micropenis com sucesso!");
            } else {
                System.out.println("micropenis nao encontrado!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void desconect() {
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
    }
}
