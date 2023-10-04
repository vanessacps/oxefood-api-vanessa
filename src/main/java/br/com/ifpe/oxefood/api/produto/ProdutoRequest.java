package br.com.ifpe.oxefood.api.produto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.ifpe.oxefood.modelo.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest {

    private Long idCategoria;


    @NotNull(message = "O Titulo é de preenchimento obrigatório")
   @NotBlank(message = "O Titulo é de preenchimento obrigatório")
   @Length(max = 50, message = "O Titulo deverá ter no máximo {max} caracteres")
    private String titulo;

    @NotNull(message = "O Codigo é de preenchimento obrigatório")
   @NotBlank(message = "O Codigo é de preenchimento obrigatório")
   @Length(min = 4,max= 8, message = "O campo codigo tem que ter entre {min} e {max} caracteres")
    private String codigo;


     @NotNull(message = "A descrição é de preenchimento obrigatório")
   @NotBlank(message = "A descrição é de preenchimento obrigatório")
   @Length(max = 100, message = "A Descrição deverá ter no máximo {max} caracteres")
    private String descricao;

    private Double valorUnitario;

    private Integer tempoEntregaMinino;

    private Integer tempoEntregaMaximo;

    public Produto build()  {

            return Produto.builder()
               .titulo(titulo)
               .codigo(codigo)
               .descricao(descricao)
               .valorUnitario(valorUnitario)
               .tempoEntregaMinino(tempoEntregaMinino)
               .tempoEntregaMaximo(tempoEntregaMaximo)
               .build();


    }

    
}
