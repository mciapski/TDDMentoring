package pl.pak.rover;
import org.junit.jupiter.api.Test;

import pl.pak.rover.environment.Grid;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.pak.rover.Direction.East;

public class ObstacleCheckerTest {

  @Test
  public void returnTrueWhenObstaclePresent(){
    //given
    var obstacleChecker = new ObstacleChecker(new Grid());
    boolean obstaclePresent=obstacleChecker.checkObstacle(new Coordinates(2.0,0.0, East));
    //expected
    assertThat(obstaclePresent).isTrue();
  }
  @Test
  public void returnFalseWhenObstacleAbsent(){
    //given
    var obstacleChecker = new ObstacleChecker(new Grid());
    boolean obstaclePresent=obstacleChecker.checkObstacle(new Coordinates(0.0,0.0, East));
    //expected
    assertThat(obstaclePresent).isFalse();
  }
}
