package br.com.ptck.app.core.usecase;

public interface UseCase<I, O> {

    O execute(I arg, String id);

}
