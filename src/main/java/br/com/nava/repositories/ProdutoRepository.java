package br.com.nava.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import br.com.nava.entity.ProdutoEntity;

@Repository
public interface ProdutoRepository extends JpaRepositoryImplementation<ProdutoEntity, Integer> {
	// JpaRepository <T, ID> 
	// T= a entidade que vai persistir 
	// ID -> tipo da chave primária da entidade
}
