package pl.pak.rover;

import java.io.IOException;

public class UnknownCommandException  extends RuntimeException {

  public UnknownCommandException(char command) {
    super("Unknown command: " + command);
  }
}
