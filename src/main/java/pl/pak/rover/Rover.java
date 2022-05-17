package pl.pak.rover;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pak.rover.commands.Command;

@Data
@AllArgsConstructor
public class Rover {

  private Coordinates coordinates;



  public void receiveCommands(char... commands) {
    for (char commandChar: commands) {
      coordinates=Command.of(commandChar).execute(coordinates);
    }

  }
}
