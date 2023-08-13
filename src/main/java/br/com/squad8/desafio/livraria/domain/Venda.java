package br.com.squad8.desafio.livraria.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Venda{

    @Id
    private Long numero;
    private String cliente;
    private Float valor;
    @OneToMany
    private List<Livro> livros;
    private static Long numVendas = 0L;

    public Venda(String cliente, Float valor) {
        this.cliente = cliente;
        this.valor = valor;
        this.livros = new ArrayList<>();
        numVendas++;
        this.numero = numVendas;
    }

    public Venda() {

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

    public Long getNumVendas() {
        return numVendas;
    }

    public void addLivro(Livro livro){
        this.livros.add(livro);
    }

    public void listarLivros() {
        for (Livro livro : livros) {
            System.out.println("Livro " + livros.indexOf(livro) + ": \n" + livro);
            System.out.println();
        }
    }
}
