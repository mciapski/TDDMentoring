package pl.pak.rover.environment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
public class Grid {
  public static char[][] gridOfMap = {
      {'x','x','o','o','x'},
      {'x','o','x','x','x'},
      {'o','x','o','x','x'},
      {'x','x','x','o','x'},
  };

}
