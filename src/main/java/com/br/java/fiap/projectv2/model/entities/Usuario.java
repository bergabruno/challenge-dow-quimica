package com.br.java.fiap.projectv2.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

  @Id
  @Column(name = "id_usuario")
  private int id;

  @Column(name = "nome_usuario", nullable = false)
  private String nome;

  @Column(name = "sobrenome_usuario")
  private String sobrenome; 

  @Column(name = "email_usuario", nullable = false, unique = true)
  private String email;

  @Column(name = "cpf_usuario", nullable = false, unique = true)
  private String cpf;

  @Column(name = "idade_usuario", nullable = false)
  private int idade;

  @OneToOne(mappedBy = "usuario")
  @Cascade(CascadeType.REMOVE)
  private Aplicativo aplicativo;

  @OneToMany(mappedBy = "usuario", orphanRemoval = true)
  @Cascade(CascadeType.ALL)
  private List<Favorito> favoritos = new ArrayList<Favorito>();

  public Usuario() {

  }

  
  public Usuario(int id, String nome, String sobrenome, String email, String cpf, int idade, Aplicativo aplicativo,
      List<Favorito> favoritos) {
    super();
    this.id = id;
    this.nome = nome;
    this.sobrenome = sobrenome;
    this.email = email;
    this.cpf = cpf;
    this.idade = idade;
    this.aplicativo = aplicativo;
    this.favoritos = favoritos;
  }


  public String toString() {
    return String.format("Usuario de ID: %d, possui nome: %s,  cpf: %s, email: %s e idade: %d", id, nome, cpf, email,
        idade);
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

  public String getSobrenome() {
    return sobrenome;
  }

  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public int getIdade() {
    return idade;
  }

  public void setIdade(int idade) {
    this.idade = idade;
  }

  public Aplicativo getAplicativo() {
    return aplicativo;
  }

  public void setAplicativo(Aplicativo aplicativo) {
    this.aplicativo = aplicativo;
  }

  public List<Favorito> getFavoritos() {
    return favoritos;
  }

  public void setFavoritos(List<Favorito> favoritos) {
    this.favoritos = favoritos;
  }
  
  
  
  

}
