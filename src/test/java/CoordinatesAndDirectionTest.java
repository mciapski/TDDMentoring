import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.pak.rover.Coordinates;
import pl.pak.rover.Direction;
import pl.pak.rover.Rover;
import pl.pak.rover.UnknownCommandException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pl.pak.rover.Direction.North;

//Zadanie domowe:

//1. Obsłużyć ruch niezależnie od stron  w którą jest obrócony DONE
//2. Obsłużyć ruch do tyłu  DONE
//3. Refactoring INWORK
//4. Te same testy wer. Spock DONE
//5. Dokończyć directiontest na wszystkie kierunki DONE
//6. Zrefaktorować kod aby używać direction DONE
//7. Test do wyjątku UnknownDirectionException DONE
//8. MoveBackward obslużyć tak jak MoveForward
//9. Przenieść testy ruchów podstawowych do klas testujących
//10. Obsłużyć obroty wg.TDD
//11. Refaktor receiveCOmmands w Rover do postaci strumienia(for)

public class CoordinatesAndDirectionTest {
  @Test
  public void receiveStartingCoordinates() {
    //given
    var coordinates = new Coordinates(1.0, 1.0, North);
    //when
    var rover = new Rover(coordinates);
    //then
    assertThat(rover.getCoordinates()).isEqualTo(coordinates);
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(1.0, 1.0, North));
  }

  @Test
  public void throwsAnExceptionWhenReceivesUnknownCommand(){
    //given
    var rover = new Rover(new Coordinates(1.0, 1.0, North));
    //expect
    assertThatThrownBy(()->rover.receiveCommands('u'))
        .isInstanceOf(UnknownCommandException.class)
        .hasMessage("Unknown command: u");
  }

  @ParameterizedTest
  @CsvSource({
      "North,0,1",
      "South,0,-1",
      "East,1,0",
      "West,-1,0"
  })
  public void movesOneStepForwardWhenReceivedFCommand(Direction direction,double resultX,double resultY){
    //given
    var rover = new Rover(new Coordinates(0.0, 0.0, direction));
    //when
    rover.receiveCommands('F');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(resultX, resultY, direction));
  }
  @ParameterizedTest
  @CsvSource({
      "North,0,-1",
      "South,0,1",
      "East,-1,0",
      "West,1,0"
  })
  public void movesOneStepBackwardWhenReceivedBCommand(Direction direction,double resultX,double resultY){
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
    var rover = new Rover(new Coordinates(0.0, 0.0, North));
    //when
    rover.receiveCommands('B','F','F');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(0.0,1.0,North));
  }


}
