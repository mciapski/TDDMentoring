package pl.pak.rover;

import lombok.Value;
import lombok.With;

import java.util.Objects;

@Value
@With
public class Coordinates {


  double x;
  double y;
  Direction direction;



}
