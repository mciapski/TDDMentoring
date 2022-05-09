package pl.pak.rover;

import java.util.Arrays;

public enum Direction {
  North('N'),
  South('S'),
  East('E'),
  West('W');

  private final char direction;
  Direction(char direction) {

    this.direction = direction;
  }

  public static Direction of(char directionInChar) {
    return Arrays.stream(values())
        .filter(direction1 -> direction1.direction==directionInChar)
        .findFirst()
        .orElseThrow(()->new UnknownDirectionException(directionInChar));
  }
}
