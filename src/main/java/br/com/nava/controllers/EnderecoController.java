package br.com.nava.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nava.dtos.EnderecoDTO;
import br.com.nava.services.EnderecoService;

@RestController
@RequestMapping("enderecos")
public class EnderecoController {
	@Autowired
	private EnderecoService enderecoService;
	
	//transformando em ResponseEntity
	@GetMapping
	public ResponseEntity<List<EnderecoDTO>> getAll(){
		//return enderecoService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.getAll());
	} 
	
	//transformando em ResponseEntity	
	@GetMapping("{id}")
	public ResponseEntity<EnderecoDTO> getOne(@PathVariable int id) {
		//return enderecoService.getOne(id);
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.getOne(id));
	}
	
	//transformando em ResponseEntity
	@PostMapping
	public ResponseEntity<EnderecoDTO> save(@RequestBody EnderecoDTO endereco) {
		//return enderecoService.save(endereco.toEntity());
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.save(endereco.toEntity()));
	}
	
	//transformando em ResponseEntity
	@PatchMapping(value = "{id}")
	public ResponseEntity<EnderecoDTO> update(@PathVariable int id, @RequestBody EnderecoDTO endereco) {
		//return enderecoService.update(id, endereco.toEntity());
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.update(id, endereco.toEntity()));
	}
	
	@DeleteMapping(value = "{id}")
	public void delete(@PathVariable int id) {
		enderecoService.delete(id); // já foi deletado
	}
}
