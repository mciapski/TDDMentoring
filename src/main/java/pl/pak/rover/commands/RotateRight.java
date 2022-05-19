package pl.pak.rover.commands;

import pl.pak.rover.Coordinates;
import pl.pak.rover.Direction;

public class RotateRight extends Command{
  @Override
  public Coordinates execute(Coordinates coordinates) {
    return switch (coordinates.getDirection()){
      case North -> coordinates.withDirection(Direction.East);
      case East -> coordinates.withDirection(Direction.South);
      case South -> coordinates.withDirection(Direction.West);
      case West -> coordinates.withDirection(Direction.North);
    };
  }
}
