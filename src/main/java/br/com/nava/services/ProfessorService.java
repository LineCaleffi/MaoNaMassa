package br.com.nava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.dtos.ProfessorDTO;
import br.com.nava.entity.ProfessorEntity;
import br.com.nava.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	@Autowired
	private ProfessorRepository profRepository;
	
	public List<ProfessorDTO> getAll(){
		List<ProfessorEntity> lista = profRepository.findAll(); // retorna os dados do banco de dados
		List<ProfessorDTO> listaDTO = new ArrayList<>();
		//foreach
		// 1-) Tipo da variável de cada elemento da lista
		// 2-) nome local da variável 
		// - 3 lista com elementos a ser percorrido
		for(ProfessorEntity professorEntity : lista) {
//			ProfessorDTO dto = new ProfessorDTO();
//			dto.setId(professorEntity.getId());
//			dto.setCep(professorEntity.getCep());
//			dto.setCpf(professorEntity.getCpf());
//			dto.setNome(professorEntity.getNome());
//			dto.setRua(professorEntity.getRua());
//			dto.setNumero(professorEntity.getNumero());
//			
//			listaDTO.add(dto);
			listaDTO.add(professorEntity.toDTO());
		}
		return listaDTO;
	}
	
//	public List<ProfessorEntity> getAll(){
//		return profRepository.findAll(); // retorna os dados do banco de dados
//	}
	
//	public ProfessorEntity getOne(int id, ArrayList<ProfessorEntity> listaProfessor) {
//		// pega o metodo findIndex(id, listaUsuario) e atrela a variavel indice
//		int indice = findIndex(id, listaProfessor);
//		
//		// verifica se o indice é >= a 0 e retorna a lista ou nulo 
//		return (indice >= 0 ? listaProfessor.get(indice) : null); // operador ternário
//	}
	
	// Atrelando com o banco de dados - GET
	public ProfessorDTO getOne(int id) {
		Optional<ProfessorEntity> optional = profRepository.findById(id); // busca pela chave primária
		ProfessorEntity professor = optional.orElse( new ProfessorEntity());
		return professor.toDTO();
	}
	
	// Para salvar no banco de dados - POST
	public ProfessorDTO save(ProfessorEntity professor) {
		return profRepository.save(professor).toDTO();
	}

	public int findIndex(int id, ArrayList<ProfessorEntity> listaProfessor) {
		for (int i = 0; i < listaProfessor.size(); i++) {
			if (listaProfessor.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	// Altera dentro do banco de dados - PATCH
	public ProfessorDTO update(int id, ProfessorEntity professor){
		//primeiro passo é verificar se o registro existe no bd
		Optional<ProfessorEntity> optional = profRepository.findById(id);
		
		// se existir no BD faz o IF a seguir....
		if(optional.isPresent() == true) {
			ProfessorEntity professorBD = optional.get();
			professorBD.setNome(professor.getNome());
			professorBD.setCep(professor.getCep());
			professorBD.setCpf(professor.getCpf());
			professorBD.setRua(professor.getRua());
			professorBD.setNumero(professor.getNumero());
			
			return profRepository.save(professorBD).toDTO();
		} else {
			return new ProfessorEntity().toDTO();
		}
	}
	
	public void delete(int id) {
		try{
			profRepository.deleteById(id); // deleta do bd
		}catch(Exception e){
			System.out.println("Resgistro não existe");
		}

	}
	public List<ProfessorDTO> searchByName(String name){
		
		//List<ProfessorEntity> lista =  profRepository.findByNomeContains(name);
		List<ProfessorEntity> lista =  profRepository.searchByNome(name);
		
		List<ProfessorDTO> dtos = new ArrayList<>();
		
		for (ProfessorEntity professorEntity : lista) {
			dtos.add( professorEntity.toDTO() );
		}
		
		return dtos;
	}
}
