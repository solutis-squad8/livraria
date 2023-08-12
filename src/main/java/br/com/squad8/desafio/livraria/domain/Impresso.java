package br.com.squad8.desafio.livraria.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Impresso extends Livro{

    private Double frete;


    private int estoque;

    public Impresso(String titulo, String autores, String editora, float preco, Double frete, int estoque) {
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

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public void atualizarEstoque(){
        estoque--;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "tipo=impresso" +
                ", frete=" + frete +
                ", estoque=" + estoque +
                '}';
    }
}
