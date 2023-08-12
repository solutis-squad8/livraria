package br.com.squad8.desafio.livraria;

import br.com.squad8.desafio.livraria.business.LivrariaVirtual;
import br.com.squad8.desafio.livraria.domain.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		Livro entity = new Livro("Harry Potter", "J.K. Tolkien", "500");
		System.out.println("Cadastrando Livro");

		livrariaVirtual.cadastrarLivro(entity);

		System.out.println("Buscando livro...");

		System.out.println("O livro que foi cadastrado e tem id 1 é: "+ livrariaVirtual.buscarLivroPorId(1L).getTitle());


	}

}
