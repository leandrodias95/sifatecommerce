package br.com.sifatecommerce.dto;

import java.math.BigDecimal;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {


	private Long id; 
	
	@NotEmpty(message="{campo.produto.nome.requer}" )
	private String nome; 
	
	@NotNull(message="{campo.produto.preco.requer}")
	private BigDecimal preco;
	
	
	private Long categoriaId; //objeto categoria

}
