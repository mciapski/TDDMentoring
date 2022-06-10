package pl.pak.rover;

import lombok.RequiredArgsConstructor;
import pl.pak.rover.environment.Grid;

@RequiredArgsConstructor
public class ObstacleChecker {

  private final Grid grid;


  public boolean checkObstacle(Coordinates coordinates) {

    int coordinateYafterInversion = grid.gridOfMap.length-1-(int)coordinates.getY();



    return grid.gridOfMap[coordinateYafterInversion][(int) coordinates.getX()] == 'o';
  }
}
