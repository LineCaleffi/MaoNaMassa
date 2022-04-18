package br.com.nava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.entity.ProdutoEntity;
import br.com.nava.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<ProdutoEntity> getAll(){
		return produtoRepository.findAll();
	}
	
	public ProdutoEntity getOne(int id) {
		return produtoRepository.findById(id).orElse(new ProdutoEntity());
	}
	
	public ProdutoEntity save(ProdutoEntity produto) {
		return this.produtoRepository.save(produto);
	}
	
	public ProdutoEntity update(int id, ProdutoEntity novoProduto) {
		
		Optional<ProdutoEntity> optional = produtoRepository.findById(id);
		
		if (optional.isPresent()) {
			ProdutoEntity produto = optional.get();
			
			produto.setDescricao( novoProduto.getDescricao() );
			produto.setNome( novoProduto.getNome() );			
			produto.setPreco( novoProduto.getPreco() );
			
			return produtoRepository.save(produto);
		}else {
			return new ProdutoEntity();
		}
	}
	
	public void delete(int id) {
		produtoRepository.deleteById(id);
	}
}
