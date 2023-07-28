package com.Curso2.Curso2.dao;

import com.Curso2.Curso2.Models.Usuario;
import  java.util.List;

public interface UsuarioDao {


    public List<Usuario> getUsuarios();


  void Eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario ObtenerUsuarioporCredenciales(Usuario usuario);
}
