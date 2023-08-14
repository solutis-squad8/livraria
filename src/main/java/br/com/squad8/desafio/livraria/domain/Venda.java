package br.com.squad8.desafio.livraria.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Venda {

    @Id
    private Long numero;
    private String cliente;
    private Float valor;
    @ManyToMany
    private List<Livro> livros = new ArrayList<>();
    @Transient
    private static Long numVendas = 0L;

    public Venda(String cliente, Float valor) {
        this.cliente = cliente;
        this.valor = valor;
        numVendas++;
        this.numero = numVendas;
    }

    public Venda() {
        numVendas++;
        this.numero = numVendas;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public Long getNumVendas() {
        return numVendas;
    }

    public void addLivro(Livro livro) {
        this.livros.add(livro);
    }

    public void listarLivros() {
        for (Livro livro : livros) {
            System.out.println("Livro " + (livros.indexOf(livro) + 1)  + ": \n" + livro);
            System.out.println();
        }
    }
}
