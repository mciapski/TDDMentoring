package pl.pak.rover.commands;

import org.junit.jupiter.api.Test;
import pl.pak.rover.UnknownCommandException;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CommandTest {


  @Test
  void returnTrueWhenOFreceiveFandBparametersAndReturnNotNullValues() {

    //expected
    assertThat(Command.of('F')).isNotNull();
    assertThat(Command.of('B')).isNotNull();
  }

}