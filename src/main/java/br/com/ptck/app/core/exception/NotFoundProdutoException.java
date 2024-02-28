package br.com.ptck.app.core.exception;

public class NotFoundProdutoException extends DomainException {
    public NotFoundProdutoException(String message) {
        super(message);
    }
}
