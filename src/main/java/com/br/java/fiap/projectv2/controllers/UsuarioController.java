package com.br.java.fiap.projectv2.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.java.fiap.projectv2.model.entities.Usuario;
import com.br.java.fiap.projectv2.model.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  @Autowired
  UsuarioRepository ur;

  @PostMapping("/insertUser")
  public String inserir(Usuario usuario) {
//    ModelAndView model = new ModelAndView();
//    model.setViewName("usuario.html");
    StringBuilder aux = new StringBuilder();
    aux.append("<body style = font-size:30px><html>");

    if (ur.existsById(usuario.getId())) {
       aux.append("Já existe um usuario com este Id");
    } else {
      try {
        ur.save(usuario);
        aux.append("Usuario inserido com Sucesso");
      } catch (DataIntegrityViolationException e) {
        aux.append("Nao foi possivel inserir usuario pois o email/cpf ja existe! ");
      }catch(Exception e) {
        aux.append("Faltou dados para inserir um cliente!");
      }
    }
    aux.append("<br/>");
    aux.append("<br/>");
    aux.append("<br/>");
    aux.append("<a style=\"text-decoration:none;font-size:30px; color:blue \" href=\"http://localhost:8080/usuario.html\">Voltar para Usuario</a>");
    aux.append("</body></html>");

    return aux.toString();
  }

  @PutMapping("/updateUser")
  public String alterar(Usuario usuario) {

    Optional<Usuario> user = ur.findById(usuario.getId());

    if (user != null) {
      ur.save(usuario);
    }

    return "Usuario alterado";
  }

  @GetMapping(path = "/deleteUser")
  public String deletar(@RequestParam(name = "idUser") int id) {
    StringBuilder aux = new StringBuilder();
    aux.append("<body style = \"font-size:30px\" color:blue ><html>");

    if(ur.existsById(id)) {
    ur.deleteById(id);
    aux.append("Usuario de ID " + id + " REMOVIDO do banco de dados");
    }else {
      aux.append("Nao foi achado nenhum usuario com este ID!");
    }
    aux.append("<br/>");
    aux.append("<br/>");
    aux.append("<a style=\"text-decoration:none;font-size:30px; color:blue \" href=\"http://localhost:8080/usuario.html\">Voltar para Usuario</a>");
    aux.append("</body></html>");
    return aux.toString();

  }

  @GetMapping(path = "/searchUser")
  public String procurarPorId(@RequestParam(name = "id") int id) {
    Optional<Usuario> usuario = ur.findById(id);
    Usuario user = null;
    
    if(usuario != null) {
      user = usuario.get();
    }
    StringBuilder aux = new StringBuilder();
    aux.append("<body style = \"font-size:30px\" color:blue ><html>");
    aux.append("Usuario de ID: " + user.getId());
    aux.append("<br/>");
    aux.append("Nome: " + user.getNome() + " " + user.getSobrenome());
    aux.append("<br/>");
    aux.append("CPF: " + user.getCpf());
    aux.append("<br/>");
    aux.append("<a style=\"text-decoration:none;font-size:30px; color:blue \" href=\"http://localhost:8080/usuario.html\">Voltar para Usuario</a>");
    aux.append("</body></html>");

    return aux.toString();
  }

  @GetMapping("/searchAllUsers")
  public String procurarTodos() {
    Iterable<Usuario> users = ur.findAll();
    StringBuilder aux = new StringBuilder();
    aux.append("<body style = font-size:30px><html>");
    for (Usuario usuario : users) {
      aux.append("Usuario de ID: " + usuario.getId());
      aux.append("<br/>");
      aux.append("Nome: " + usuario.getNome() + " " + usuario.getSobrenome());
      aux.append("<br/>");
      aux.append("CPF: " + usuario.getCpf());
      aux.append("<br/>");
      aux.append("<br/>");
    }
    aux.append("<a style=\"text-decoration:none;font-size:30px; color:blue \" href=\"http://localhost:8080/usuario.html\">Voltar para Usuario</a>");
    aux.append("</body></html>");
    return aux.toString();

  }
}
