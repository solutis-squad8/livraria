package br.com.squad8.desafio.livraria.business;

import br.com.squad8.desafio.livraria.domain.Livro;
import br.com.squad8.desafio.livraria.persistence.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LivrariaVirtual {
    @Autowired
    LivroRepository livroRepository;

    private static final int MAX_IMPRESSOS = 10;
    private static final int MAX_ELETRONICOS = 20;
    private static final int MAX_VENDAS = 50;

    private int numImpressos = 0;
    private int numEletronicos = 0;
    private int numVendas = 0;

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
}
