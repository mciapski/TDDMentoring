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

  private ObstacleChecker obstacleChecker;

  public void receiveCommands(Character... commands) {

    var commandsList = Stream.of(commands).map(Command::of).collect(Collectors.toList());
    for (Command command : commandsList) {

      if (obstacleChecker.checkObstacle(coordinates)) {
        break;
      }
      coordinates = command.execute(coordinates);

      if (coordinates.getY() < 0) {
        coordinates = coordinates.withY(Grid.gridOfMap.length - 1);
      }
      if (coordinates.getY() > Grid.gridOfMap.length-1) {
        coordinates = coordinates.withY(coordinates.getY()*0);
      }
      if (coordinates.getX() < 0) {
        coordinates = coordinates.withX(Grid.gridOfMap[0].length - 1);
      }
      if (coordinates.getX() > Grid.gridOfMap[0].length-1) {
        coordinates = coordinates.withX(coordinates.getY()*0);
      }

    }

//  public Coordinates wrapAroundTheEdgesVertical(Coordinates coordinates){
//    return switch ((int) coordinates.getY()){
//      case -1 -> coordinates.withY(coordinates.getY()+Grid.gridOfMap.length);
//      case 1 -> coordinates.withY(coordinates.getY()%Grid.gridOfMap.length);
//      default -> throw new IllegalStateException("Unexpected value: " + (int) coordinates.getY());
//    };
  }

// 2.
//    for (Character commandChar : commands) {
//
//      commandsList.add(Command.of(commandChar));
//    }
//    commandsList.forEach(command -> coordinates=command.execute(coordinates));
// 3.


}
