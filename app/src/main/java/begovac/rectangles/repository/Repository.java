package begovac.rectangles.repository;

import android.support.annotation.Nullable;

/** Created by juraj begovac on 26/07/2017. */
public interface Repository<Item> {

  @Nullable
  Item getFirst();

  void save(Item item);

  void deleteAll();
}
