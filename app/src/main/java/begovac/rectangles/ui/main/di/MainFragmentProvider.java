package begovac.rectangles.ui.main.di;

import begovac.rectangles.ui.choose.ChooseFragment;
import begovac.rectangles.ui.home.HomeFragment;
import begovac.rectangles.ui.rectangles.RectanglesFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/** Created by juraj begovac on 26/07/2017. */
@Module
public abstract class MainFragmentProvider {

  @ContributesAndroidInjector
  abstract HomeFragment provideHomeFragmentFactory();

  @ContributesAndroidInjector
  abstract ChooseFragment provideChooseFragmentFactory();

  @ContributesAndroidInjector
  abstract RectanglesFragment provideRectanglesFragmentFactory();
}
