package br.com.ptck.app.core.usecase;

public interface UseCase<I extends UseCase.InputValues, O extends UseCase.OutputValues>{

   public abstract O execute(I input);

   public interface InputValues {}

   public interface OutputValues {}

}
