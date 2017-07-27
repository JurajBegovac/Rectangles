package begovac.rectangles.model;

import com.google.auto.value.AutoValue;

/** Created by juraj begovac on 26/07/2017. */
@AutoValue
public abstract class Rectangle {

  public static Rectangle create(int ordinalNumber, int color, int indexInList) {
    return builder().ordinalNumber(ordinalNumber).color(color).indexInList(indexInList).build();
  }

  public static Builder builder() {
    return new AutoValue_Rectangle.Builder();
  }

  public abstract int ordinalNumber();

  public abstract int color();

  public abstract int indexInList();

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder ordinalNumber(int ordinalNumber);

    public abstract Builder color(int color);

    public abstract Builder indexInList(int indexInList);

    public abstract Rectangle build();
  }
}
