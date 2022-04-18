package br.com.nava.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.nava.entity.ProfessorEntity;

@RestController
public class ProfessorController {
	private ArrayList<ProfessorEntity> listaProfessor = new ArrayList<ProfessorEntity>();
	private int contador = 1;
	
	@GetMapping("prof") // serve para consultar os dados
	public ArrayList<ProfessorEntity> getUsuarios() {
		return listaProfessor;
	}
	

	@GetMapping("prof/{id}") // serve para consultar os dados por ID
	public ProfessorEntity getOne(@PathVariable Integer id) {
		System.out.println(id);
		//array.length
		for(int i = 0; i<listaProfessor.size();i++){
			if(listaProfessor.get(i).getId() == id){
				return listaProfessor.get(i);
			}
		}
		return null;
	}
	
	@PostMapping("prof") // cria elementos dentro do array
	public ProfessorEntity save(@RequestBody ProfessorEntity prof) {
		System.out.println(prof);
		
		prof.setId(contador);
		listaProfessor.add(prof);
		contador++;
		
		return prof;
	}
	
	@PatchMapping("prof/{id}") // serve para fazer atualização de dados
	public ProfessorEntity update(@PathVariable Integer id, @RequestBody ProfessorEntity prof) {
		System.out.println(id);
		System.out.println(prof);

		for(int i = 0; i<listaProfessor.size();i++){
			if(listaProfessor.get(i).getId() == id){
				listaProfessor.get(i).setNome(prof.getNome()); 
				listaProfessor.get(i).setCpf(prof.getCpf());
				listaProfessor.get(i).setRua(prof.getRua());
				listaProfessor.get(i).setNumero(prof.getNumero());
				listaProfessor.get(i).setCep(prof.getCep());
				return listaProfessor.get(i); // se sim retorna a lista
			}
		}
		
		return null; // senão retorna nulo
	}
	
	
	@DeleteMapping("prof/{id}") // serve para remover um elemento por ID
	public void update(@PathVariable Integer id) {
		System.out.println(id);

		//percorre a lista do array
		for(int i = 0; i<listaProfessor.size();i++){
			if(listaProfessor.get(i).getId() == id){
				listaProfessor.remove(i); 
			}
		}
	}
}
