package pl.pak.rover;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pak.rover.commands.Command;

@Data
@AllArgsConstructor
public class Rover {

  private Coordinates coordinates;



  public void moveBackward() {
    switch (coordinates.getDirection()) {
      case North -> coordinates = coordinates.withY(coordinates.getY()-1);
      case South -> coordinates = coordinates.withY(coordinates.getY()+1);
      case East -> coordinates = coordinates.withX(coordinates.getX()-1);
      case West -> coordinates = coordinates.withX(coordinates.getX()+1);
    }
  }

  public void receiveCommands(char... commands) {
    for (char commandChar: commands) {
      coordinates=Command.of(commandChar).execute(coordinates);
//      if (commandChar == 'F') {
//        moveForward();
//      } else if (commandChar == 'B') {
//        moveBackward();
//      } else {
//        throw new UnknownCommandException(commands[0]);
//      }
    }

  }
}
