package br.com.nava.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.dtos.VendaDTO;
import br.com.nava.entity.ProdutoEntity;
import br.com.nava.entity.VendaEntity;
import br.com.nava.repositories.ProdutoRepository;
import br.com.nava.repositories.VendaRepository;

@Service
public class VendaService {
	@Autowired
	private VendaRepository vendaRep;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<VendaDTO> getAll(){
		//return this.vendaRep.findAll();
		List<VendaEntity> lista = vendaRep.findAll();
		List<VendaDTO> listaDTO = new ArrayList<>();
		
		for(VendaEntity vendaEntity : lista) {
			listaDTO.add(vendaEntity.toDTO());
		}
		
		return listaDTO;
	}
	
	public VendaDTO getOne(int id) {
	//	return vendaRep.findById(id).orElse(new VendaEntity());
		Optional<VendaEntity> optional = vendaRep.findById(id);
		VendaEntity venda = optional.orElse(new VendaEntity());
		return venda.toDTO();
	}
	
	public VendaDTO save(VendaEntity venda) {
		// primeiro teremos que salvar a venda (para preencher a lista de vendas para cada produto)
		VendaEntity vendaSalva = vendaRep.save(venda);

		// depois teremos que alterar a lista de vendas para cada produtos
		// para cada produto da venda do body, temos que atualizar a venda salva no
		// banco
		if (venda.getProduto() != null) {
			List<ProdutoEntity> listaProdutos = venda.getProduto();
			// atualizando as vendas para cada produto acima
			for (int i = 0; i < listaProdutos.size(); i++) {
				// Arrays.asList(): converte um conjunto de objetos em uma lista
				listaProdutos.get(i).setVendas(Arrays.asList(vendaSalva));
			}
			// salvando as atualizações no banco de dados
			produtoRepository.saveAll(listaProdutos);
		}
		return vendaSalva.toDTO();
	}
	
	public VendaDTO update(int id, VendaEntity novaVenda) {
		
		Optional<VendaEntity> optional = vendaRep.findById(id);
		
		if (optional.isPresent()) {
			VendaEntity venda = optional.get();
			
			venda.setValorTotal( novaVenda.getValorTotal());
			
			return vendaRep.save(venda).toDTO();
		}else {
			return new VendaEntity().toDTO();
		}
	}
	
	public void delete(int id) {
		vendaRep.deleteById(id); // ja deletado no teste
	}
}

