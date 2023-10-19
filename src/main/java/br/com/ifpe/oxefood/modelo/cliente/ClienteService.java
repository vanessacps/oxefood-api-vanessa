package br.com.ifpe.oxefood.modelo.cliente;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.modelo.Acesso.UsuarioService;
import br.com.ifpe.oxefood.util.entity.exception.EntidadeNaoEncontradaException;

import java.util.List;
import java.lang.Long;

import java.util.Optional;

 



@Service
public class ClienteService  {

    @Autowired
    private UsuarioService usuarioService;

   
   


    @Autowired
   private ClienteRepository repository;

   

 
   @Transactional
   public Cliente save(Cliente cliente) {

     usuarioService.save(cliente.getUsuario());


       cliente.setHabilitado(Boolean.TRUE);
       cliente.setVersao(1L);
       cliente.setDataCriacao(LocalDate.now());
       return repository.save(cliente);
   }

   public List<Cliente> findAll() {
  
    return repository.findAll();
}

public Cliente findById(Long id) {

    Optional<Cliente> consulta = repository.findById(id);
  
       if (consulta.isPresent()) {
           return consulta.get();
       } else {
           throw new EntidadeNaoEncontradaException("Cliente", id);
       }

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
