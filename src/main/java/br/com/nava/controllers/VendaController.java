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

import br.com.nava.dtos.VendaDTO;
import br.com.nava.services.VendaService;

@RestController
@RequestMapping("vendas")
public class VendaController {
	@Autowired
	private VendaService vendaServ;
	
	@GetMapping
	public ResponseEntity<List<VendaDTO>> getAll(){
		//return this.vendaServ.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(vendaServ.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<VendaDTO> getOne(@PathVariable int id) {
		//return vendaServ.getOne(id);]
		return ResponseEntity.status(HttpStatus.OK).body(vendaServ.getOne(id));
	}
	
	@PostMapping
	public ResponseEntity<VendaDTO> save(@RequestBody VendaDTO venda) {
		//return vendaServ.save(venda.toEntity());
		return ResponseEntity.status(HttpStatus.OK).body(vendaServ.save(venda.toEntity()));
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<VendaDTO> update(@PathVariable int id,
			@RequestBody VendaDTO venda) {
		//return vendaServ.save(venda.toEntity());
		return ResponseEntity.status(HttpStatus.OK).body(vendaServ.update(id,venda.toEntity()));
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {
		vendaServ.delete(id); // ja foi deletado no test
	}
}
