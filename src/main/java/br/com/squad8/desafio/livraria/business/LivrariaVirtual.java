package br.com.squad8.desafio.livraria.business;

import br.com.squad8.desafio.livraria.domain.Eletronico;
import br.com.squad8.desafio.livraria.domain.Impresso;
import br.com.squad8.desafio.livraria.domain.Livro;
import br.com.squad8.desafio.livraria.domain.Venda;
import br.com.squad8.desafio.livraria.persistence.LivroRepository;
import br.com.squad8.desafio.livraria.persistence.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LivrariaVirtual {

    private static final int MAX_IMPRESSOS = 10;
    private static final int MAX_ELETRONICOS = 20;
    private static final int MAX_VENDAS = 50;

    private List<Livro> impressos = new ArrayList<>();
    private List<Livro> eletronicos = new ArrayList<>();
    private List<Venda> vendas = new ArrayList<>();

    private static Integer numImpressos = 0;
    private static Integer numEletronicos = 0;
    private static Integer numVendas = 0;

    private final LivroRepository livroRepository;
    private final VendaRepository vendaRepository;

    public LivrariaVirtual(LivroRepository livroRepository, VendaRepository vendaRepository) {
        this.livroRepository = livroRepository;
        this.vendaRepository = vendaRepository;

    }

    public List<Livro> getImpressos() {
        return impressos;
    }

    public void setImpressos(List<Livro> impressos) {
        this.impressos = impressos;
    }

    public List<Livro> getEletronicos() {
        return eletronicos;
    }

    public void setEletronicos(List<Livro> eletronicos) {
        this.eletronicos = eletronicos;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public LivroRepository getLivroRepository() {
        return livroRepository;
    }

    public VendaRepository getVendaRepository() {
        return vendaRepository;
    }

    public Integer getNumImpressos() {
        return numImpressos;
    }

    public void setNumImpressos(Integer numImpressos) {
        LivrariaVirtual.numImpressos = numImpressos;
    }

    public Integer getNumEletronicos() {
        return numEletronicos;
    }

    public static void setNumEletronicos(Integer numEletronicos) {
        LivrariaVirtual.numEletronicos = numEletronicos;
    }

    public Integer getNumVendas() {
        return numVendas;
    }

    public void setNumVendas(Integer numVendas) {
        LivrariaVirtual.numVendas = numVendas;
    }

    public void cadastrarLivro (Livro livro){
        if (livro.getClass() == Impresso.class) {
            if (numImpressos < MAX_IMPRESSOS) {
                livroRepository.save(livro);
                impressos.add((Impresso) livro);
                numImpressos++;
            }
            else {
                System.out.println("Limite de livros impressos excedido!");
            }
        }
        else {
            if (numImpressos < MAX_ELETRONICOS) {
                livroRepository.save(livro);
                eletronicos.add((Eletronico) livro);
                numImpressos++;
            }
            else {
                System.out.println("Limite de livros eletrônicos excedido!");
            }
        }
    }

    public Livro buscarLivroPorId(Long id){
        return livroRepository.findById(id).orElseThrow(() -> new NoSuchElementException(String.valueOf(id)));
    }

    public List<Livro> listarLivrosImpressos(){
        return livroRepository.findLivrosByType("Impresso");
    }
    public List<Livro> listarLivrosEletronicos(){
        return livroRepository.findLivrosByType("Eletronico");
    }

    public List<Livro> listarLivros(){
        return livroRepository.findAll();
    }

    public Venda buscarVendaPorId(Long id){
        return vendaRepository.findById(id).orElseThrow(() -> new NoSuchElementException(String.valueOf(id)));
    }

    public List<Venda> buscarVendaPorCliente(String cliente) {
        return vendaRepository.findVendasByClienteContains(cliente);
    }

    public List<Venda> listarVendas(){
        return vendaRepository.findAll();
    }

    public void realizarVenda(Venda venda){
        if(numVendas < MAX_VENDAS) {
            vendas.add(venda);
            vendaRepository.save(venda);
        }
        else {
            System.out.println("Limite de vendas excedido!");
        }
    }

    public void sincronizarListas() {
        impressos = livroRepository.findLivrosByType("Impresso");
        numImpressos = impressos.size();

        eletronicos = livroRepository.findLivrosByType("Eletronico");
        numEletronicos = eletronicos.size();

        vendas = vendaRepository.findAll();
        numVendas = vendas.size();
    }

    public void decrementarEstoque(Venda venda){
        for (Livro l : venda.getLivros()) {
            if (l.getClass() == Impresso.class) {
                ((Impresso) l).setEstoque(((Impresso) l).getEstoque()-1);
            }
            livroRepository.saveAll(venda.getLivros());
        }
    }
}
