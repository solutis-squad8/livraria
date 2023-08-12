package br.com.squad8.desafio.livraria.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Impresso extends Livro{
    @Column
    private Double frete;
    @Column
    private int estoque;

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
        return "Impresso{" +
                "frete=" + frete +
                ", estoque=" + estoque +
                '}';
    }
}
