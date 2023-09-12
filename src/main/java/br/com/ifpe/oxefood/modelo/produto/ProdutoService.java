package br.com.ifpe.oxefood.modelo.produto;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProdutoService {

     @Autowired
   private ProdutoRepository repository;

   @Transactional
   public Produto save(Produto produto) {

       
       produto.setVersao(1L);
       
       return repository.save(produto);


    
}

}
