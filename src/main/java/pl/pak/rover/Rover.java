package pl.pak.rover;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pak.rover.commands.Command;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
public class Rover {

  private Coordinates coordinates;


  public void receiveCommands(Character... commands) {

    var commandsList = Stream.of(commands).map(Command::of).collect(Collectors.toList());
    commandsList.forEach(command -> coordinates=command.execute(coordinates));
//    for (char commandChar: commands) {
//      coordinates=Command.of(commandChar).execute(coordinates);
//    }

  }
}
