package br.com.nava.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import br.com.nava.entity.EnderecoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {
	private int id;
	
	@NotEmpty(message = "Preenchimento Obrigatório") 
	@NotNull(message="Preenchimento Obrigatório") 
	@Length(min = 3, max =80, message = "O número de caracteres deve ser entre 3 e 80")
	@Pattern( regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÃÉÈÍÏÓÔÕÖÚÇÑ]*$", message = "É valido apenas caracteres")
	private String rua;
	
	private int numero;
	private String cep;
	private String cidade;
	private String estado;
	
	public EnderecoEntity toEntity() {
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, EnderecoEntity.class);
	}
}
