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

import com.br.java.fiap.projectv2.model.entities.Favorito;
import com.br.java.fiap.projectv2.model.entities.Usuario;
import com.br.java.fiap.projectv2.model.repositories.FavoritoRepository;
import com.br.java.fiap.projectv2.model.repositories.UsuarioRepository;

@RestController
@RequestMapping("/favorito")
public class FavoritoController {

  @Autowired
  private FavoritoRepository fr;
  @Autowired
  private UsuarioRepository ur;
  
  @PostMapping("/insertFav")
  public String inserir(Favorito favorito, @RequestParam(name = "idUser") int idUser) {
    
    StringBuilder aux = new StringBuilder();
    aux.append("<body style = font-size:30px><html>");

    try {
    Optional<Usuario> usuario = ur.findById(idUser);
    
    if(fr.existsById(favorito.getId())) {
      aux.append("Ja existe um favorito com esse ID!");
    }

    if(usuario != null) {
      Usuario user = usuario.get();
      Favorito fav = new Favorito(favorito.getId(), favorito.getNome(), user);
      //TODO verificacao de usuario
      
      fr.save(fav);
      aux.append("Favorito inserido com sucesso!") ;
    }else {
      aux.append("Erro ao inserir favorito, nao existe usuario com esse Id!") ;
    }
    
    }catch(NoSuchElementException e){
      aux.append("Nao existe usuario com esse ID!");
    }catch(DataIntegrityViolationException e) {
      aux.append("Ja existe um favorito que contem o id deste usuario!");
    }
    
    catch(Exception e) {
      aux.append("Erro ao inserir favorito" + e) ;
    }
    aux.append("<br/>");
    aux.append("<br/>");
    aux.append("<a style=\"text-decoration:none;font-size:30px; color:blue \" href=\"http://localhost:8080/favorito.html\">Voltar para Favorito</a>");
    aux.append("</body></html>");
    
    return aux.toString();
  }
  
  
  @GetMapping("/searchAllFavs")
  public String procurarTodos() {
    Iterable<Favorito> fav = fr.findAll();
    StringBuilder aux = new StringBuilder();
    aux.append("<body style = font-size:30px><html>");
    for (Favorito favv : fav) {
      aux.append("Favorito de ID: " + favv.getId());
      aux.append("<br/>");
      aux.append("Nome: " + favv.getNome());
      aux.append("<br/>");
      aux.append("ID do Usuario que tem esse Favorito: " + favv.getUsuario().getId());
      aux.append("<br/>");
      aux.append("<br/>");
    }
    aux.append("<a style=\"text-decoration:none;font-size:30px; color:blue \" href=\"http://localhost:8080/favorito.html\">Voltar para Favorito</a>");
    aux.append("<br/>");
    aux.append("<br/>");
    aux.append("</body></html>");
    return aux.toString();

  }
  
  @GetMapping(path = "/searchFav")
  public String procurarPorId(@RequestParam(name = "id") int id) {
    Optional<Favorito> fav = fr.findById(id);
    Favorito favv = null;
    if(fav != null) {
       favv = fav.get();
    }
    
    StringBuilder aux = new StringBuilder();
    aux.append("<body style = font-size:30px><html>");
    aux.append("Favorito de ID: " + favv.getId());
    aux.append("<br/>");
    aux.append("Nome: " + favv.getNome());
    aux.append("<br/>");
    aux.append("ID do Usuario que tem esse Favorito: " + favv.getUsuario().getId());
    aux.append("<br/>");
    aux.append("<br/>");
    aux.append("<a style=\"text-decoration:none;font-size:30px; color:blue \" href=\"http://localhost:8080/favorito.html\">Voltar para Favorito</a>");
    aux.append("</body></html>");

    return aux.toString();
  }
  
  @GetMapping(path = "/deleteFav")
  public String deletar(@RequestParam(name = "id") int id) {
    StringBuilder aux = new StringBuilder();
    aux.append("<body style = \"font-size:30px\" color:blue ><html>");

    if(fr.existsById(id)) {
      fr.deleteById(id);
    aux.append("Favorito de ID " + id + " REMOVIDO do banco de dados");
    }else {
      aux.append("Nao foi achado nenhum favorito com este ID!");
    }
    aux.append("<br/>");
    aux.append("<br/>");
    aux.append("<a style=\"text-decoration:none;font-size:30px; color:blue \" href=\"http://localhost:8080/favorito.html\">Voltar para Usuario</a>");
    aux.append("</body></html>");
    return aux.toString();

  }

}
