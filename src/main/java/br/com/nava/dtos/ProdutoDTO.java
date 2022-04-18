package br.com.nava.dtos;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.nava.entity.ProdutoEntity;
import br.com.nava.entity.VendaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
	private int id;
	private String nome;
	private String descricao;
	private float preco;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "VENDA_PRODUTO",
			joinColumns = @JoinColumn(name = "PRODUTO_ID"),
			inverseJoinColumns = @JoinColumn(name = "VENDA_ID"))
	private List<VendaEntity> vendas;
	
	public ProdutoEntity toEntity() {
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, ProdutoEntity.class);
	}
}
