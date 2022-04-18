package br.com.nava.controllers;

import java.util.List;

import javax.validation.Valid;

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

import br.com.nava.dtos.ProfessorDTO;
import br.com.nava.services.ProfessorService;

@RestController
@RequestMapping("professores")
public class ProfessorController {
	// injeção de dependencia - o sistema que define quando utilizar na memória
	@Autowired
	private ProfessorService profServ;

	@GetMapping()
	public ResponseEntity<List<ProfessorDTO>> getAll(){
		//return profServ.getAll(); // retorna do banco de dados quando pesquisa no postman
		return ResponseEntity.status(HttpStatus.OK).body(profServ.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProfessorDTO> getOne(@PathVariable int id) {
		//return profServ.getOne(id);
		return ResponseEntity.status(HttpStatus.OK).body(profServ.getOne(id));
	}
	
	@PostMapping() 
	public ResponseEntity<ProfessorDTO> save(@Valid @RequestBody ProfessorDTO professor) {
		//return profServ.save(professor.toEntity()); // pega o metodo do Service e salva no bd
		return ResponseEntity.status(HttpStatus.OK).body(profServ.save(professor.toEntity()));
	}

	@PatchMapping("{id}")
	public ResponseEntity<ProfessorDTO> update(@PathVariable int id, @RequestBody ProfessorDTO professor) {
		// return profServ.update(id, professor, listaProfessor);
		//return profServ.update(id, professor.toEntity()); //pega o metodo da classe service e altera no BD...
		return ResponseEntity.status(HttpStatus.OK).body(profServ.update(id,professor.toEntity()));
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {
		profServ.delete(id); // deleta do BD
	}
	
	@GetMapping(value="search-by-name/{name}")
	public ResponseEntity<List<ProfessorDTO>> searchByName(@PathVariable  String name){
		return ResponseEntity.ok().body(profServ.searchByName(name));
	}
}
