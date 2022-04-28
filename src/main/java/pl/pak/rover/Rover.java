package pl.pak.rover;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
public class Rover {

  private Coordinates coordinates;

  public void moveForward() {
    switch (coordinates.getDirection()) {
      case 'N' -> coordinates = new Coordinates(coordinates.getX()
          , coordinates.getY() + 1
          , coordinates.getDirection());
      case 'S' -> coordinates = new Coordinates(coordinates.getX()
          , coordinates.getY() - 1
          , coordinates.getDirection());
      case 'E' -> coordinates = new Coordinates(coordinates.getX() + 1
          , coordinates.getY()
          , coordinates.getDirection());
      case 'W' -> coordinates = new Coordinates(coordinates.getX() - 1
          , coordinates.getY()
          , coordinates.getDirection());
    }
  }

  public void moveBackward() {
    switch (coordinates.getDirection()) {
      case 'N' -> coordinates = new Coordinates(coordinates.getX()
          , coordinates.getY() - 1
          , coordinates.getDirection());
      case 'S' -> coordinates = new Coordinates(coordinates.getX()
          , coordinates.getY() + 1
          , coordinates.getDirection());
      case 'E' -> coordinates = new Coordinates(coordinates.getX() - 1
          , coordinates.getY()
          , coordinates.getDirection());
      case 'W' -> coordinates = new Coordinates(coordinates.getX() + 1
          , coordinates.getY()
          , coordinates.getDirection());
    }
  }

  public void receiveCommands(char... commands) {
    if (commands[0] == 'F') {
      moveForward();
    } else if (commands[0] == 'B') {
      moveBackward();
    } else {
      throw new UnknownCommandException(commands[0]);
    }
  }
}
