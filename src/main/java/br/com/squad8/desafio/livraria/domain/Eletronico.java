package br.com.squad8.desafio.livraria.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Eletronico extends Livro{


    private Double tamanho;

    public Eletronico(String titulo, String autores, String editora, Float preco, Double tamanho) {
        super(titulo, autores, editora, preco);
        this.tamanho = tamanho;
    }

    public Eletronico() {

    }

    public Double getTamanho() {
        return tamanho;
    }

    public void setTamanho(Double tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTipo: eletrônico" +
                "\nTamanho: " + tamanho + "KB";
    }
}
