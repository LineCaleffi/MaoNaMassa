package br.com.nava.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import br.com.nava.entity.EnderecoEntity;

@Repository
public interface EnderecoRepository extends JpaRepositoryImplementation<EnderecoEntity, Integer>{
	// JpaRepository <T, ID> 
	// T= a entidade que vai persistir 
	// ID -> tipo da chave primária da entidade
}
