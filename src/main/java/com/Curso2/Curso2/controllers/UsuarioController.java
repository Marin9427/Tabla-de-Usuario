package com.Curso2.Curso2.controllers;

import com.Curso2.Curso2.Models.Usuario;
import com.Curso2.Curso2.Utils.JWTutil;
import com.Curso2.Curso2.dao.UsuarioDao;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
//
@RestController
public class UsuarioController
{
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTutil jwTutil;




    @RequestMapping(value = "api/Usuarios/{id}" , method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id)
    {
        Usuario individuo = new Usuario();
        individuo.setId(id);
        individuo.setNombre("Jose");
        individuo.setApellido("Marin");
        individuo.setEmail("jose@marin");
        individuo.setTelefono("7331393239");
        individuo.setConstrasena("Manchas");

        return individuo;
    }






    @RequestMapping(value = "api/Usuarios",method = RequestMethod.GET)
    public List<Usuario> ListadoUsuario (@RequestHeader (value="Authorization") String token)
    {
    if(!validartoken(token))
    {
        return null;
    }

    return usuarioDao.getUsuarios();
    }

    private boolean validartoken(String token)
    {
        String idUsuario=jwTutil.getKey(token);
        return idUsuario != null;
    }



    @RequestMapping(value = "api/Usuarios",method = RequestMethod.POST)
    public void RegistrarUsuario (@RequestBody Usuario usuario)
    {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024 ,1, usuario.getConstrasena());
        usuario.setConstrasena(hash);
       usuarioDao.registrar(usuario);
    }





    @RequestMapping(value = "Editar")
    public Usuario Editar()
    {
        Usuario individuo = new Usuario();

        individuo.setNombre("Jose");
        individuo.setApellido("Marin");
        individuo.setEmail("jose@marin");
        individuo.setTelefono("7331393239");
        individuo.setConstrasena("Manchas");

        return individuo;
    }






    @RequestMapping(value = "api/Usuarios/{id}" , method = RequestMethod.DELETE)
    public  void Eliminar(@RequestHeader (value="Authorization") String token , @PathVariable  Long id)
    {

        if(!validartoken(token))
        {
            return ;
        }
        usuarioDao.Eliminar(id);

    }




}
