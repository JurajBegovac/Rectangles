package begovac.rectangles.di;

import begovac.rectangles.ui.MainActivity;
import begovac.rectangles.ui.main.di.MainActivityModule;
import begovac.rectangles.ui.main.di.MainFragmentProvider;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/** Created by juraj begovac on 26/07/2017. */
@Module
public abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = {MainActivityModule.class, MainFragmentProvider.class})
  abstract MainActivity bindMainActivity();
}
