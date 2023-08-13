package br.com.squad8.desafio.livraria.tui;

import br.com.squad8.desafio.livraria.business.LivrariaVirtual;
import br.com.squad8.desafio.livraria.domain.Eletronico;
import br.com.squad8.desafio.livraria.domain.Impresso;
import br.com.squad8.desafio.livraria.domain.Livro;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class LivrariaMenu {

    private static LivrariaVirtual livrariaVirtual;

    public LivrariaMenu(LivrariaVirtual livrariaVirtual) {
        LivrariaMenu.livrariaVirtual = livrariaVirtual;
    }

    public static void iniciarMenu() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    Menu de Opções:
                    1. Cadastrar livro
                    2. Realizar venda
                    3. Listar Livros
                    4. Listar Vendas
                    5. Sair
                    """);
            System.out.println();
            System.out.print("Digite sua opção: ");

            int opcao;
            try {
                opcao = input.nextInt();
                input.nextLine();
                System.out.println();
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Opção inválida. Experimente digitar um número como opção");
                break;
            }

            switch (opcao) {
                case 1:
                    menuCadastrarLivro(input); //metodo criado abaixo
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

    //Cadastra livros até o usuário voltar ao menu
    private static void menuCadastrarLivro(Scanner input) {
        while (true) {
            System.out.println("Adicionando livro:");
            System.out.print("""
                    1. Livro impresso
                    2. Livro eletrônico
                    3. Voltar para o menu
                                        
                    Escolha uma opção:\s""");

            int tipoLivro = input.nextInt();
            if (tipoLivro == 3) {
                System.out.println("Retornando ao menu!");
                System.out.println();
                break;
            }
            System.out.println();
            input.nextLine();
            System.out.print("Título: ");
            String titulo = input.nextLine();
            System.out.print("Autores: ");
            String autores = input.nextLine();
            System.out.print("Editora: ");
            String editora = input.nextLine();
            System.out.print("Preço: ");
            Float preco = input.nextFloat();
            Livro livro;
            if (tipoLivro == 1) {
                System.out.print("Valor do frete: ");
                Double frete = input.nextDouble();
                System.out.print("Estoque: ");
                Integer estoque = input.nextInt();
                livro = new Impresso(titulo, autores, editora, preco, frete, estoque);
            } else if (tipoLivro == 2) {
                System.out.print("Tamanho: ");
                Double tamanho = input.nextDouble();
                livro = new Eletronico(titulo, autores, editora, preco, tamanho);
            } else {
                System.out.println("Tipo inválido! Tente novamente!");
                System.out.println();
                continue;
            }
            livrariaVirtual.cadastrarLivro(livro);
            System.out.println();
        }
    }
}
