package br.com.sifatecommerce.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sifatecommerce.entity.Categoria;
import br.com.sifatecommerce.repository.CategoriaRepository;
import br.com.sifatecommerce.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sifatecommerce/categoria")
public class CategoriaController {
	
	private final CategoriaService categoriaService;
	private final CategoriaRepository categoriaRepository;


	@Operation(summary = "inserir categoria")
	@PostMapping(value = "insert")
	public ResponseEntity<Categoria> salvarCategoria(@RequestBody @Valid Categoria categoria) {
		Categoria salvarCategoria = categoriaService.salvarCategoria(categoria);
		return ResponseEntity.ok(salvarCategoria);

	}

	@Operation(summary = "Procura categoria por id")
	@GetMapping("{id}")
	public ResponseEntity<Categoria> procurarcategoriaPorId(@PathVariable Long id) {
		Optional<Categoria> optionalCategoria = categoriaService.procurarCategoriaporId(id);
		return optionalCategoria.map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	} 
	
	@Operation(summary = "Deleta Categoria")
	@DeleteMapping("{id}")
	public ResponseEntity<Categoria> deletePerson(@PathVariable Long id){
		categoriaService.deletarCategoria(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Atualiza categoria por id")
	@PutMapping("{id}")
	public ResponseEntity<Categoria> updatePerson(@PathVariable Long id, @RequestBody @Valid Categoria categoriaAtualizar) {
	    Categoria categoriaAtualizada = categoriaService.atualizarCategoria(id, categoriaAtualizar); 
	    return ResponseEntity.ok(categoriaAtualizada); 
	}

	
	
	@Operation(summary="Paginação de categoria")
	@GetMapping
	public Page<Categoria>list(
			@RequestParam(defaultValue = "0")Integer page,
			@RequestParam(defaultValue = "10") Integer pageSize
			){
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		PageRequest pageRequest = PageRequest.of(page, pageSize, sort);
		return categoriaRepository.findAll(pageRequest);
	}


}
