package begovac.rectangles.repository.realm;

import android.support.annotation.Nullable;

import java.util.List;

import begovac.rectangles.model.Data;
import begovac.rectangles.model.Rectangle;
import begovac.rectangles.repository.Repository;
import io.realm.Realm;
import io.realm.RealmList;
import java8.util.function.Function;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

/** Created by juraj begovac on 26/07/2017. */
public class RealmDataRepository implements Repository<Data> {

  @Nullable
  @Override
  public Data getFirst() {
    RealmData data = Realm.getDefaultInstance().where(RealmData.class).findFirst();

    if (data == null) return null;

    RealmList<RealmRectangle> realmRectangles = data.rectangles;

    List<Rectangle> rectangles =
        StreamSupport.stream(realmRectangles)
            .map(
                new Function<RealmRectangle, Rectangle>() {
                  @Override
                  public Rectangle apply(RealmRectangle realmRectangle) {
                    return Rectangle.create(
                        realmRectangle.getOrdinalNumber(),
                        realmRectangle.getColor(),
                        realmRectangle.getIndexInList());
                  }
                })
            .collect(Collectors.<Rectangle>toList());

    return Data.create(data.rows, data.columns, rectangles);
  }

  @Override
  public void save(Data data) {
    Realm realm = Realm.getDefaultInstance();

    realm.beginTransaction();
    RealmData realmData = realm.createObject(RealmData.class);
    realmData.rows = data.rows();
    realmData.columns = data.columns();

    for (Rectangle r : data.rectangles()) {
      RealmRectangle realmRectangle = realm.createObject(RealmRectangle.class);
      realmRectangle.setOrdinalNumber(r.ordinalNumber());
      realmRectangle.setColor(r.color());
      realmRectangle.setIndexInList(r.indexInList());
      realmData.rectangles.add(realmRectangle);
    }
    realm.commitTransaction();
  }

  @Override
  public void deleteAll() {
    Realm realm = Realm.getDefaultInstance();
    realm.beginTransaction();
    realm.deleteAll();
    realm.commitTransaction();
  }
}
