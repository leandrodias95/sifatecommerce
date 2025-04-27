package br.com.sifatecommerce.controller;

import org.springframework.data.domain.Page;
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

import br.com.sifatecommerce.dto.ProdutoDTO;
import br.com.sifatecommerce.entity.Produto;
import br.com.sifatecommerce.repository.ProdutoRepository;
import br.com.sifatecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sifatecommerce/produto")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ProdutoRepository produtoRepository;

    @Operation(
    	    summary = "Inserir um novo produto",
    	    description = "Cria um novo produto no sistema. É necessário informar o nome, preço e o ID da categoria associada."
    	)
    @PostMapping(value = "insert")
    public ResponseEntity<ProdutoDTO> salvarProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
        ProdutoDTO salvo = produtoService.salvarProduto(produtoDTO);
        return ResponseEntity.ok(salvo);
    }

    @Operation(
    	    summary = "Buscar produto por ID",
    	    description = "Recupera as informações de um produto específico através do seu ID."
    	)
    @GetMapping("{id}")
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable Long id) {
        ProdutoDTO produtoDTO = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok(produtoDTO);
    }

    @Operation(
    	    summary = "Atualizar produto",
    	    description = "Atualiza os dados de um produto existente com base no seu ID. Os dados do produto devem ser enviados no corpo da requisição."
    	)
    @PutMapping("{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoDTO produtoDTO) {
        ProdutoDTO atualizado = produtoService.atualizarProduto(id, produtoDTO);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(
    	    summary = "Deletar produto",
    	    description = "Exclui um produto do sistema com base no ID informado."
    	)
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
    	    summary = "Listar produtos com paginação e filtro por categoria",
    	    description = "Lista produtos de forma paginada. Pode filtrar produtos por categoria se o ID da categoria for informado."
    	)
    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> listarProdutos(
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        Page<ProdutoDTO> produtos = produtoService.listarProdutos(categoriaId, page, pageSize);
        return ResponseEntity.ok(produtos);
    }

    
    @Operation(summary="Procurar por produto")
	@GetMapping("/procurar")
	public ResponseEntity<Page<Produto>> searchByAttributes(
	        @RequestParam String query,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int pageSize) {
	    Page<Produto> resultado = produtoService.procurarPorAtributos(query, page, pageSize);
	    return ResponseEntity.ok(resultado);
	}
}
