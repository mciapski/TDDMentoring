import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.pak.rover.Direction;
import pl.pak.rover.UnknownCommandException;
import pl.pak.rover.UnknownDirectionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DirectionTest {

  //  @Test
//  public void createsDirectionFromChars(){
//    //given
//    assertThat(Direction.of('N')).isEqualTo(Direction.North);
//  }
  @ParameterizedTest
  @CsvSource({
      "N, Direction.North",
      "S, Direction.South",
      "E, Direction.East",
      "W, Direction.West"
  })
  public void createsDirectionsFromChars(char input, String direction) {
    //expected
    assertThat(Direction.of('N')).isEqualTo(Direction.North);
  }

  @ParameterizedTest
  @ValueSource(chars={'A','B','C','D'})
  public void throwsAnExceptionWhenReceivesUnknownChar(char input){
    //expected
    assertThatThrownBy(()->Direction.of(input))
        .isInstanceOf(UnknownDirectionException.class)
        .hasMessage("Unknown direction: " + input);
  }
}
