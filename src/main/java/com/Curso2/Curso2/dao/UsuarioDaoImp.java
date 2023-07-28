package com.Curso2.Curso2.dao;

import com.Curso2.Curso2.Models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository //Tendra funcionalidad que funcionalidad de acceder al repocitorio de la base de datos
@Transactional // Como va tratar las consultas de SQL

public class UsuarioDaoImp implements  UsuarioDao
{

    @PersistenceContext
 EntityManager entityManager;

    @Override
    public List<Usuario> getUsuarios() {

       String query="FROM Usuario";

     List<Usuario> resultado =  entityManager.createQuery(query).getResultList();
     return resultado;
    }





    @Override
    public void Eliminar(Long id)
    {
        Usuario usuarioo= entityManager.find(Usuario.class , id);
         entityManager.remove(usuarioo);
    }





    @Override
    public void registrar(Usuario usuario)
    {
  entityManager.merge(usuario);
    }

    @Override
    public Usuario ObtenerUsuarioporCredenciales(Usuario usuario) {
        String query="FROM Usuario WHERE Email = :Email";  //AND Constrasena = :Constrasena
        List<Usuario> lista =entityManager.createQuery(query)
                .setParameter("Email",usuario.getEmail())
                .getResultList();                                                     //.setParameter("Constrasena",usuario.getConstrasena())

        if(lista.isEmpty())
        {
            return null;

        }

        String ContraseñaHashes= lista.get(0).getConstrasena();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(ContraseñaHashes,usuario.getConstrasena()))
        {
   return lista.get(0);
        }

        return null;
    }


}
