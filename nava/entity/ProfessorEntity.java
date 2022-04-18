package br.com.nava.entity;

import br.com.nava.entity.ProfessorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorEntity {
	private int id;
	private String nome, cpf, cep, rua;
	private int numero;
}
