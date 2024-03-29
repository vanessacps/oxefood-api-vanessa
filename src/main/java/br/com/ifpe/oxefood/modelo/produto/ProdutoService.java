package br.com.ifpe.oxefood.modelo.produto;


import java.time.LocalDate;
import java.util.List;
import java.lang.Long;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.util.entity.exception.ProdutoException;


@Service
public class ProdutoService {

     @Autowired
   private ProdutoRepository repository;

   @Transactional
   public Produto save(Produto produto) {

        if (produto.getValorUnitario() < 10) {
	          throw new ProdutoException(ProdutoException.MSG_VALOR_MINIMO_PRODUTO);
	      }
        produto.setHabilitado(Boolean.TRUE);
        produto.setVersao(1L);
        produto.setDataCriacao(LocalDate.now());

        return repository.save(produto);
       
  
        }

        public List<Produto> findAll() {
    
            return repository.findAll();
    }

    public Produto findById(Long id) {

        return repository.findById(id).get();
    }


    @Transactional
   public void update(Long id, Produto produtoAlterado) {

      Produto produto = repository.findById(id).get();
        produto.setCategoria(produtoAlterado.getCategoria());
      produto.setTitulo(produtoAlterado.getTitulo());
      produto.setCodigo(produtoAlterado.getCodigo());
      produto.setDescricao(produtoAlterado.getDescricao());
      produto.setValorUnitario(produtoAlterado.getValorUnitario());
      produto.setTempoEntregaMinino(produtoAlterado.getTempoEntregaMinino());
      produto.setTempoEntregaMaximo(produtoAlterado.getTempoEntregaMaximo());
      
	    
      produto.setVersao(produto.getVersao() + 1);
      repository.save(produto);
  }


  @Transactional
  public void delete (Long id ){

      Produto produto = repository.findById(id).get();
      produto.setHabilitado(Boolean.FALSE);
      produto.setVersao(produto.getVersao() + 1);

      repository.save(produto);



  }




}
