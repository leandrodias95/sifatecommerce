package br.com.sifatecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.sifatecommerce.entity.Categoria;
import br.com.sifatecommerce.entity.Produto;
import br.com.sifatecommerce.repository.CategoriaRepository;
import br.com.sifatecommerce.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

	private final CategoriaRepository  categoriaRepository;
	private final ProdutoRepository produtoRepository;
	
	public Categoria salvarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Optional<Categoria> procurarCategoriaporId(Long id) {
		return categoriaRepository.findById(id);
	}

	public void  deletarCategoria(Long id) {
		List<Produto> produtos = produtoRepository.findByCategoriaId(id);
		if(!produtos.isEmpty()) {
			throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, "Há produtos vinculado a categoria"); 
		}
	    categoriaRepository.findById(id).map(categoria -> {
	    	categoriaRepository.delete(categoria);
	        return categoria;
	    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada!")); 
	}

	
	public Categoria atualizarCategoria(Long id, Categoria categoriaAtualizada) {
	    return categoriaRepository.findById(id)
	        .map(categoria -> {
	            categoria.setNome(categoriaAtualizada.getNome());
	            return categoriaRepository.save(categoria); 
	        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada!"));
	}

	public Page<Categoria> procurarPorAtributos(String query, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return categoriaRepository.findByNomeContainingIgnoreCase(query, pageRequest);
    }
	
}
