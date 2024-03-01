package br.com.ptck.app.core.usecase;

import java.util.Optional;
import java.util.UUID;

import br.com.ptck.app.core.Produto;
import br.com.ptck.app.core.gateway.ProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

public class GetProdutoByIdUseCase implements UseCase<GetProdutoByIdUseCase.InputValues, GetProdutoByIdUseCase.OutputValues>{

    private ProdutoRepository repository;

    public GetProdutoByIdUseCase(ProdutoRepository repository){
        this.repository = repository;
    }
    
    @Override
    public OutputValues execute(InputValues input) {
        Optional<Produto> optional = findById(input.getId());

        return new OutputValues(optional);
    }
    
    private Optional<Produto> findById(UUID id) {
        return repository.findById(id);
    }

    @Getter
    @Setter
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class InputValues implements UseCase.InputValues {
        private final UUID id;
    }

    @Getter
    @Setter
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class OutputValues implements UseCase.OutputValues {
        private final Optional<Produto> optionalProduto;
    }

}
