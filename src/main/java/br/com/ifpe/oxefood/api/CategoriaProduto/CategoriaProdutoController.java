package br.com.ifpe.oxefood.api.CategoriaProduto;

import java.util.List;

import javax.validation.Valid;

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

import br.com.ifpe.oxefood.modelo.CategoriaProduto.CategoriaProduto;
import br.com.ifpe.oxefood.modelo.CategoriaProduto.CategoriaProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;




@RestController
@RequestMapping("/api/categoriaproduto")
@CrossOrigin

public class CategoriaProdutoController {

    @Autowired
    private CategoriaProdutoService categoriaProdutoService;

     
    
   @ApiOperation(value = "Serviço responsável por salvar uma categoria produto no sistema.")
   @PostMapping
   public ResponseEntity<CategoriaProduto> save(@RequestBody @Valid CategoriaProdutoRequest request) {

       CategoriaProduto categoriaProdutoNovo = request.build();
       CategoriaProduto categoriaProduto = categoriaProdutoService.save (categoriaProdutoNovo);
       return new ResponseEntity<CategoriaProduto>(categoriaProduto, HttpStatus.CREATED);
   }


   @ApiOperation(value = "Serviço responsável por listar todos as categorias do produto do sistema.")
    @GetMapping
    public List<CategoriaProduto> findAll() {
  
        return categoriaProdutoService.findAll();
    }

    @ApiOperation(value = "Serviço responsável por obter uma categoria de produto referente ao Id passado na URL.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna  a categotia do produto."),
        @ApiResponse(code = 401, message = "Acesso não autorizado."),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
        @ApiResponse(code = 404, message = "Não foi encontrado um registro para o Id informado."),
        @ApiResponse(code = 500, message = "Foi gerado um erro no servidor."),
    })
   
    @GetMapping("/{id}")
    public CategoriaProduto findById(@PathVariable Long id) {

        return categoriaProdutoService.findById(id);
    }


   @ApiOperation(value = "Serviço responsável por alterar uma categoria de produto referente ao id passado na URL no sistema.")
    @PutMapping("/{id}")
   public ResponseEntity<CategoriaProduto> update(@PathVariable("id") Long id, @RequestBody  @Valid CategoriaProdutoRequest request) {

      categoriaProdutoService.update(id, request.build());
       return ResponseEntity.ok().build();
   }


   @ApiOperation(value = "Serviço responsável por deletar uma categoria de produto referente ao id passado na URL no sistema.")
   @DeleteMapping ("/{id}")
   public ResponseEntity<Void> delete (@PathVariable Long id){

        categoriaProdutoService.delete(id);
        return ResponseEntity.ok().build();

   }

}

    

