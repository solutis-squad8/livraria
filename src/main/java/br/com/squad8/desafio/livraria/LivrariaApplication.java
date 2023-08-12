package br.com.squad8.desafio.livraria;

import br.com.squad8.desafio.livraria.business.LivrariaVirtual;
import br.com.squad8.desafio.livraria.domain.Eletronico;
import br.com.squad8.desafio.livraria.domain.Impresso;
import br.com.squad8.desafio.livraria.domain.Livro;
import br.com.squad8.desafio.livraria.domain.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		// Iremos instanciar a interface de texto aqui
		// Exemplo de utilizacao do codigo:

        System.out.println("Cadastrando Livros");
        Livro entity = new Impresso("Harry Potter 1", "J.K. Tolkien", "Fundamento", 500, 30.0, 30);
        Livro entity2 = new Eletronico("Harry Potter 2", "J.K. Tolkien", "Fundamento", 400, 300.0);
        Livro entity3 = new Impresso("Harry Potter 3", "J.K. Tolkien", "Fundamento", 300, 30.0, 30);
        Livro entity4 = new Impresso("Harry Potter 4", "J.K. Tolkien", "Fundamento", 50, 30.0, 30);
        Livro entity5 = new Eletronico("Harry Potter 5", "J.K. Tolkien", "Fundamento", 30, 300.0);
        Livro entity6 = new Eletronico("Harry Potter 6", "J.K. Tolkien", "Fundamento", 5500, 300.0);
        Livro entity7 = new Eletronico("Harry Potter 7", "J.K. Tolkien", "Fundamento", 100, 300.0);


		livrariaVirtual.cadastrarLivro(entity);
        livrariaVirtual.cadastrarLivro(entity2);
        livrariaVirtual.cadastrarLivro(entity3);
        livrariaVirtual.cadastrarLivro(entity4);
        livrariaVirtual.cadastrarLivro(entity5);
        livrariaVirtual.cadastrarLivro(entity6);
        livrariaVirtual.cadastrarLivro(entity7);

        Venda venda = new Venda(1L,"Cainan", 1, 200);
        Livro livro = livrariaVirtual.buscarLivroPorId(1L);

        System.out.println("-----Teste de Vendas-----");
        System.out.println(livro);
        venda.addLivro(livro,0);

        livrariaVirtual.realizarVenda(venda);

		System.out.println("Buscando livro...");

		System.out.println("O livro que foi cadastrado e tem id 1 é: "+ livrariaVirtual.buscarLivroPorId(1L).getTitulo());

        System.out.println("\n----Testes de Listagem----");

        livrariaVirtual.listarLivrosImpressos();
        System.out.println();
        livrariaVirtual.listarLivrosEletronicos();

        Scanner input = new Scanner(System.in);
        int a = input.nextInt();

	}

}
