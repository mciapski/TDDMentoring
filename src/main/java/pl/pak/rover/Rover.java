package pl.pak.rover;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
public class Rover {

  private Coordinates coordinates;

  public void moveForward() {
    switch (Direction.of(coordinates.getDirectionInChar())) {
      case North -> coordinates = coordinates.withY(coordinates.getY()+1);
      case South -> coordinates = coordinates.withY(coordinates.getY()-1);
      case East -> coordinates = coordinates.withX(coordinates.getX()+1);
      case West -> coordinates = coordinates.withX(coordinates.getX()-1);
    }
  }

  public void moveBackward() {
    switch (Direction.of(coordinates.getDirectionInChar())) {
      case North -> coordinates = coordinates.withY(coordinates.getY()-1);
      case South -> coordinates = coordinates.withY(coordinates.getY()+1);
      case East -> coordinates = coordinates.withX(coordinates.getX()-1);
      case West -> coordinates = coordinates.withX(coordinates.getX()+1);
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
