package br.com.sifatecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sifatecommerce.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
