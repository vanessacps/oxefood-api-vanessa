package br.com.ifpe.oxefood.modelo.cliente;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.lang.Long;

@Service
public class ClienteService  {

    @Autowired
   private ClienteRepository repository;

   

 
   @Transactional
   public Cliente save(Cliente cliente) {

       cliente.setHabilitado(Boolean.TRUE);
       cliente.setVersao(1L);
       cliente.setDataCriacao(LocalDate.now());
       return repository.save(cliente);
   }

   public List<Cliente> findAll() {
  
    return repository.findAll();
}

public Cliente findById(Long id) {

    return repository.findById(id).get();
}

@Transactional
public void update(Long id, Cliente clienteAlterado) {

   Cliente cliente = repository.findById(id).get();
   cliente.setNome(clienteAlterado.getNome());
   cliente.setDataNascimento(clienteAlterado.getDataNascimento());
   cliente.setCpf(clienteAlterado.getCpf());
   cliente.setFoneCelular(clienteAlterado.getFoneCelular());
   cliente.setFoneFixo(clienteAlterado.getFoneFixo());
     
   cliente.setVersao(cliente.getVersao() + 1);
   repository.save(cliente);
}

@Transactional
public void delete (Long id){

    Cliente cliente = repository.findById(id).get();
    cliente.setHabilitado(Boolean.FALSE);
    cliente.setVersao(cliente.getVersao() + 1);

    repository.save(cliente); // so altera o habilitado para false ( exclusão logica , continua no BD )
    //repository.delete(cliente); - se quiser deletar realmente

}





}
