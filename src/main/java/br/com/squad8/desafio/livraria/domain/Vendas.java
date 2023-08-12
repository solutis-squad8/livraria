package br.com.squad8.desafio.livraria.domain;

public class Vendas {
    private Long numVendas;

    public Vendas() {
        this.numVendas++;
    }

    public Long getNumVendas() {
        return numVendas;
    }
}
