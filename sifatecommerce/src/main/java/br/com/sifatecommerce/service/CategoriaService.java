package br.com.sifatecommerce.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.sifatecommerce.entity.Categoria;
import br.com.sifatecommerce.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

	private final CategoriaRepository  categoriaRepository;
	
	public Categoria salvarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Optional<Categoria> procurarCategoriaporId(Long id) {
		return categoriaRepository.findById(id);
	}

	public void deletarCategoria(Long id) {
	    categoriaRepository.findById(id).map(categoria -> {
	    	categoriaRepository.delete(categoria);
	        return categoria;
	    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada!")); // Lança exceção se não encontrado
	}

	
	public Categoria atualizarCategoria(Long id, Categoria categoriaAtualizada) {
	    return categoriaRepository.findById(id)
	        .map(categoria -> {
	            categoria.setNome(categoriaAtualizada.getNome());
	            return categoriaRepository.save(categoria); 
	        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada!"));
	}

	
}
