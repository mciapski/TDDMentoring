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
      case 'N' -> coordinates = coordinates.withY(coordinates.getY()+1);
      case 'S' -> coordinates = coordinates.withY(coordinates.getY()-1);
      case 'E' -> coordinates = coordinates.withX(coordinates.getX()+1);
      case 'W' -> coordinates = coordinates.withX(coordinates.getX()-1);
    }
  }

  public void moveBackward() {
    switch (coordinates.getDirection()) {
      case 'N' -> coordinates = coordinates.withY(coordinates.getY()-1);
      case 'S' -> coordinates = coordinates.withY(coordinates.getY()+1);
      case 'E' -> coordinates = coordinates.withX(coordinates.getX()-1);
      case 'W' -> coordinates = coordinates.withX(coordinates.getX()+1);
    }
  }

  public void receiveCommands(char... commands) {
    for (char aCommand: commands) {
      if (aCommand == 'F') {
        moveForward();
      } else if (aCommand == 'B') {
        moveBackward();
      } else {
        throw new UnknownCommandException(commands[0]);
      }
    }

  }
}
