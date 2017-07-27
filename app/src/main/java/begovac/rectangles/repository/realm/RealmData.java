package begovac.rectangles.repository.realm;

import io.realm.RealmList;
import io.realm.RealmObject;

/** Created by juraj begovac on 27/07/2017. */
public class RealmData extends RealmObject {
  public int rows;
  public int columns;
  public RealmList<RealmRectangle> rectangles;
}
