package br.com.squad8.desafio.livraria.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
