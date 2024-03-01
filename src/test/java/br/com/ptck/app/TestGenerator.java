package br.com.ptck.app;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.github.javafaker.Faker;

import br.com.ptck.app.core.Produto;
import br.com.ptck.app.core.Produto.CategoriaEnum;
import br.com.ptck.app.core.tarifas.IProduto;
import br.com.ptck.app.presenter.rest.entities.ProdutoRequest;

public class TestGenerator {

    private static final Faker faker = new Faker();

    public static UUID randomId() {
        return UUID.randomUUID();
    }

    public static String randomNome() {
        return faker.name().name();
    }

    public static CategoriaEnum randomCategoria() {
        List<String> categoriaEnumValues = Arrays.asList(CategoriaEnum.values()).stream()
                .map(Enum::name).collect(Collectors.toList());

        return CategoriaEnum.convertToEnum(categoriaEnumValues.get(randomNumberBetweenZeroAndFour())).get();
    }

    public static String randomCategoriaRequest() {
        List<String> categoriaEnumValues = Arrays.asList(CategoriaEnum.values()).stream()
                .map(Enum::name).collect(Collectors.toList());

        return categoriaEnumValues.get(randomNumberBetweenZeroAndFour());
    }

    public static Produto randomProduto() {
        return Produto.newInstance(randomId(), randomNome(), randomCategoria(), randomPreco(), randomPreco());
    }

    public static Produto bindRequest(ProdutoRequest request) {
        return Produto.newInstance(
                            request.getNome(), 
                            CategoriaEnum.convertToEnum(request.getCategoria()).get(), 
                            new BigDecimal(request.getPreco_base())
                        );
    }

    public static ProdutoRequest bindProduto(Produto produto) {
        return ProdutoRequest.from(produto);
    }

    private static int randomNumberBetweenZeroAndFour() {
        return faker.number().numberBetween(0, 4);
    }

    public static BigDecimal randomPreco() {
        return new BigDecimal(faker.number().randomDouble(2, 10, 100));
    }

    public static Double randomPrecoRequest() {
        return faker.number().randomDouble(2, 10, 100);
    }

    public static BigDecimal valorTarifado(IProduto produto, BigDecimal precoBase){
        return produto.calcularTarifa(precoBase);
    }

}
