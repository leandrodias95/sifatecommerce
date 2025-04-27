package br.com.sifatecommerce.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sifatecommerce.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("SELECT p FROM Produto p WHERE p.categoria.id = :id")
	List<Produto> findByCategoriaId(@Param("id")Long id);
	
	Page<Produto> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
	
	@Query("SELECT p FROM Produto p WHERE p.categoria.id = :id")
	Page<Produto> findByCategoriaIdWithPage(@Param("id") Long id, Pageable pageable);


	
			
}
