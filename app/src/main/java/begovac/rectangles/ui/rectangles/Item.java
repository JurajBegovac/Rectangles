package begovac.rectangles.ui.rectangles;

/** Created by juraj begovac on 27/07/2017. */
public class Item {

  private int color;
  private int ordinalNumber;
  private boolean isSelected;

  public Item(int color, int ordinalNumber, boolean isSelected) {
    this.color = color;
    this.ordinalNumber = ordinalNumber;
    this.isSelected = isSelected;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public int getOrdinalNumber() {
    return ordinalNumber;
  }

  public void setOrdinalNumber(int ordinalNumber) {
    this.ordinalNumber = ordinalNumber;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public void setSelected(boolean selected) {
    isSelected = selected;
  }

  @Override
  public int hashCode() {
    int result = color;
    result = 31 * result + ordinalNumber;
    result = 31 * result + (isSelected ? 1 : 0);
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Item item = (Item) o;

    if (color != item.color) return false;
    if (ordinalNumber != item.ordinalNumber) return false;
    return isSelected == item.isSelected;
  }

  @Override
  public String toString() {
    return "Item{"
        + "color="
        + color
        + ", ordinalNumber="
        + ordinalNumber
        + ", isSelected="
        + isSelected
        + '}';
  }
}
