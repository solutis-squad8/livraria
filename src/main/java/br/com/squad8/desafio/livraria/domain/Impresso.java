package br.com.squad8.desafio.livraria.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Impresso extends Livro{

    private Double frete;

    private Integer estoque;

    public Impresso(String titulo, String autores, String editora, Float preco, Double frete, Integer estoque) {
        super(titulo, autores, editora, preco);
        this.frete = frete;
        this.estoque = estoque;
    }

    public Impresso() {

    }

    public Double getFrete() {
        return frete;
    }

    public void setFrete(Double frete) {
        this.frete = frete;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public void atualizarEstoque(){
        estoque--;
    }

    @Override
    public String toString() {
        return  super.toString() + String.format("%10s|%10s|", String.format("R$%.2f", frete), estoque);
    }
}
