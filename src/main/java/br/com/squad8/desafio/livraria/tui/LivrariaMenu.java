package br.com.squad8.desafio.livraria.tui;

import br.com.squad8.desafio.livraria.business.LivrariaVirtual;
import br.com.squad8.desafio.livraria.domain.Eletronico;
import br.com.squad8.desafio.livraria.domain.Impresso;
import br.com.squad8.desafio.livraria.domain.Livro;
import br.com.squad8.desafio.livraria.domain.Venda;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LivrariaMenu {

    private final LivrariaVirtual livrariaVirtual;

    public LivrariaMenu(LivrariaVirtual livrariaVirtual) {
        this.livrariaVirtual = livrariaVirtual;
    }

    private final Scanner input = new Scanner(System.in);

    public void iniciarMenu() {
        while (true) {
            System.out.println("""
                    --- Menu de Opções ---
                    1. Cadastrar livro
                    2. Realizar venda
                    3. Listar Livros
                    4. Listar Vendas
                    5. Sair
                    """);
            System.out.print("Digite sua opção: ");

            int opcao;
            try {
                opcao = input.nextInt();
                input.nextLine();
                System.out.println();
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Opção inválida. Experimente digitar um número como opção");
                System.out.println();
                continue;
            }

            switch (opcao) {
                case 1:
                    menuCadastrarLivro(); //metodo criado abaixo
                    continue;
                case 2:
                    menuRealizarVenda();// aqui vai ser a func de vendas.
                    continue;
                case 3:
                    menuListarLivros(); // metodo com opções de listarLivros abaixo
                    continue;
                case 4:
                    // aqui a func de listar vendas.
                case 5:
                    System.out.println("Programa Encerrado! Volte Sempre"); //Finaliza a aplicação
                    return;
                default:
                    System.out.println("Opção inválida. Experimente digitar um número entre 1 a 5 como opção");
                    System.out.println();
            }
        }
    }

    //Cadastra livros até o usuário voltar ao menu
    private void menuCadastrarLivro() {
        while (true) {
            System.out.println("--- Adicionando livro ---");
            System.out.print("""
                    1. Livro impresso
                    2. Livro eletrônico
                    3. Voltar para o menu
                                        
                    Escolha uma opção:\s""");

            int tipoLivro;
            try {
                tipoLivro = input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Opção inválida! Tente novamente.");
                continue;
            }
            if (tipoLivro == 3) {
                System.out.println("Retornando ao menu!");
                System.out.println();
                break;
            }

            try {
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
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida! Tente novamente utilizando vírgula. Ex: 50,95");
                input.nextLine();
            }
        }
    }

    //Realiza venda até o usuário voltar ao menu
    private void menuRealizarVenda() {
        System.out.println("--- Realizando venda ---");
        System.out.print("Nome do cliente: ");
        String nome = input.nextLine();
        float valor = 0;
        Venda venda = new Venda();
        while (true) {
            System.out.println();
            System.out.print("""
                    1. Adicionar livro ao carrinho
                    2. Remover livro do carrinho
                    3. Ir para o carrinho
                    4. Voltar para o menu
                                        
                    Escolha uma opção:\s""");

            int opcao = input.nextInt();

            if (opcao == 4) {
                System.out.println("Retornando ao menu!");
                System.out.println();
                break;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Id do livro: ");
                    Long id = input.nextLong();
                    Livro livro;
                    try {
                        livro = livrariaVirtual.buscarLivroPorId(id);
                    } catch (NoSuchElementException | IllegalArgumentException e) {
                        System.out.println("Nenhum livro encontrado!");
                        continue;
                    }
                    if (livro.getClass() == Impresso.class && ((Impresso) livro).getEstoque() == 0) {
                        System.out.println("Sem estoque do livro!");
                        continue;
                    }
                    System.out.println();
                    System.out.println(livro);
                    System.out.print("""
                                                        
                            1. Sim
                            2. Não
                            Adicionar:\s""");
                    opcao = input.nextInt();
                    if (opcao == 1) {
                        venda.addLivro(livro);
                        if (livro.getClass() == Impresso.class) {
                            valor += ((Impresso) livro).getFrete();
                            ((Impresso) livro).setEstoque(((Impresso) livro).getEstoque() - 1);
                        }
                        valor += livro.getPreco();
                    }
                    continue;
                case 2:
                    System.out.print("Id do livro para remoção: ");
                    Long idRemocao = input.nextLong();
                    venda.getLivros().removeIf(livroRemover -> livroRemover.getId().equals(idRemocao));
                    System.out.println("Livro removido!");
                    continue;
                case 3:
                    System.out.println("--- Carrinho ---");
                    venda.listarLivros();
                    System.out.print("""
                                                        
                            1. Sim
                            2. Não
                            Finalizar compra?\s""");
                    opcao = input.nextInt();
                    if (opcao == 1) {
                        if (venda.getLivros().isEmpty()) {
                            System.out.println("Não é possível realizar vendas sem cadastrar pelo menos um livro.\n");
                            continue;
                        }
                        venda.setCliente(nome);
                        venda.setValor(valor);
                        livrariaVirtual.realizarVenda(venda);
                        System.out.println("Seu pedido foi finalizado!");
                        break;
                    }
                    continue;
                default:
                    System.out.println("Opção inválida! Tente novamente!");
                    System.out.println();
            }
        }
    }

    //Opções para listar livros
    private void menuListarLivros() {
        while (true) {
            System.out.println("--- Listando livros ---");
            System.out.print("""
                    1. Livro por id
                    2. Livros impressos
                    3. Livros eletrônicos
                    4. Todos os livros
                    5. Voltar ao menu
                                        
                    Escolha uma opção:\s""");

            int tipoLivro = input.nextInt();
            switch (tipoLivro) {
                case 1:
                    System.out.print("Id do livro: ");
                    Long id = input.nextLong();
                    Livro livro = livrariaVirtual.buscarLivroPorId(id);
                    System.out.println(livro);
                    System.out.println();
                    continue;
                case 2:
                    List<Livro> listaLivrosImpressos = livrariaVirtual.listarLivrosImpressos();
                    for (Livro l : listaLivrosImpressos) {
                        System.out.println(l);
                        System.out.println();
                    }
                    continue;
                case 3:
                    List<Livro> listaLivrosEletronicos = livrariaVirtual.listarLivrosEletronicos();
                    for (Livro l : listaLivrosEletronicos) {
                        System.out.println(l);
                        System.out.println();
                    }
                    continue;
                case 4:
                    List<Livro> listaLivros = livrariaVirtual.listarLivros();
                    for (Livro l : listaLivros) {
                        System.out.println(l);
                        System.out.println();
                    }
                    continue;
                case 5:
                    System.out.println("Retornando ao menu!");
                    System.out.println();
                    return;
                default:
                    System.out.println("Opção inválida. Experimente digitar um número entre 1 a 5 como opção");
                    System.out.println();
            }
        }
    }
}

