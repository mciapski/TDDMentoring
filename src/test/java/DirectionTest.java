import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.pak.rover.Direction;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {

  @Test
  public void createsDirectionFromChars(){
    //given
    assertThat(Direction.of('N')).isEqualTo(Direction.North);
  }
}
