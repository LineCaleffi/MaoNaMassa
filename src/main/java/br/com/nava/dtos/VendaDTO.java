package br.com.nava.dtos;

import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.nava.entity.ProdutoEntity;
import br.com.nava.entity.UsuarioEntity;
import br.com.nava.entity.VendaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaDTO {
	private int id;
	private float valorTotal;
	private UsuarioEntity usuario;
	private List<ProdutoEntity> produto;
	
	public VendaEntity toEntity() {
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, VendaEntity.class);
	}
}
