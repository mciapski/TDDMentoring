import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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

  @Test
  public void movesOneStepForwardWhenReceivedFCommandInNDirections(){
    //given
    var rover = new Rover(new Coordinates(0.0, 0.0, 'N'));
    //when
    rover.receiveCommands('F');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(0.0, 1.0, 'N'));
  }
  @Test
  public void movesOneStepForwardWhenReceivedFCommandInSDirections(){
    //given
    var rover = new Rover(new Coordinates(0.0, 0.0, 'S'));
    //when
    rover.receiveCommands('F');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(0.0, -1.0, 'S'));
  }
  @Test
  public void movesOneStepForwardWhenReceivedFCommandInEDirection(){
    //given
    var rover = new Rover(new Coordinates(0.0,0.0,'E'));
    //when
    rover.receiveCommands('F');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(1.0, 0.0, 'E'));
  }
  @Test
  public void movesOneStepForwardWhenReceivedFCommandInWDirection(){
    //given
    var rover = new Rover(new Coordinates(0.0,0.0,'W'));
    //when
    rover.receiveCommands('F');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(-1.0, 0.0, 'W'));
  }

  @Test
  public void movesOneStepForwardWhenReceivedBCommandInNDirections(){
    //given
    var rover = new Rover(new Coordinates(0.0, 0.0, 'N'));
    //when
    rover.receiveCommands('B');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(0.0, -1.0, 'N'));
  }
  @Test
  public void movesOneStepForwardWhenReceivedWCommandInSDirections(){
    //given
    var rover = new Rover(new Coordinates(0.0, 0.0, 'S'));
    //when
    rover.receiveCommands('B');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(0.0, 1.0, 'S'));
  }
  @Test
  public void movesOneStepForwardWhenReceivedWCommandInEDirection(){
    //given
    var rover = new Rover(new Coordinates(0.0,0.0,'E'));
    //when
    rover.receiveCommands('B');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(-1.0, 0.0, 'E'));
  }
  @Test
  public void movesOneStepForwardWhenReceivedWCommandInWDirection(){
    //given
    var rover = new Rover(new Coordinates(0.0,0.0,'W'));
    //when
    rover.receiveCommands('B');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(1.0, 0.0, 'W'));
  }

}
