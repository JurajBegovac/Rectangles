package begovac.rectangles.ui.rectangles;

import com.google.auto.value.AutoValue;

import android.os.Parcelable;

import begovac.rectangles.ui.Navigator.AppScreen;

/** Created by juraj begovac on 26/07/2017. */
@AutoValue
public abstract class RectanglesScreen implements AppScreen, Parcelable {
  public static RectanglesScreen create() {
    return new AutoValue_RectanglesScreen();
  }

  @Override
  public Screen screen() {
    return Screen.RECTANGLES;
  }
}
