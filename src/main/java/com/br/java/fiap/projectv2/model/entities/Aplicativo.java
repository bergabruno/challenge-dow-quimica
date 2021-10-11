package com.br.java.fiap.projectv2.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_aplicativo")
public class Aplicativo {

  @Id
  @Column(name = "id_aplicativo")
  private int id;

  @Column(name = "nm_aplicativo")
  private String nome;

  @OneToOne
  @JoinColumn(name = "usuario_id", unique = true)
  private Usuario usuario;

  public String toString() {
    return String.format("Nome do Aplicativo: %s, Id do Aplicativo: %d,  Nome do Usuario: %s %s, ID do usuario: %d  ",
        nome, id, usuario.getNome(), usuario.getSobrenome(), usuario.getId());
  }

  public Aplicativo() {
  }

  public Aplicativo(int id, String nome, Usuario usuario) {
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

  public void setUsuarioById(int id) {

  }

}
