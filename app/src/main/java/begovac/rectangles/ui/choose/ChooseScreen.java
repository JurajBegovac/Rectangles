package begovac.rectangles.ui.choose;

import com.google.auto.value.AutoValue;

import android.os.Parcelable;

import begovac.rectangles.ui.Navigator.AppScreen;

/** Created by juraj begovac on 26/07/2017. */
@AutoValue
public abstract class ChooseScreen implements AppScreen, Parcelable {
  public static ChooseScreen create() {
    return new AutoValue_ChooseScreen();
  }

  @Override
  public Screen screen() {
    return Screen.CHOOSE;
  }
}
