package pl.pak.rover.commands;

import pl.pak.rover.Coordinates;

public class MoveBackward extends Command{
  @Override
  public Coordinates execute(Coordinates coordinates) {
    return switch (coordinates.getDirection()) {
      case North -> coordinates.withY(coordinates.getY() - 1);
      case South -> coordinates.withY(coordinates.getY() + 1);
      case East -> coordinates.withX(coordinates.getX() - 1);
      case West -> coordinates.withX(coordinates.getX() + 1);
    };
  }
}
