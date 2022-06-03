package pl.pak.rover;

import lombok.RequiredArgsConstructor;
import pl.pak.rover.environment.Grid;

@RequiredArgsConstructor
public class ObstacleChecker {

  private final Grid grid;


  public boolean checkObstacle(Coordinates coordinates) {
    return grid.gridOfMap[(int) coordinates.getY()][(int) coordinates.getX()] == 'o';
  }
}
