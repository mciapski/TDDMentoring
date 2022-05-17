package pl.pak.rover.commands;

import org.springframework.stereotype.Component;
import pl.pak.rover.Coordinates;
import pl.pak.rover.UnknownCommandException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

// Pattern Command
// Wyniesienie logiki biznesowej z Rover
// moveForward i moveBackward przeniesione
@Component
public abstract class Command {

  //public interface Supplier<T> {
  //
  //    /**
  //     * Gets a result.
  //     *
  //     * @return a result
  //     */
  //    T get();
  private static final List<Character> characterList = List.of('F', 'B');
  private static final List<Supplier<Command>> commandList = List.of(MoveForward::new, MoveBackward::new);

  private static final Map<List<Character>, List<Supplier<Command>>> availableCommands = Map.of(
      characterList, commandList);

  public static Command of(char commandChar) {

    return Optional.ofNullable(availableCommands.get(commandChar))
        .orElseThrow(()->new UnknownCommandException(commandChar)).get();


  }

  public abstract Coordinates execute(Coordinates coordinates);
}
