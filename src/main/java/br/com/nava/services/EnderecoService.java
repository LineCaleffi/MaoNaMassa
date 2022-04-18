package br.com.nava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.dtos.EnderecoDTO;
import br.com.nava.entity.EnderecoEntity;
import br.com.nava.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<EnderecoDTO> getAll(){
		List<EnderecoEntity> lista = enderecoRepository.findAll(); // retorna os dados do banco de dados
		List<EnderecoDTO> listaDTO = new ArrayList<>();
		//foreach
		// 1-) Tipo da variável de cada elemento da lista
		// 2-) nome local da variável 
		// - 3 lista com elementos a ser percorrido
		for(EnderecoEntity enderecoEntity : lista) {
			listaDTO.add(enderecoEntity.toDTO());
		}
		return listaDTO;
	}
	
	public EnderecoDTO getOne(int id) {
		return enderecoRepository.findById(id).orElse(new EnderecoEntity()).toDTO();
	}
	
	public EnderecoDTO save(EnderecoEntity endereco) {		
		return enderecoRepository.save(endereco).toDTO();
	}
	
	public EnderecoDTO update(int id, EnderecoEntity novoEndereco) {
		
		//verificar se o registro existe
		
		Optional<EnderecoEntity> enderecoBD = enderecoRepository.findById(id);
		
		// se o registro existir
		if (enderecoBD.isPresent()) {
			EnderecoEntity obj = enderecoBD.get();
			
			obj.setCep( novoEndereco.getCep() );
			obj.setCidade( novoEndereco.getCidade() );
			obj.setEstado(novoEndereco.getEstado());
			obj.setNumero(novoEndereco.getNumero());
			obj.setRua( novoEndereco.getRua());
			obj.setUsuario( novoEndereco.getUsuario()); 
			
			return enderecoRepository.save(obj).toDTO();						
		}
		else {
			return new EnderecoEntity().toDTO();
		}
	}
	
	public void delete(int id) {
		enderecoRepository.deleteById(id);
	}
}
