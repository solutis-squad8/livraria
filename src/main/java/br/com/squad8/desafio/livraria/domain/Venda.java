package br.com.squad8.desafio.livraria.domain;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Venda{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numero;

    private String cliente;

    private float valor;

    @OneToMany
    private List<Livro> livros;

    private static Long numVendas;

    public Venda(Long numero, String cliente, int numItens, float valor) {
        super();
        this.numero = numero;
        this.cliente = cliente;
        this.valor = valor;
        this.livros = new ArrayList<>();
    }

    public Venda() {
        super();
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

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Long getNumVendas() {
        return numVendas;
    }

    public void addLivro(Livro livro, int index){
        this.livros.add(livro);
    }
}
