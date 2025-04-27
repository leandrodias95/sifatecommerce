package br.com.sifatecommerce.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.sifatecommerce.dto.ProdutoDTO;
import br.com.sifatecommerce.entity.Categoria;
import br.com.sifatecommerce.entity.Produto;
import br.com.sifatecommerce.repository.CategoriaRepository;
import br.com.sifatecommerce.repository.ProdutoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarProdutoComSucesso() {
        
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Produto Teste");
        produtoDTO.setPreco(new BigDecimal("99.99"));
        produtoDTO.setCategoriaId(1L);

        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Categoria Teste");

        Produto produtoEntity = new Produto();
        produtoEntity.setNome(produtoDTO.getNome());
        produtoEntity.setPreco(produtoDTO.getPreco());
        produtoEntity.setCategoria(categoria);

        Produto produtoSalvo = new Produto();
        produtoSalvo.setId(1L);
        produtoSalvo.setNome(produtoDTO.getNome());
        produtoSalvo.setPreco(produtoDTO.getPreco());
        produtoSalvo.setCategoria(categoria);

        when(mapper.map(produtoDTO, Produto.class)).thenReturn(produtoEntity);
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(produtoRepository.save(produtoEntity)).thenReturn(produtoSalvo);
        when(mapper.map(produtoSalvo, ProdutoDTO.class)).thenReturn(new ProdutoDTO(1L, "Produto Teste", new BigDecimal("99.99"), 1L));

        
        ProdutoDTO resultado = produtoService.salvarProduto(produtoDTO);

        
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Produto Teste", resultado.getNome());
        assertEquals("1", resultado.getCategoriaId());
    }
}
