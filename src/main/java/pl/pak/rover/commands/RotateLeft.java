package pl.pak.rover.commands;

import pl.pak.rover.Coordinates;
import pl.pak.rover.Direction;

public class RotateLeft extends Command{
  @Override
  public Coordinates execute(Coordinates coordinates) {
    return switch (coordinates.getDirection()){
      case North -> coordinates.withDirection(Direction.West);
      case East -> coordinates.withDirection(Direction.North);
      case South -> coordinates.withDirection(Direction.East);
      case West -> coordinates.withDirection(Direction.South);
    };
  }
}
