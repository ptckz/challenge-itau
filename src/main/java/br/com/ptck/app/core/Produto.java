package br.com.ptck.app.core;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Produto {

    private UUID id;
    private String nome;
    private CategoriaEnum categoria;
    private BigDecimal precoBase;
    private BigDecimal precoTarifado;

    public Produto(String nome, CategoriaEnum categoria, BigDecimal precoBase, BigDecimal precoTarifado) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoBase = precoBase;
        this.precoTarifado = precoTarifado;
    }

    public Produto(UUID id, String nome, CategoriaEnum categoria, BigDecimal precoBase, BigDecimal precoTarifado) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.precoBase = precoBase;
        this.precoTarifado = precoTarifado;
    }

    public Produto(String nome, CategoriaEnum categoria, BigDecimal precoBase) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoBase = precoBase;
    }

    public static Produto newInstance(String nome, CategoriaEnum categoria, BigDecimal precoBase){
        return new Produto(
            nome, 
            categoria, 
            precoBase
        );
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public BigDecimal getPrecoBase() {
        return precoBase;
    }

    public BigDecimal getPrecoTarifado() {
        return precoTarifado;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }

    public void setPrecoBase(BigDecimal precoBase) {
        this.precoBase = precoBase;
    }

    public void setPrecoTarifado(BigDecimal precoTarifado) {
        this.precoTarifado = precoTarifado;
    }

    public enum CategoriaEnum {

        VIDA,
        AUTO,
        VIAGEM,
        PATRIMONIAL,
        RESIDENCIAL;

        public static Optional<CategoriaEnum> convertToEnum(String enumString) {
            Set<String> categoriaEnumValues = Arrays.asList(CategoriaEnum.values()).stream()
                    .map(Enum::name).collect(Collectors.toSet());
            if (categoriaEnumValues.contains(enumString.toUpperCase()))
                return Optional.of(CategoriaEnum.valueOf(enumString.toUpperCase()));
            else
                return Optional.empty();
        }

    }

}
