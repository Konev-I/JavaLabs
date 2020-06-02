package main.exeptions;

public class SomethingNotFoundExeption extends RuntimeException {
  public SomethingNotFoundExeption(String message) {
    super(message);
  }
}
