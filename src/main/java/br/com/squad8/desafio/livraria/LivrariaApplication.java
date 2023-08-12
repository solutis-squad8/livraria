package br.com.squad8.desafio.livraria;

import br.com.squad8.desafio.livraria.business.LivrariaVirtual;
import br.com.squad8.desafio.livraria.domain.Eletronico;
import br.com.squad8.desafio.livraria.domain.Impresso;
import br.com.squad8.desafio.livraria.domain.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
public class LivrariaApplication implements CommandLineRunner {
	@Autowired
	private LivrariaVirtual livrariaVirtual;

	public static void main(String[] args) {
		SpringApplication.run(LivrariaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Menu de Opções:");
            System.out.println("1. Cadastrar livro");
            System.out.println("2. Realizar venda");
            System.out.println("3. Listar Livros");
            System.out.println("4. Listar Vendas ");
            System.out.println("5. Sair");
            System.out.print("Digite sua opção:");
        }
        int opcao;
        try {
            opcao = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("Opção inválida. Experimente digitar um número como opção");
        }

        switch (opcao) {
            case 1:
                livrariaVirtual.cadastrarLivro();
                break;
            case 2:
                // aqui vai ser a func de vendas.
                break;
            case 3:
                livrariaVirtual.listarLivros(); // talvez implementar a divisao qual é eletronico qual é impresso
                break;
            case 4:
                // aqui a func de listar vendas.
            case 5:
                System.out.println("Programa Encerrado! Volte Sempre");
                return;
            default:
                System.out.println("Opção inválida. Experimente digitar um número como opção");
        }


    }
}
