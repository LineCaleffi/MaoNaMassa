package br.com.nava.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import br.com.nava.entity.ProfessorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor

// mostra os dados sem o CPF
public class ProfessorDTO {
	private int id;
	
	@NotEmpty(message = "Preenchimento Obrigatório") 
	@NotNull(message="Preenchimento Obrigatório") 
	@Length(min = 3, max =80, message = "O número de caracteres deve ser entre 3 e 80")
	@Pattern( regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÃÉÈÍÏÓÔÕÖÚÇÑ ]*$", message = "É valido apenas caracteres")
	private String nome;
	
	private String rua;
	private String cep;
	private int numero;
	
	public ProfessorEntity toEntity() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, ProfessorEntity.class);
	}
}

/* ------------------- COMENTARIOS -------------------- */
/* 
 * @Data faz o papel de:
 * Getter
 * Setter
 * Required
 * ArgsConstructor
 * ToString
 * EqualsAndHashCode
 * lombok.Value
 * 
 * @NotEmpty -> não permite valor nulo e nem vazio
 * @NotNull -> não permite valor nulo mas permite valor vazio
 * 
 * qualquer um dos dois no metodo de POST deve ter @Valid antes do RequestBody
 */

