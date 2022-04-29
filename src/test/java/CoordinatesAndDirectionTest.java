import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.pak.rover.Coordinates;
import pl.pak.rover.Rover;
import pl.pak.rover.UnknownCommandException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

//Zadanie domowe:

//1. Obsłużyć ruch niezależnie od stron  w którą jest obrócony
//2. Obsłużyć ruch do tyłu
//3. Refactoring
//4. Te same testy wer. Spock

public class CoordinatesAndDirectionTest {
  @Test
  public void receiveStartingCoordinates() {
    //given
    var coordinates = new Coordinates(1.0, 1.0, 'N');
    //when
    var rover = new Rover(coordinates);
    //then
    assertThat(rover.getCoordinates()).isEqualTo(coordinates);
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(1.0, 1.0, 'N'));
  }

  @Test
  public void throwsAnExceptionWhenReceivesUnknownCommand(){
    //given
    var rover = new Rover(new Coordinates(1.0, 1.0, 'N'));
    //expect
    assertThatThrownBy(()->rover.receiveCommands('u'))
        .isInstanceOf(UnknownCommandException.class)
        .hasMessage("Unknown command: u");
  }

  @ParameterizedTest
  @CsvSource({
      "N,0,1",
      "S,0,-1",
      "E,1,0",
      "W,-1,0"
  })
  public void movesOneStepForwardWhenReceivedFCommand(char direction,double resultX,double resultY){
    //given
    var rover = new Rover(new Coordinates(0.0, 0.0, direction));
    //when
    rover.receiveCommands('F');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(resultX, resultY, direction));
  }
  @ParameterizedTest
  @CsvSource({
      "N,0,-1",
      "S,0,1",
      "E,-1,0",
      "W,1,0"
  })
  public void movesOneStepBackwardWhenReceivedBCommand(char direction,double resultX,double resultY){
    //given
    var rover = new Rover(new Coordinates(0.0, 0.0, direction));
    //when
    rover.receiveCommands('B');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(resultX, resultY, direction));
  }

  @Test
  public void receivesMultipleCommandsAsAnArray(){
    //given
    var rover = new Rover(new Coordinates(0.0, 0.0, 'N'));
    //when
    rover.receiveCommands('B','F','F');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(0.0,1.0,'N'));
  }


}
