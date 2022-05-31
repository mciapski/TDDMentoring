package pl.pak.rover;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.val;
import pl.pak.rover.commands.Command;
import pl.pak.rover.environment.Grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
public class Rover {

  private Coordinates coordinates;
//  private Coordinates sensorCoordinates =coordinates;


  public void receiveCommands(Character... commands) {
    int Xchecker = 0;
    int Ychecker = 0;
    String sensorMessage = "Alert! On location(x,y): ";

    var commandsList = Stream.of(commands).map(Command::of).collect(Collectors.toList());
    val coordinateList = new ArrayList<Coordinates>();
    for (Command command : commandsList) {
      Xchecker = (int) coordinates.getX();
      Ychecker = (int) coordinates.getY();
      char field = Grid.gridOfMap[Ychecker][Xchecker];

      if (field == 'o') {
        System.out.println(sensorMessage + Ychecker + ", " + Xchecker);
        break;
      } else {
        coordinates = command.execute(coordinates);
      }
    }
  }

// 2.
//    for (Character commandChar : commands) {
//
//      commandsList.add(Command.of(commandChar));
//    }
//    commandsList.forEach(command -> coordinates=command.execute(coordinates));
// 3.


}
