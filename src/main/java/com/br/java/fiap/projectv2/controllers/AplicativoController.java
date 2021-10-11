package com.br.java.fiap.projectv2.controllers;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.java.fiap.projectv2.model.entities.Aplicativo;
import com.br.java.fiap.projectv2.model.entities.Usuario;
import com.br.java.fiap.projectv2.model.repositories.AplicativoRepository;
import com.br.java.fiap.projectv2.model.repositories.UsuarioRepository;

@RestController
@RequestMapping("/aplicativo")
public class AplicativoController {

  @Autowired
  private AplicativoRepository ar;
  @Autowired
  private UsuarioRepository ur;

  @PostMapping("/insertApp")
  public String inserir(Aplicativo aplicativo, @RequestParam(name = "idUser") int idUser) {
    StringBuilder aux = new StringBuilder();
    aux.append("<body style = font-size:30px><html>");

    try {
      Optional<Usuario> usuario = ur.findById(idUser);

      if (ar.existsById(aplicativo.getId())) {
        aux.append("Ja existe um aplicativo com esse ID!");
      } else if (usuario != null) {
        Usuario user = usuario.get();
        Aplicativo app = new Aplicativo(aplicativo.getId(), aplicativo.getNome(), user);

        ar.save(app);
        aux.append("Aplicativo inserido com sucesso!");
      } else {
        aux.append("Erro ao inserir aplicativo, nao existe usuario com esse Id!");
      }

    } catch (NoSuchElementException e) {
      aux.append("Nao existe usuario com esse ID!");
    } catch (DataIntegrityViolationException e) {
      aux.append("Ja existe um aplicativo que contem o id deste usuario!");
    } catch (Exception e) {
      aux.append("Erro ao inserir aplicativo" + e);
    }
    aux.append("<br/>");
    aux.append("<br/>");
    aux.append(
        "<a style=\"text-decoration:none;font-size:30px; color:blue \" href=\"http://localhost:8080/aplicativo.html\">Voltar para Aplicativo</a>");
    aux.append("</body></html>");

    return aux.toString();
  }

  @GetMapping(path = "/searchApp")
  public String procurarPorId(@RequestParam(name = "id") int id) {
    Optional<Aplicativo> app = ar.findById(id);
    Aplicativo appv = null;
    StringBuilder aux = new StringBuilder();
    aux.append("<body style = font-size:30px><html>");

    try {
      app = ar.findById(id);
      if (app != null) {
        appv = app.get();
      }
    } catch (NoSuchElementException e) {
      aux.append("Nao foi encontrado nenhum aplicativo com este ID!");
      aux.append("<br/>");
      aux.append(
          "<a style=\"text-decoration:none;font-size:30px; color:blue \" href=\"http://localhost:8080/aplicativo.html\">Voltar para Aplicativo</a>");
      aux.append("</body></html>");
      return aux.toString();
    }

    if (app != null) {
      appv = app.get();
    }

    aux.append("Aplicativo de ID: " + appv.getId());
    aux.append("<br/>");
    aux.append("Nome: " + appv.getNome());
    aux.append("<br/>");
    aux.append("ID do Usuario que tem esse App: " + appv.getUsuario().getId());
    aux.append("<br/>");
    aux.append("<br/>");
    aux.append(
        "<a style=\"text-decoration:none;font-size:30px; color:blue \" href=\"http://localhost:8080/aplicativo.html\">Voltar para Aplicativo</a>");
    aux.append("</body></html>");

    return aux.toString();
  }

  @GetMapping("/searchAllApps")
  public String procurarTodos() {
    Iterable<Aplicativo> apps = ar.findAll();
    StringBuilder aux = new StringBuilder();
    aux.append("<body style = font-size:30px><html>");
    for (Aplicativo app : apps) {
      aux.append("Aplicativo de ID: " + app.getId());
      aux.append("<br/>");
      aux.append("Nome: " + app.getNome());
      aux.append("<br/>");
      aux.append("ID do Usuario que tem esse App: " + app.getUsuario().getId());
      aux.append("<br/>");
      aux.append("<br/>");
    }
    aux.append(
        "<a style=\"text-decoration:none;font-size:30px; color:blue \" href=\"http://localhost:8080/aplicativo.html\">Voltar para Aplicativo</a>");
    aux.append("<br/>");
    aux.append("<br/>");
    aux.append("</body></html>");
    return aux.toString();

  }

  @GetMapping("/updateApp")
  public String alterar(Aplicativo aplicativo, @RequestParam(name = "idUser") int idUser) {
    StringBuilder aux = new StringBuilder();
    Optional<Aplicativo> app = ar.findById(aplicativo.getId());

    Optional<Usuario> user = ur.findById(idUser);
    aux.append("<body style = \"font-size:30px\" color:blue ><html>");
    try {
      if (app != null && user != null) {
        Usuario userv = user.get();
        Aplicativo favor = new Aplicativo(aplicativo.getId(), aplicativo.getNome(), userv);
        ar.save(favor);
        aux.append("Aplicativo alterado");
      }
    } catch (NoSuchElementException e) {
      aux.append("Nao foi possivel encontrar nenhum ID do aplicativo ou de Usuario!");
    }

    aux.append("<br/>");
    aux.append("<br/>");
    aux.append(
        "<a style=\"text-decoration:none;font-size:30px; color:blue \" href=\"http://localhost:8080/aplicativo.html\">Voltar para aplicativo</a>");
    aux.append("</body></html>");
    return aux.toString();
  }

  @GetMapping(path = "/deleteApp")
  public String deletar(@RequestParam(name = "id") int id) {
    StringBuilder aux = new StringBuilder();
    aux.append("<body style = \"font-size:30px\" color:blue ><html>");

    if (ar.existsById(id)) {
      ar.deleteById(id);
      aux.append("Aplicativo de ID " + id + " REMOVIDO do banco de dados");
    } else {
      aux.append("Nao foi achado nenhum aplicativo com este ID!");
    }
    aux.append("<br/>");
    aux.append("<br/>");
    aux.append(
        "<a style=\"text-decoration:none;font-size:30px; color:blue \" href=\"http://localhost:8080/aplicativo.html\">Voltar para Usuario</a>");
    aux.append("</body></html>");
    return aux.toString();
  }

}
