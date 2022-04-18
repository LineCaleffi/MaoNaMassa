package br.com.nava.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.com.nava.entity.ProfessorEntity;
import br.com.nava.entity.UsuarioEntity;

@Service
public class UsuarioService {
	public int findIndex(int id, ArrayList<UsuarioEntity> listaUsuario) {
		for(int i=0; i< listaUsuario.size(); i++) {
			if(listaUsuario.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}

	// GET ONE
	public UsuarioEntity getOne(int id, ArrayList<UsuarioEntity> listaUsuario) {
		// pega o metodo findIndex(id, listaUsuario) e atrela a variavel indice
		int indice = findIndex(id, listaUsuario);
		// verifica se o indice é >= a 0 e retorna a lista ou nulo 
		return (indice >= 0 ? listaUsuario.get(indice) : null); // operador ternário - retorna o indice, a lista
	}
	
	
	//para atualizar
	public UsuarioEntity update(int id,UsuarioEntity usuario,ArrayList<UsuarioEntity> listaUsuario) {
		int indice = findIndex(id, listaUsuario);
		//percorre a lista do array
		if (indice >= 0) {
			listaUsuario.get(indice).setNome( usuario.getNome() );
			listaUsuario.get(indice).setEmail( usuario.getEmail() );

			return listaUsuario.get(indice); // retorna a lista
		}
		
		return null;
	}
	
	public void delete(int id,ArrayList<UsuarioEntity> listaUsuario) {
		int indice = findIndex(id,listaUsuario);
		if (indice >= 0) {
			listaUsuario.remove(indice);
		}
	}
}
