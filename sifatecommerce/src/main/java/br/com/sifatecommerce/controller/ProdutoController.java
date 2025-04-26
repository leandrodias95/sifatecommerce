package br.com.sifatecommerce.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Operation(summary = "Inserir produto")
    @PostMapping(value = "insert")
    public ResponseEntity<ProdutoDTO> salvarProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
        ProdutoDTO salvo = produtoService.salvarProduto(produtoDTO);
        return ResponseEntity.ok(salvo);
    }

    @Operation(summary = "Buscar produto por id")
    @GetMapping("{id}")
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable Long id) {
        ProdutoDTO produtoDTO = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok(produtoDTO);
    }

    @Operation(summary = "Atualizar produto")
    @PutMapping("{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoDTO produtoDTO) {
        ProdutoDTO atualizado = produtoService.atualizarProduto(id, produtoDTO);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Deletar produto")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Paginação de produtos")
    @GetMapping
    public Page<ProdutoDTO> listarProdutos(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        Sort sort = Sort.by(Sort.Direction.ASC, "nome");
        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);
        Page<ProdutoDTO> produtoPage = produtoRepository.findAll(pageRequest)
                .map(produtoService::convertToDTO);
        
        return produtoPage;
    }
}
