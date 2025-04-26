package br.com.sifatecommerce.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", initialValue = 1, allocationSize = 1)
public class Produto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
	private Long id; 
	
	@Column(length = 50, unique = false, nullable = false)
	@NotEmpty(message="{campo.produto.nome.requer}" )
	private String nome; 
	
	@Column(nullable = false, precision = 10, scale = 2)
	@NotNull(message="{campo.produto.preco.requer}")
	private BigDecimal preco;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="categoria_id", foreignKey= @ForeignKey (name= "fk_categoria"),  nullable = false)
	private Categoria categoria;

}
