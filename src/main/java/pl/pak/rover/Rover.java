package pl.pak.rover;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pak.rover.commands.Command;

import java.util.stream.Stream;

@Data
@AllArgsConstructor
public class Rover {

  private Coordinates coordinates;



  public void receiveCommands(Character... commands) {
    Stream.of(commands).forEach(x->coordinates=Command.of(x).execute(coordinates));
//    for (char commandChar: commands) {
//      coordinates=Command.of(commandChar).execute(coordinates);
//    }

  }
}
