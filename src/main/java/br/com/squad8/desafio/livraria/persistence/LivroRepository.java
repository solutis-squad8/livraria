package br.com.squad8.desafio.livraria.persistence;

import br.com.squad8.desafio.livraria.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

}
