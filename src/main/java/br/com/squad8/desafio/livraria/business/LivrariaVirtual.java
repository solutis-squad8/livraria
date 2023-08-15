package br.com.squad8.desafio.livraria.business;

import br.com.squad8.desafio.livraria.domain.Eletronico;
import br.com.squad8.desafio.livraria.domain.Impresso;
import br.com.squad8.desafio.livraria.domain.Livro;
import br.com.squad8.desafio.livraria.domain.Venda;
import br.com.squad8.desafio.livraria.persistence.LivroRepository;
import br.com.squad8.desafio.livraria.persistence.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LivrariaVirtual {

    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private VendaRepository vendaRepository;

    private static final int MAX_IMPRESSOS = 10;
    private static final int MAX_ELETRONICOS = 20;
    private static final int MAX_VENDAS = 50;

    private List<Livro> impressos = new ArrayList<>();
    private List<Livro> eletronicos = new ArrayList<>();
    private List<Venda> vendas = new ArrayList<>();

    private Integer numImpressos = 0;
    private Integer numEletronicos = 0;
    private Integer numVendas = 0;

    public void cadastrarLivro (Livro livro){
        livroRepository.save(livro);
    }

    public Livro buscarLivroPorId(Long id){
        return livroRepository.findById(id).orElseThrow(() -> new NoSuchElementException(String.valueOf(id)));
    }

    public void listarLivrosImpressos(){
        List<Livro> listaLivros = livroRepository.findLivrosByType("Impresso");
        for (Livro livro: listaLivros) {
            System.out.println(livro);
        }
    }
    public void listarLivrosEletronicos(){
        List<Livro> listaLivros = livroRepository.findLivrosByType("Eletronico");
        for (Livro livro: listaLivros) {
            System.out.println(livro);
        }
    }

    public void listarLivros(){
        List<Livro> livros = livroRepository.findAll();
        for (Livro livro : livros){
            System.out.println(livro);

        }
    }

    public void realizarVenda(Venda venda){
        vendaRepository.save(venda);
    }

    public void sincronizarListas() {
        impressos = livroRepository.findLivrosByType("Impresso");
        numImpressos = impressos.size();

        eletronicos = livroRepository.findLivrosByType("Eletronico");
        numEletronicos = eletronicos.size();

        vendas = vendaRepository.findAll();
        numVendas = vendas.size();
    }
}
