import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.pak.rover.Coordinates;
import pl.pak.rover.Direction;
import pl.pak.rover.ObstacleChecker;
import pl.pak.rover.Rover;
import pl.pak.rover.environment.Grid;

import static org.assertj.core.api.Assertions.assertThat;

import static pl.pak.rover.Direction.North;
import static pl.pak.rover.Direction.West;

public class RotateLeftTest {

  @ParameterizedTest
  @CsvSource({
      "North,West",
      "South,East",
      "East,North",
      "West,South"
  })
 public void rotateLeftWhenReceivedLCommand(Direction inputDirection, Direction directionAfterRotate){
    //given
    var rover = new Rover(new Coordinates(0.0,0.0, inputDirection),new ObstacleChecker(new Grid()));
    //when
    rover.receiveCommands('L');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(0.0,0.0,directionAfterRotate));
  }
}
