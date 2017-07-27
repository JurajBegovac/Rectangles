package begovac.rectangles.ui.main.di;

import begovac.rectangles.ui.Navigator;
import begovac.rectangles.ui.MainActivity;
import dagger.Module;
import dagger.Provides;

/** Created by juraj begovac on 26/07/2017. */
@Module
public class MainActivityModule {

  @Provides
  Navigator navigator(MainActivity mainActivity) {
    return mainActivity;
  }
}
