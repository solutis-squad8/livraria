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
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Livro> livros;
    @Transient
    private static Long numVendas = 0L;

    public Venda(String cliente, Float valor) {
        this.cliente = cliente;
        this.valor = valor;
        this.livros = new ArrayList<>();
        numVendas++;
        this.numero = numVendas;
    }

    public Venda() {
        this.livros = new ArrayList<>();
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
        String table = String.format("|%5s|%20s|%15s|%15s|%10s|%12s|%10s|%10s|\n", "Id", "Titulo", "Autores", "Editora", "Preço", "Tipo", "Tam/Frete", "Estoque");

        for (Livro livro : livros) {
            System.out.println("--- Livro " + (livros.indexOf(livro) + 1)  + " ---\n" + table + livro);
            System.out.println();
        }
    }

    private String printarLivros(List<Livro> livros) {
        StringBuilder imprimir = new StringBuilder();
        for (Livro livro : livros) {
            imprimir.append(livro).append("\n");
        }
        return imprimir.toString();
    }

    @Override
    public String toString() {
        return "Número: " + numero +
                "\nCliente: " + cliente +
                "\nValor: R$" + String.format("%.2f", valor)+
                "\nLivros:\n" + String.format("|%5s|%20s|%15s|%15s|%10s|%12s|%10s|%10s|\n", "Id", "Titulo", "Autores", "Editora", "Preço", "Tipo", "Tam/Frete", "Estoque") +
                printarLivros(livros);
    }
}
