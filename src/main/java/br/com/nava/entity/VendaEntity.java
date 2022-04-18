package br.com.nava.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.nava.dtos.VendaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="VENDAS")
public class VendaEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "VALOR_TOTAL")
	private float valorTotal;
	
	@ManyToOne
	@JoinColumn(name = "USUARIO_ID")
	private UsuarioEntity usuario;
	
	@ManyToMany(mappedBy = "vendas")
	private List<ProdutoEntity> produto;

	public VendaDTO toDTO() {
		ModelMapper mapper = new ModelMapper();
		
		VendaDTO dto = mapper.map(this, VendaDTO.class);
		
		return dto;
	}
}

// --------------- COMENTARIOS -------------- 

/*
 * @ManyToOne é usado quando o relacionamento é de MUITOS para UM
 * No caso, muitas vendas pode ter apenas um usuário
 */

/*
 * @ManyToMany é usado quando o relacionamento é de MUITOS para MUITOS
 * No caso, muitas vendas pode ter muitos produtos
 */

/*
 * @JoinColumn é utilizado para nomearmos a coluna que possui a chave-estrangeira
 * No caso, USUARIO_ID
 */

/*
 * @Id é utilizado para declarar qual a chave primária 
 * No caso, id
 */

/* @GeneratedValue é utilizado para declarar que é unico */




