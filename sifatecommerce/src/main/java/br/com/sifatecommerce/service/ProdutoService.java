package br.com.sifatecommerce.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.sifatecommerce.dto.ProdutoDTO;
import br.com.sifatecommerce.entity.Categoria;
import br.com.sifatecommerce.entity.Produto;
import br.com.sifatecommerce.repository.CategoriaRepository;
import br.com.sifatecommerce.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

	private final ModelMapper mapper; 
	private final ProdutoRepository produtoRepository; 
	private final CategoriaRepository categoriaRepository; 


	@Transactional
	public ProdutoDTO salvarProduto(ProdutoDTO produtoDTO) {
	    Produto produto = convertToEntity(produtoDTO);
	    Produto salvoProduto = produtoRepository.save(produto);
	    return convertToDTO(salvoProduto);
	}

	@Transactional
	public ProdutoDTO atualizarProduto(Long id, ProdutoDTO produtoDTO) {
	    Produto produtoExistente = produtoRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
	    Produto produtoAtualizado = convertToEntity(produtoDTO);
	    produtoAtualizado.setId(id); 

	    Produto salvo = produtoRepository.save(produtoAtualizado);

	    return convertToDTO(salvo);
	}

	@Transactional
	public void deletarProduto(Long id) {
	    Produto produtoExistente = produtoRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
	    produtoRepository.delete(produtoExistente);
	}

	public ProdutoDTO buscarProdutoPorId(Long id) {
	    Produto produto = produtoRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));

	    return convertToDTO(produto);
	}
		
	
	
	public Page<ProdutoDTO> listarProdutos(Long categoriaId, int page, int pageSize) {
	    PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, "nome"));

	    Page<Produto> produtos;

	    if (categoriaId != null) {
	        produtos = produtoRepository.findByCategoriaIdWithPage(categoriaId, pageRequest);
	    } else {
	        produtos = produtoRepository.findAll(pageRequest);
	    }

	    return produtos.map(this::convertToDTO);
	}

	
	
	
	public Page<Produto> procurarPorAtributos(String query, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return produtoRepository.findByNomeContainingIgnoreCase(query, pageRequest);
    }

		public ProdutoDTO convertToDTO(Produto produto) {
			ProdutoDTO dto = new ProdutoDTO();
			dto = mapper.map(produto, ProdutoDTO.class);
			dto.setCategoriaId(produto.getCategoria().getId());
			return dto;

		}
		
		public Produto convertToEntity(ProdutoDTO dto) {
		    Produto produto = mapper.map(dto, Produto.class);
		    if (dto.getCategoriaId() != null) {
				Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
						.orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada!"));
				produto.setCategoria(categoria);
			} 
		    return produto;
		}	
	
		
}
