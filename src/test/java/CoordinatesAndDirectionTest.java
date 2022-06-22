import org.assertj.core.api.Assertions;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.yaml.snakeyaml.util.ArrayUtils;
import pl.pak.rover.*;
import pl.pak.rover.commands.Command;
import pl.pak.rover.environment.Grid;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.pak.rover.Direction.*;


//Zadanie domowe:

//1. Obsłużyć ruch niezależnie od stron  w którą jest obrócony DONE
//2. Obsłużyć ruch do tyłu  DONE
//3. Refactoring INWORK
//4. Te same testy wer. Spock DONE
//5. Dokończyć directiontest na wszystkie kierunki DONE
//6. Zrefaktorować kod aby używać direction DONE
//7. Test do wyjątku UnknownDirectionException DONE
//8. MoveBackward obslużyć tak jak MoveForward DONE
//9. Przenieść testy ruchów podstawowych do klas testujących DONE
//10. Obsłużyć obroty wg.TDD DONE
//11. Refaktor receiveCommands w Rover do postaci strumienia(for) DONE
//12. Napisać klasy testujące dla obrotów
//13. Dokończyć testy



public class CoordinatesAndDirectionTest {
  @Test
  public void receiveStartingCoordinates() {
    //given
    var coordinates = new Coordinates(1.0, 1.0, North);
    //when
    var rover = new Rover(coordinates,new ObstacleChecker(new Grid()));
    //then
    assertThat(rover.getCoordinates()).isEqualTo(coordinates);
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(1.0, 1.0, North));
  }

  @Test
  public void throwsAnExceptionWhenReceivesUnknownCommand() {
    //given
    var rover = new Rover(new Coordinates(0.0, 0.0, North),new ObstacleChecker(new Grid()));
    //expect

    assertThatThrownBy(() -> rover.receiveCommands('u'))
        .isInstanceOf(UnknownCommandException.class)
        .hasMessage("Unknown command: u");
  }


  @Test
  public void receivesMultipleCommandsAsAnArray() {
    //given
    var rover = new Rover(new Coordinates(0.0, 0.0, North),new ObstacleChecker(new Grid()));
    //when
    rover.receiveCommands('R','F');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(1.0, 0.0, East));
  }
  @Test
  public void doesntMoveWhenAnyOfCommandIsIncorrect() {
    //given
    var rover = new Rover(new Coordinates(0.0, 3.0, North),new ObstacleChecker(new Grid()));
    //when
    var throwable = catchThrowable(()->rover.receiveCommands( 'F', 'F','U','L'));
    //then
    assertThat(throwable)
        .isInstanceOf(UnknownCommandException.class)
        .hasMessage("Unknown command: U");
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(0.0, 3.0, North));
  }

  @Test
  public void receivesCharacterArray(){
    //given
    var rover = new Rover(new Coordinates(0.0, 0.0, East),new ObstacleChecker(new Grid()));
    //when
    rover.receiveCommands('F','B');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(0.0,0.0,East));
  }
  @Test
  public void detectObstacleInForwardAndStayOneMoveBeforeItInEastDirection(){
    //given
    var rover = new Rover(new Coordinates(0.0, 0.0, East),new ObstacleChecker(new Grid()));
    //when
    rover.receiveCommands('F','F','F','F');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(3.0, 0.0, East));
  }
  @Test
  public void detectObstacleAndStayOneMoveBeforeItInSouthDirection(){
    //given
    var rover = new Rover(new Coordinates(0.0, 0.0, East),new ObstacleChecker(new Grid()));
    //when
    rover.receiveCommands('L','F','F');
    //then
    assertThat(rover.getCoordinates()).isEqualTo(new Coordinates(0.0, 1.0, North));
  }

  @ParameterizedTest
  @CsvSource({
      "0,0,North,  0,3,North, B",
      "0,3,North,  0,0,North, F",
      "1,3,North,  1,1,North, FF"
  })
  public void wrapAroundTheEdgesInVerticalDirection(
      double xValue, double yValue, Direction direction,
      double xValueAfterWrap, double yValueAfterWrap, Direction directionAfterWrap, String moveSequenceInString){
    //given
    var rover = new Rover(new Coordinates(xValue, yValue, direction), new ObstacleChecker(new Grid()));
    Character[] moveSequence = moveSequenceInString.chars().mapToObj(
        c->(char)c).toArray(Character[]::new);

    //when
    rover.receiveCommands(moveSequence);

    //then
    assertThat(rover.getCoordinates()).isEqualTo(
        new Coordinates(xValueAfterWrap, yValueAfterWrap, directionAfterWrap));
  }
  @ParameterizedTest
  @CsvSource({
      "0,0,East,  4,0,East, B",
      "4,0,East,  0,0,East, F",

  })
  public void wrapAroundTheEdgesInHorizontalDirection(
      double xValue, double yValue, Direction direction,
      double xValueAfterWrap, double yValueAfterWrap, Direction directionAfterWrap, String moveSequenceInString){
    //given
    var rover = new Rover(new Coordinates(xValue, yValue, direction), new ObstacleChecker(new Grid()));
    Character[] moveSequence = moveSequenceInString.chars().mapToObj(
        c->(char)c).toArray(Character[]::new);

    //when
    rover.receiveCommands(moveSequence);

    //then
    assertThat(rover.getCoordinates()).isEqualTo(
        new Coordinates(xValueAfterWrap, yValueAfterWrap, directionAfterWrap));
  }


}
