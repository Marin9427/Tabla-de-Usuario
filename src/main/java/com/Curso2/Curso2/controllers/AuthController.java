package com.Curso2.Curso2.controllers;
import com.Curso2.Curso2.Models.Usuario;
import com.Curso2.Curso2.Utils.JWTutil;
import com.Curso2.Curso2.dao.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController
{
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTutil jwtutil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario)
    {
        Usuario Usuarilogeado= usuarioDao.ObtenerUsuarioporCredenciales(usuario);

         if (Usuarilogeado != null)
         {
            String tokenJWT= jwtutil.create(String.valueOf(Usuarilogeado.getId()) ,Usuarilogeado.getEmail());

             return tokenJWT;
         }
         return "fail";
    }



}
