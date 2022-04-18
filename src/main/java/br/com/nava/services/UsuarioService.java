package br.com.nava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.entity.UsuarioEntity;
import br.com.nava.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
		
	public List<UsuarioEntity> getAll(){
		return usuarioRepository.findAll();// puxa os dados do bd
	}
	
	//--------- Atrelando a busca ao BD - get -----------
	public UsuarioEntity getOne(int id) {
		Optional<UsuarioEntity> optional = usuarioRepository.findById(id);
		UsuarioEntity usuario = optional.orElse(new UsuarioEntity());
		return usuario;
	}
	
	//--------- Para salvar no BD - POST ---------------
	public UsuarioEntity save(UsuarioEntity usuario) {
		return usuarioRepository.save(usuario);
	}
	
	//--------- Para alterar dentro do banco de dados --------
	public UsuarioEntity update(int id, UsuarioEntity usuario) {
		// verifica se o registro existe no bd
		Optional<UsuarioEntity> optional = usuarioRepository.findById(id);
		
		// se existir faz o IF 
		if(optional.isPresent() == true) {
			UsuarioEntity usuarioBD = optional.get();
			usuarioBD.setNome(usuario.getNome());
			usuarioBD.setEmail(usuario.getEmail());
			usuarioBD.setEndereco(usuario.getEndereco());
			usuarioBD.setVendas(usuario.getVendas());

			return usuarioRepository.save(usuarioBD);
		}else {
			return new UsuarioEntity();
		}
	}
	
	//--------- Deleta o ID do bd de dados -------------
	public void delete(int id) {
		usuarioRepository.deleteById(id); 
	}
}
