package begovac.rectangles.model;

import com.google.auto.value.AutoValue;

import java.util.List;

/** Created by juraj begovac on 26/07/2017. */
@AutoValue
public abstract class Data {

  public static Data create(int rows, int columns, List<Rectangle> rectangles) {
    return builder().rows(rows).columns(columns).rectangles(rectangles).build();
  }

  public static Builder builder() {
    return new AutoValue_Data.Builder();
  }

  public abstract int rows();

  public abstract int columns();

  public abstract List<Rectangle> rectangles();

  public abstract Data.Builder toBuilder();

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder rows(int rows);

    public abstract Builder columns(int columns);

    public abstract Builder rectangles(List<Rectangle> rectangles);

    public abstract Data build();
  }
}
