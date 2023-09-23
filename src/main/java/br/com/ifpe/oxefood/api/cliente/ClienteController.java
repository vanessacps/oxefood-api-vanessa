package br.com.ifpe.oxefood.api.cliente;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import br.com.ifpe.oxefood.modelo.cliente.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;




@RestController
@RequestMapping("/api/cliente")
@CrossOrigin
public class ClienteController {
    
    @Autowired
   private ClienteService clienteService;

   
   @ApiOperation(value = "Serviço responsável por salvar um cliente no sistema.")
   @PostMapping
   public ResponseEntity<Cliente> save(@RequestBody ClienteRequest request) {

       Cliente cliente = clienteService.save(request.build());
       return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
   }

   @ApiOperation(value = "Serviço responsável por listar todos os clientes do sistema.")
    @GetMapping
    public List<Cliente> findAll() {
  
        return clienteService.findAll();
    }

    @ApiOperation(value = "Serviço responsável por obter um cliente referente ao Id passado na URL.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna  o cliente."),
        @ApiResponse(code = 401, message = "Acesso não autorizado."),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
        @ApiResponse(code = 404, message = "Não foi encontrado um registro para o Id informado."),
        @ApiResponse(code = 500, message = "Foi gerado um erro no servidor."),
    })
 
    @GetMapping("/{id}")
    public Cliente findById(@PathVariable Long id) {

        return clienteService.findById(id);
    }

    @ApiOperation(value = "Serviço responsável por alterar um cliente referente ao id passado na URL no sistema.")
    @PutMapping("/{id}")
   public ResponseEntity<Cliente> update(@PathVariable("id") Long id, @RequestBody ClienteRequest request) {

       clienteService.update(id, request.build());
       return ResponseEntity.ok().build();
   }


   @ApiOperation(value = "Serviço responsável por deletar um cliente referente ao id passado na URL no sistema.")
   @DeleteMapping ("/{id}")
   public ResponseEntity<Void> delete (@PathVariable Long id){

        clienteService.delete(id);
        return ResponseEntity.ok().build();

   }

   
}


