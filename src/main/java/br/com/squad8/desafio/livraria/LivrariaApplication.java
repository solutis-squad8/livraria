package br.com.squad8.desafio.livraria;

import br.com.squad8.desafio.livraria.tui.LivrariaMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LivrariaApplication implements CommandLineRunner {

    private final LivrariaMenu livrariaMenu;

    public LivrariaApplication(LivrariaMenu livrariaMenu) {
        this.livrariaMenu = livrariaMenu;
    }

    public static void main(String[] args) {
        SpringApplication.run(LivrariaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        livrariaMenu.iniciarMenu();
    }

}

