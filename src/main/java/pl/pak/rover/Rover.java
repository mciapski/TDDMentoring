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
    List<Coordinates> coordinateList = new ArrayList<>();
    for (Command command:commandsList) {
      coordinates=command.execute(coordinates);
      coordinateList.add(coordinates);
    }
    for (int i=0; i<coordinateList.size();i++) {
      Xchecker = (int) coordinateList.get(i).getX();
      Ychecker = (int) coordinateList.get(i).getY();
      char field = Grid.gridOfMap[Ychecker][Xchecker];

      if (field == 'o') {
        System.out.println(sensorMessage + Xchecker + ", " + Ychecker);
        coordinates=coordinateList.get(i-1);
        break;
      } else {
        coordinates = coordinateList.get(i);

      }
    }
    System.out.println(coordinates);
  }

// 2.
//    for (Character commandChar : commands) {
//
//      commandsList.add(Command.of(commandChar));
//    }
//    commandsList.forEach(command -> coordinates=command.execute(coordinates));
// 3.


}
