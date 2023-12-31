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
        livrariaVirtual.sincronizarListas();
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
                    menuListarVendas(); // aqui a func de listar vendas.
                    continue;
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
                System.out.println("Opção inválida! Tente novamente utilizando número com vírgula. Ex: 50,95");
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

            int opcao;
            try {
                opcao = input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Opção inválida! Tente novamente.");
                continue;
            }

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
                    System.out.printf("|%5s|%20s|%15s|%15s|%10s|%12s|%10s|%10s|\n", "Id", "Titulo", "Autores", "Editora", "Preço", "Tipo", "Tam/Frete", "Estoque");
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
                    System.out.println("\nValor total: R$" + String.format("%.2f", valor));
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
                        livrariaVirtual.decrementarEstoque(venda);
                        System.out.println("Seu pedido foi finalizado!");
                        return;
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

            int opcao;
            try {
                opcao = input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Opção inválida! Tente novamente.");
                continue;
            }
            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Id do livro: ");
                        Long id = input.nextLong();
                        Livro livro = livrariaVirtual.buscarLivroPorId(id);
                        System.out.println("----------------------------------------------------------------------------------------------------------");
                        System.out.printf("|%5s|%20s|%15s|%15s|%10s|%12s|%10s|%10s|\n", "Id", "Titulo", "Autores", "Editora", "Preço", "Tipo", "Tam/Frete", "Estoque");
                        System.out.println("----------------------------------------------------------------------------------------------------------");
                        System.out.println(livro);
                        System.out.println();
                        continue;
                    case 2:
                        List<Livro> listaLivrosImpressos = livrariaVirtual.listarLivrosImpressos();
                        if(listaLivrosImpressos.isEmpty()){
                            System.out.println("Nenhum livro impresso encontrado");
                            System.out.println();
                            continue;
                        }
                        System.out.println("----------------------------------------------------------------------------------------------------------");
                        System.out.printf("|%5s|%20s|%15s|%15s|%10s|%12s|%10s|%10s|\n", "Id", "Titulo", "Autores", "Editora", "Preço", "Tipo", "Frete", "Estoque");
                        System.out.println("----------------------------------------------------------------------------------------------------------");
                        for (Livro l : listaLivrosImpressos) {
                            System.out.println(l);
                            System.out.println();
                        }
                        continue;
                    case 3:
                        List<Livro> listaLivrosEletronicos = livrariaVirtual.listarLivrosEletronicos();
                        if(listaLivrosEletronicos.isEmpty()){
                            System.out.println("Nenhum livro eletrônico encontrado");
                            System.out.println();
                            continue;
                        }
                        System.out.println("-----------------------------------------------------------------------------------------------");
                        System.out.printf("|%5s|%20s|%15s|%15s|%10s|%12s|%10s|\n", "Id", "Titulo", "Autores", "Editora", "Preço", "Tipo", "Tamanho");
                        System.out.println("-----------------------------------------------------------------------------------------------");
                        for (Livro l : listaLivrosEletronicos) {
                            System.out.println(l);
                            System.out.println();
                        }
                        continue;
                    case 4:
                        List<Livro> listaLivros = livrariaVirtual.listarLivros();
                        if(listaLivros.isEmpty()){
                            System.out.println("Nenhum livro encontrado");
                            System.out.println();
                            continue;
                        }
                        System.out.println("----------------------------------------------------------------------------------------------------------");
                        System.out.printf("|%5s|%20s|%15s|%15s|%10s|%12s|%10s|%10s|\n", "Id", "Titulo", "Autores", "Editora", "Preço", "Tipo", "Tam/Frete", "Estoque");
                        System.out.println("----------------------------------------------------------------------------------------------------------");
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
            } catch (NoSuchElementException e) {
                System.out.println("Nenhum livro encontrado!");
            }
        }
    }

    private void menuListarVendas() {
        while (true) {
            System.out.println("--- Listando vendas ---");
            System.out.print("""
                    1. Venda por id
                    2. Venda por cliente
                    3. Todas as vendas
                    4. Retornar ao menu
                                        
                    Escolha uma opção:\s""");

            int opcao;
            try {
                opcao = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Opção inválida! Tente novamente.");
                continue;
            }

            if (opcao == 4) {
                System.out.println("Retornando ao menu!");
                break;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Id da venda: ");
                    long id;
                    try {
                        id = input.nextLong();
                    } catch (InputMismatchException e) {
                        input.nextLine();
                        System.out.println("Opção inválida! Tente novamente.");
                        continue;
                    }
                    Venda venda;
                    try {
                        venda = livrariaVirtual.buscarVendaPorId(id);
                    } catch (NoSuchElementException e) {
                        System.out.println("Nenhuma venda encontrada!");
                        System.out.println();
                        return;
                    }

                    System.out.println(venda); // Arrumar venda
                    System.out.println();
                    continue;
                case 2:
                    System.out.print("Nome do cliente: ");
                    String cliente = input.nextLine();
                    List<Venda> vendas = livrariaVirtual.buscarVendaPorCliente(cliente);
                    if (vendas.isEmpty()) {
                        System.out.println("Nenhuma venda encontrada!");
                        System.out.println();
                        continue;
                    }
                    for (Venda v : vendas) {
                        System.out.println(v);
                        System.out.println();
                    }
                    continue;
                case 3:
                    List<Venda> todasVendas = livrariaVirtual.listarVendas();
                    if (todasVendas.isEmpty()) {
                        System.out.println("Nenhuma venda encontrada!");
                        System.out.println();
                        return;
                    }
                    for (Venda v : todasVendas) {
                        System.out.println(v);
                        System.out.println();
                    }
                    continue;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    System.out.println();
            }

        }
    }
}

