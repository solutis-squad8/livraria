package br.com.squad8.desafio.livraria.business;

import br.com.squad8.desafio.livraria.domain.Livro;
import br.com.squad8.desafio.livraria.persistence.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LivrariaVirtual {
    @Autowired
    LivroRepository livroRepository;

    public void cadastrarLivro (Livro livro){
        livroRepository.save(livro);
    }

    public Livro buscarLivroPorId(Long id){
        return livroRepository.findById(id).orElseThrow(() -> new NoSuchElementException(String.valueOf(id)));
    }
}
