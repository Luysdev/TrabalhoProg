package org.example.controller;

import org.example.model.Usuario;

import java.util.List;

public class ControllerUsuario extends Usuario{

    //LOGIN USER
    public boolean validaUsuario(String login, String password) {
        Usuario u1 = new Usuario();
        u1.setLogin(login);
        u1.setPassword(password);
        List<Usuario> usuarios = u1.read();
        for (Usuario u : usuarios) {
            if (u.getLogin().equalsIgnoreCase(login) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


    //CREATE USER
    public boolean validaLogin(Usuario usuario) {
        List<Usuario> usuarios = usuario.read();
        for (Usuario u : usuarios) {
            if (u.getLogin().equalsIgnoreCase(usuario.getLogin())) {
                return true;
            }
        }
        return false;
    }

    public boolean incluirUsuario(String login, String password) {
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setPassword(password);
        if (!validaLogin(usuario)) {
            usuario.create();
            return true;
        }
        return false;
    }
}
