package br.com.ifpe.oxefood.api.CupomDesconto;

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

import br.com.ifpe.oxefood.modelo.CupomDesconto.CupomDesconto;
import br.com.ifpe.oxefood.modelo.CupomDesconto.CupomDescontoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/cupomDesconto")
@CrossOrigin
public class CupomDescontoController {

      @Autowired
   private CupomDescontoService cupomDescontoService;

   
   @ApiOperation(value = "Serviço responsável por salvar um cupom desconto no sistema.")
   @PostMapping
   public ResponseEntity<CupomDesconto> save(@RequestBody CupomDescontoRequest request) {

       CupomDesconto cupomDesconto = cupomDescontoService.save(request.build());
       return new ResponseEntity<CupomDesconto>(cupomDesconto, HttpStatus.CREATED);
   }

   @ApiOperation(value = "Serviço responsável por listar todos os cupom desconto do sistema.")
    @GetMapping
    public List<CupomDesconto> findAll() {
  
        return cupomDescontoService.findAll();
    }

    @ApiOperation(value = "Serviço responsável por obter um cupom desconto referente ao Id passado na URL.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna  o cupom desconto."),
        @ApiResponse(code = 401, message = "Acesso não autorizado."),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
        @ApiResponse(code = 404, message = "Não foi encontrado um registro para o Id informado."),
        @ApiResponse(code = 500, message = "Foi gerado um erro no servidor."),
    })
 
    @GetMapping("/{id}")
    public CupomDesconto findById(@PathVariable Long id) {

        return cupomDescontoService.findById(id);
    }

    @ApiOperation(value = "Serviço responsável por alterar um cupom desconto referente ao id passado na URL no sistema.")
    @PutMapping("/{id}")
   public ResponseEntity<CupomDesconto> update(@PathVariable("id") Long id, @RequestBody CupomDescontoRequest request) {

       cupomDescontoService.update(id, request.build());
       return ResponseEntity.ok().build();
   }


   @ApiOperation(value = "Serviço responsável por deletar um cupom desconto referente ao id passado na URL no sistema.")
   @DeleteMapping ("/{id}")
   public ResponseEntity<Void> delete (@PathVariable Long id){

       cupomDescontoService.delete(id);
        return ResponseEntity.ok().build();

   }

   
}

    
    

