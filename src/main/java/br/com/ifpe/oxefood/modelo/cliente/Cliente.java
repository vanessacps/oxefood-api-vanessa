package br.com.ifpe.oxefood.modelo.cliente;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import br.com.ifpe.oxefood.modelo.Acesso.Usuario;
import br.com.ifpe.oxefood.util.entity.EntidadeAuditavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="Cliente")
@Where(clause ="habilitado = true") // so aparede os clientes habilitados para aparecer os excluidos é so coloca false
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends EntidadeAuditavel {

     @ManyToOne
   @JoinColumn(nullable = false)
   private Usuario usuario;

    @Fetch(FetchMode.SUBSELECT)

  

    @Column (nullable = false, length = 100)
     private String nome;

    @Column (name= "DT_NASC") 
    private LocalDate dataNascimento;

    @Column (unique = true)
    private String cpf;

    @Column
    private String foneCelular;

    @Column
    private String foneFixo;

}



