package br.com.ptck.app.presenter.rest;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ptck.app.core.usecase.CreateProdutoUseCase;
import br.com.ptck.app.core.usecase.FindProdutoByIdUseCase;
import br.com.ptck.app.core.usecase.UpdateProdutoUseCase;
import br.com.ptck.app.presenter.rest.entities.ProdutoRequest;
import br.com.ptck.app.presenter.rest.entities.ProdutoResponse;
import jakarta.validation.Valid;


@RestController
public class ProdutoController {

    @Autowired
    CreateProdutoUseCase createProdutoUseCase;
    
    @Autowired
    FindProdutoByIdUseCase findByCategoria;
    
    @Autowired
    UpdateProdutoUseCase updateProdutoUseCase;

    @PostMapping("/produto")
    public ResponseEntity<ProdutoResponse> createProduto(@Valid @RequestBody ProdutoRequest request) throws URISyntaxException {

        ProdutoResponse produto = createProdutoUseCase.execute(request, null);
            
        return ResponseEntity.created(new URI("/produto")).body(produto);
    }

    @PutMapping("/produto/{id}")
    public ResponseEntity<ProdutoResponse> updateProduto(@Valid @RequestBody ProdutoRequest request, @Valid @PathVariable String id) throws URISyntaxException {

        ProdutoResponse produto = updateProdutoUseCase.execute(request, id);
            
        return ResponseEntity.created(new URI("/produto")).body(produto);
    }

}
