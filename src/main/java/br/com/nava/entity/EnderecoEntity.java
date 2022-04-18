package br.com.nava.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.nava.dtos.EnderecoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ENDERECOS")
public class EnderecoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String rua;
	private int numero;
	private String cep;
	private String cidade;
	private String estado;
	
	/* nome do atributo da classe java que mapeia o endereço (Usuário)
	 * na classe UsuarioEntity*/
	@JsonIgnore
	@OneToOne(mappedBy = "endereco") 
	@ToString.Exclude
	private UsuarioEntity usuario;
	
	//retorna apenas os que eu quero que mostre ao usuario
	public EnderecoDTO toDTO() {
		ModelMapper mapper = new ModelMapper();
		
		EnderecoDTO dto = mapper.map(this, EnderecoDTO.class);
		
		return dto;
	}
}

//--------------- COMENTARIOS -------------- 

/* 
* @Data faz o papel de:
* Getter / Setter / ToString
* Required / ArgsConstructor
* EqualsAndHashCode / lombok.Value
*/

/*
* @OneToOne -> é usado quando o relacionamento é de UM para UM
* No caso não pode existir um id de endereço atrelado a mais de uma pessoa
*/
