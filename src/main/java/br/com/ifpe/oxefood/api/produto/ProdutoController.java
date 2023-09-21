package br.com.ifpe.oxefood.api.produto;

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

import br.com.ifpe.oxefood.modelo.CategoriaProduto.CategoriaProdutoService;
import br.com.ifpe.oxefood.modelo.produto.Produto;
import br.com.ifpe.oxefood.modelo.produto.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/produto")
@CrossOrigin
public class ProdutoController {
      
    @Autowired
   private ProdutoService produtoService;

    @Autowired
   private CategoriaProdutoService categoriaProdutoService;


   @ApiOperation(value = "Serviço responsável por salvar um produto no sistema.")
   @PostMapping
   public ResponseEntity<Produto> save(@RequestBody ProdutoRequest request) {

    Produto produtoNovo = request.build();
    produtoNovo.setCategoria(categoriaProdutoService.findById(request.getIdCategoria()));
    Produto produto = produtoService.save(produtoNovo);
    return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);

   }


   @ApiOperation(value = "Serviço responsável por listar todos os produtos do sistema.")
    @GetMapping
    public List<Produto> findAll() {
  
        return produtoService.findAll();
    }

    @ApiOperation(value = "Serviço responsável por obter um produto referente ao Id passado na URL.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna  o produto."),
        @ApiResponse(code = 401, message = "Acesso não autorizado."),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
        @ApiResponse(code = 404, message = "Não foi encontrado um registro para o Id informado."),
        @ApiResponse(code = 500, message = "Foi gerado um erro no servidor."),
    })

    @GetMapping("/{id}")
    public Produto findById(@PathVariable Long id) {

        return produtoService.findById(id);
    }


    @ApiOperation(value = "Serviço responsável por alterar um produto referente ao id passado na URL no sistema.")
    @PutMapping ("/{id}")
    public ResponseEntity<Produto> update (@PathVariable ("id") Long id, @RequestBody ProdutoRequest request){

        Produto produto = request.build();
       produto.setCategoria(categoriaProdutoService.findById(request.getIdCategoria()));
       produtoService.update(id, produto);


        return ResponseEntity.ok().build();
    }


    @ApiOperation(value = "Serviço responsável por deletar um produto referente ao id passado na URL no sistema.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){

        produtoService.delete(id);
        return ResponseEntity.ok().build();

    }

}

