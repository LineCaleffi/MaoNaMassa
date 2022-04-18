package br.com.nava.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.nava.entity.ProfessorEntity;

@DataJpaTest // permite manipular o banco de dados com rollback(desfazer uma operação)
@ExtendWith(SpringExtension.class)
public class ProfessorRepositoryTests {
	@Autowired
	private ProfessorRepository profRep;
	
	@Autowired
	private TestEntityManager testEntityManager; //expecifica para test
	
	@Test
	void findByIdWhenFoundTest() {
		ProfessorEntity professorEntidade = createValidProfessor();
		
		// vai persistir a entidade no banco de dados para testar o findById
		// ao final do testes, esta entidade será deletada
		testEntityManager.persist(professorEntidade);
		
		// buscar a entidade no banco de dados para testar o findById
		// execução do findById
		Optional<ProfessorEntity> professor = profRep.findById( professorEntidade.getId() );
		
		// validando a respota - se o objeto encontrado não é nulo
		assertThat( professor ).isNotNull();
	}
	
	@Test
	void findByIdWhenNotFoundTest() {
		// buscar uma entidad na qual não existe no banco de dados
		Optional<ProfessorEntity> optional = profRep.findById(1);
		
		// temos que verificar se o opcional não possui valores, ou seja isPresent() possui valor falso
		assertThat(optional.isPresent()).isFalse();
	}
	
	@Test
	void findAllTest() {
		ProfessorEntity professorEntidade = createValidProfessor();
		
		// salvando temporariamente o professor no banco de dados
		testEntityManager.persist(professorEntidade);
		
		//execução		
		List<ProfessorEntity> professores = this.profRep.findAll();
		
		// verificar
		assertThat( professores.size() ).isEqualTo(1);
	}
	
	@Test
	void saveTest() {
		ProfessorEntity professor = createValidProfessor();
		
		// ao final do testes, esta entidade será deletada
		testEntityManager.persist(professor);
		
		ProfessorEntity professorSalvo = profRep.save(professor);
		
		assertThat(professorSalvo.getId()).isNotNull();
		assertThat(professorSalvo.getCep()).isEqualTo(professor.getCep());
		assertThat(professorSalvo.getNome()).isEqualTo(professor.getNome());
		assertThat(professorSalvo.getNumero()).isEqualTo(professor.getNumero());
		assertThat(professorSalvo.getRua()).isEqualTo(professor.getRua());
	}
	
	@Test
	void deleteTest() {
		ProfessorEntity professor = createValidProfessor();
		ProfessorEntity professorTemporario = testEntityManager.persist(professor);
		
		profRep.deleteById(professorTemporario.getId());
		
		Optional<ProfessorEntity> deletado = profRep.findById(professorTemporario.getId());
		
		// se não existe no banco de dados é pq já foi deletado
		assertThat(deletado.isPresent()).isFalse();
	}
	
	
	/******* FAZ OS SETTERS PARA O CÓDIGO NÃO FICAR REPETITIVO *******/
	private ProfessorEntity createValidProfessor() {
		ProfessorEntity professorEntidade = new ProfessorEntity();
		
		professorEntidade.setCep("04567895");
		professorEntidade.setNome("Professor Teste");
		professorEntidade.setNumero(3);
		professorEntidade.setRua("Rua de Teste");
		//professorEntidade.setId(1);
		
		return professorEntidade;
	}
}
