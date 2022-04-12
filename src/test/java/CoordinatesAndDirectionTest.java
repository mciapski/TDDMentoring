import org.junit.jupiter.api.Test;
import pl.pak.rover.Coordinates;
import pl.pak.rover.Rover;

import static org.assertj.core.api.Assertions.assertThat;

public class CoordinatesAndDirectionTest {
  @Test
  public void receiveStartingCoordinates(){
    //given
    var coordinates = new Coordinates(1.0,1.0,'N');
    //when
    var rover = new Rover(coordinates);
    //then
    assertThat(rover.getCoordinates()).isEqualTo(coordinates);

    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(1.0,1.0,'N'));
  }
}
