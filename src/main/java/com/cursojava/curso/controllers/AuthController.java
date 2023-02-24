package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtutil;

    @RequestMapping(value="api/login", method= RequestMethod.POST)
    public String registrarUsuario(@RequestBody Usuario usuario){
        Usuario usuarioLog = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if (usuarioLog != null){
            return jwtutil.create(String.valueOf(usuarioLog.getId()),usuarioLog.getEmail());
        }else{
            return "FAIL";
        }
    }
}
