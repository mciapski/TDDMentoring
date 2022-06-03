import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.pak.rover.Coordinates;
import pl.pak.rover.Direction;
import pl.pak.rover.ObstacleChecker;
import pl.pak.rover.Rover;
import pl.pak.rover.environment.Grid;

import static org.assertj.core.api.Assertions.assertThat;

public class MoveForwardTest {

  @ParameterizedTest
  @CsvSource({
      "North,0,1",
      "South,0,-1",
      "East,1,0",
      "West,-1,0"
  })
  public void movesOneStepForwardWhenReceivedFCommand(Direction direction, double resultX, double resultY){
    //given
    var rover = new Rover(new Coordinates(0.0, 0.0, direction),new ObstacleChecker(new Grid()));
    //when
    rover.receiveCommands('F');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(resultX, resultY, direction));
  }
}
