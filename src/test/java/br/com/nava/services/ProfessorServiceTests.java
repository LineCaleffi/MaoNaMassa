package br.com.nava.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.nava.dtos.ProfessorDTO;
import br.com.nava.entity.ProfessorEntity;
import br.com.nava.repositories.ProfessorRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProfessorServiceTests {
	@Autowired
	private ProfessorService professorService;
	
	// a anotação @MockBean serve para sinalizar que iremos "MOCKAR(Simular)" a camada repository 
	@MockBean 
	private ProfessorRepository profRep;
	
	@Test
	void getAllTest() {
		List<ProfessorEntity> listaMockada = new ArrayList<ProfessorEntity>();

		// faz os Setters dos atributos
		ProfessorEntity professor = createValidProfessor();
		
		listaMockada.add(professor);
		
		// quando o repository for acionado, retorna a lista Mockada
		when(profRep.findAll()).thenReturn(listaMockada);
		
		List<ProfessorDTO> retorno = professorService.getAll();
		
		// compara a lista Mockada com o que esta no DTO
//		assertThat(listaMockada.get(0).getCep()).isEqualTo(retorno.get(0).getCep());
//		assertThat(listaMockada.get(0).getNome()).isEqualTo(retorno.get(0).getNome());
//		assertThat(listaMockada.get(0).getNumero()).isEqualTo(retorno.get(0).getNumero());
//		assertThat(listaMockada.get(0).getRua()).isEqualTo(retorno.get(0).getRua());
//		assertThat(listaMockada.get(0).getId()).isEqualTo(retorno.get(0).getId());
		isProfessorValid(retorno.get(0), listaMockada.get(0));	
	}// funcionando
	
	@Test //faz o teste de quando ele acha o Objeto no banco de dados
	void getOneWheFindObjectTest() {
		// faz os Setters dos atributos
		ProfessorEntity professor = createValidProfessor();
		
		Optional<ProfessorEntity> optional = Optional.of(professor);
		
		// quando o repository for acionado, retorna o optional
		when(profRep.findById(1)).thenReturn(optional);
		
		// execução
		ProfessorDTO obj = professorService.getOne(1);
		
		//método isProfessorValid -> validação 
		isProfessorValid(obj, professor);
	}// funcionando
	
	@Test //faz o teste de quando ele não acha o Objeto no banco de dados
	void getOneWhenNotFoundObjectTest() {
		// Optional.empty() -> simulando o caso de NÃO achar o registro no banco de dados
		Optional<ProfessorEntity> optional = Optional.empty();
				
		when ( profRep.findById(1) ).thenReturn( optional );
				
		// execução
		ProfessorDTO obj = professorService.getOne(1);
				
		// objeto com valores "padrão"
		ProfessorEntity professorEntidade = new ProfessorEntity();
				
		//método isProfessorValid -> validação
		isProfessorValid(obj, professorEntidade);
	} // funcionando
	
	@Test
	void saveTest() {
		// faz os Setters dos atributos
		ProfessorEntity professor = createValidProfessor();
		
		when(profRep.save(professor)).thenReturn(professor);
		
		ProfessorDTO professorSalvo = professorService.save(professor);
		
		//método isProfessorValid -> validação
		isProfessorValid(professorSalvo, professor);
	}// funcionando
	
	@Test //Cenário que acha os dados no bd
	void updateWhenFoundObjTest() {
		ProfessorEntity professor = createValidProfessor();
		
		Optional<ProfessorEntity> optional = Optional.of(professor);
		
		//mocks
		when(profRep.findById(professor.getId())).thenReturn(optional);
		when(profRep.save(professor)).thenReturn(professor);
		
		//execução
		ProfessorDTO professorAlterado = professorService.update(professor.getId(), professor);
		
		isProfessorValid(professorAlterado, professor);
	} // funcionando
	
	@Test
	void updateWhenNotFoundObjTest() {
		Optional<ProfessorEntity> optional = Optional.empty();
		ProfessorEntity professor = createValidProfessor();

		//mocks
		when(profRep.findById(1)).thenReturn(optional);
		
		//execução
		ProfessorDTO professorAlterado = professorService.update(1, professor);
		
		isProfessorValid(professorAlterado, new ProfessorEntity());
	}
	
	@Test
	void deleteTest() {
		assertDoesNotThrow(()-> professorService.delete(9));
		verify(profRep, times(1)).deleteById(9);
	}
	
	/******* FAZ A VALIDAÇÃO PARA O CÓDIGO NÃO FICAR REPETITIVO *******/
	private void isProfessorValid( ProfessorDTO obj, ProfessorEntity professorEntidade) {
		assertThat(obj.getCep()).isEqualTo(professorEntidade.getCep());
		assertThat(obj.getNome()).isEqualTo(professorEntidade.getNome());
		assertThat(obj.getNumero()).isEqualTo(professorEntidade.getNumero());
		assertThat(obj.getRua()).isEqualTo(professorEntidade.getRua());
		assertThat(obj.getId()).isEqualTo(professorEntidade.getId());
	}
	
	/******* FAZ OS SETTERS PARA O CÓDIGO NÃO FICAR REPETITIVO *******/
	private ProfessorEntity createValidProfessor() {
		ProfessorEntity professorEntidade = new ProfessorEntity();
		
		professorEntidade.setCep("04567895");
		professorEntidade.setNome("Professor Teste");
		professorEntidade.setNumero(3);
		professorEntidade.setRua("Rua de Teste");
		professorEntidade.setId(1);
		
		return professorEntidade;
	}
}
