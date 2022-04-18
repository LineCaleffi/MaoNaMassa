package br.com.nava.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.nava.dtos.ProfessorDTO;
import br.com.nava.entity.ProfessorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROFESSORES") // associa com a tabela PROFESSORES do banco de dados
public class ProfessorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto incremente
	private int id;
	private String nome, cpf, cep, rua;
	private int numero;
	
	//retorna apenas os que eu quero que mostre ao usuario (no caso sem CPF)
	public ProfessorDTO toDTO() {
		ModelMapper mapper = new ModelMapper();
				
		ProfessorDTO dto = mapper.map(this, ProfessorDTO.class);
			
		return dto;
	}
}
