package com.br.java.fiap.projectv2.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "tb_favorito")
public class Favorito {

  @Id
  @Column(name = "id_favorito")
  private int id;
  
  @Column(name = "nm_favorito")
  private String nome;
  
  @ManyToOne
  @JoinColumn(name = "usuario_id")
  @Cascade(CascadeType.DELETE)
  private Usuario usuario;
  

  public Favorito() {}
  
  public Favorito(int id, String nome, Usuario usuario) {
    super();
    this.id = id;
    this.nome = nome;
    this.usuario = usuario;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  
  
  
}
