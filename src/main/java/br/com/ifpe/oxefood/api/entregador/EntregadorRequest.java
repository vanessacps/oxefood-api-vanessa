package br.com.ifpe.oxefood.api.entregador;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.entregador.Entregador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorRequest {


        
   @NotNull(message = "O Nome é de preenchimento obrigatório")
   @NotBlank(message = "O Nome é de preenchimento obrigatório")
   @Length(max = 100, message = "O Nome deverá ter no máximo {max} caracteres")
        private String nome;

        @Past(message = "A data não pode ser maior que o dia de hoje")
        @JsonFormat
        (pattern = "dd/MM/yyyy")
        private LocalDate dataNascimento;

        @NotNull(message = "O CPF é de preenchimento obrigatório")
   @NotBlank(message = "O CPF é de preenchimento obrigatório")
   @CPF
        private String cpf;

        private String rg;

        @Length(min = 8, max = 20, message = "O campo Fone tem que ter entre {min} e {max} caracteres")
        private String foneCelular;

        @Length(min = 8, max = 20, message = "O campo Fone tem que ter entre {min} e {max} caracteres")
        private String foneFixo;
        
        
        private Integer qtdEntregasRealizadas;

        private Double valorFrete;

        private String enderecoRua;

        private String enderecoNumero;

        private String enderecoBairro;

        private String enderecoCidade;

        private String enderecoUf;

        private String enderecoComplemento;

        private Boolean ativo;

        public Entregador build () {

            return Entregador.builder()
               .nome(nome) 
               .dataNascimento(dataNascimento)
               .cpf(cpf)
               .rg(rg)
               .foneCelular(foneCelular)
               .foneFixo(foneFixo)
               .qtdEntregasRealizadas(qtdEntregasRealizadas)
               .valorFrete(valorFrete)
               .enderecoRua(enderecoRua)
               .enderecoNumero(enderecoNumero)
               .enderecoBairro(enderecoBairro)
               .enderecoCidade(enderecoCidade)
               .enderecoUf(enderecoUf)
               .enderecoComplemento(enderecoComplemento)  
               //.ativo(ativo)            
               .build();
   }
        }




    
