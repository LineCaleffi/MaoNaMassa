package br.com.nava.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nava.entity.UsuarioEntity;
import br.com.nava.services.UsuarioService;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioServ;
	
	@GetMapping() 
	public List<UsuarioEntity> getUsuarios() {
		return usuarioServ.getAll();
	}
	
	// Busca os dados que estão dentro do Banco de Dados
	@GetMapping("{id}")
	public UsuarioEntity getOne(@PathVariable Integer id) {
		return usuarioServ.getOne(id);
	}
	
	@PostMapping()
	public UsuarioEntity save(@RequestBody UsuarioEntity usuario) {
		return usuarioServ.save(usuario); // salva no BD
	}
	
	@PatchMapping("{id}")
	public UsuarioEntity update(@PathVariable Integer id, @RequestBody UsuarioEntity usuario) {
		return usuarioServ.update(id, usuario); // altera no bd
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		usuarioServ.delete(id); // deleta do bd
	}
}
