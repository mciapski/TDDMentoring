import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.pak.rover.Coordinates;
import pl.pak.rover.Direction;
import pl.pak.rover.ObstacleChecker;
import pl.pak.rover.Rover;
import pl.pak.rover.environment.Grid;

import static org.assertj.core.api.Assertions.assertThat;

public class RotateRightTest {
  @ParameterizedTest
  @CsvSource({
      "North,East",
      "East,South",
      "South,West",
      "West,North"
  })
  public void rotateLeftWhenReceivedLCommand(Direction inputDirection, Direction directionAfterRotate){
    //given
    var rover = new Rover(new Coordinates(0.0,0.0, inputDirection),new ObstacleChecker(new Grid()));
    //when
    rover.receiveCommands('R');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(0.0,0.0,directionAfterRotate));
  }
}
