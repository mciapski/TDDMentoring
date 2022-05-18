package pl.pak.rover;

import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

public class UnknownCommandException  extends RuntimeException {

  public UnknownCommandException(char command) {
    super("Unknown command: " + command);
  }
}
