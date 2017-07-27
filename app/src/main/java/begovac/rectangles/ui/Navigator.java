package begovac.rectangles.ui;

/** Created by juraj begovac on 26/07/2017. */
public interface Navigator {

  void navigate(AppScreen screen);

  interface AppScreen {
    Screen screen();

    enum Screen {
      HOME,
      CHOOSE,
      RECTANGLES
    }
  }
}
