package org.example.interfaces;

import org.example.model.Cliente;

import java.util.List;

import org.example.model.Cliente;
import org.example.model.Usuario;

import java.util.List;

public interface ICrud<T> {
    public void create();
    public List<T> read();
    public void delete(int id);
    public void update();
}
