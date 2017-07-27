package begovac.rectangles.ui.home;

import com.google.auto.value.AutoValue;

import android.os.Parcelable;

import begovac.rectangles.ui.Navigator.AppScreen;

/** Created by juraj begovac on 26/07/2017. */
@AutoValue
public abstract class HomeScreen implements AppScreen, Parcelable {
  public static HomeScreen create() {
    return new AutoValue_HomeScreen();
  }

  @Override
  public Screen screen() {
    return Screen.HOME;
  }
}
