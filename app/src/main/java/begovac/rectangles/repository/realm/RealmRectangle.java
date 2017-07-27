package begovac.rectangles.repository.realm;

import io.realm.RealmObject;

/** Created by juraj begovac on 26/07/2017. */
public class RealmRectangle extends RealmObject {
  private int ordinalNumber;
  private int color;
  private int indexInList;

  public int getOrdinalNumber() {
    return ordinalNumber;
  }

  public void setOrdinalNumber(int ordinalNumber) {
    this.ordinalNumber = ordinalNumber;
  }

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public int getIndexInList() {
    return indexInList;
  }

  public void setIndexInList(int indexInList) {
    this.indexInList = indexInList;
  }
}
