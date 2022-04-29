package pl.pak.rover;

public class UnknownDirectionException extends RuntimeException {
  public UnknownDirectionException(char n) {
    super("Unknown direction: "+n);
  }
}
