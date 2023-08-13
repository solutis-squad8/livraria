package br.com.squad8.desafio.livraria.domain;

import jakarta.persistence.*;

@Entity(name = "livro")
@DiscriminatorColumn(name = "type")
public abstract class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String autores;
    @Column(nullable = false)
    private String editora;
    @Column(nullable = false)
    private Float preco;
    @Column(insertable = false, updatable = false)
    private String type;

    public Livro(String titulo, String autores, String editora, Float preco) {
        this.titulo = titulo;
        this.autores = autores;
        this.editora = editora;
        this.preco = preco;
    }

    public Livro() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Id: " + id +
                "\nTítulo: " + titulo +
                "\nAutores: " + autores +
                "\nEditora: " + editora +
                "\nPreço: " + preco;
    }


}
