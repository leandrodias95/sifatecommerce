package br.com.sifatecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sifatecommerce.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
