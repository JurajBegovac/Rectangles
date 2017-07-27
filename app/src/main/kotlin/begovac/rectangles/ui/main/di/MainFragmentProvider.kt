package begovac.rectangles.ui.main.di

import begovac.rectangles.ui.choose.ChooseFragment
import begovac.rectangles.ui.home.HomeFragment
import begovac.rectangles.ui.rectangles.RectanglesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/** Created by juraj begovac on 26/07/2017.  */
@Module
abstract class MainFragmentProvider {
  
  @ContributesAndroidInjector
  abstract fun provideHomeFragmentFactory(): HomeFragment
  
  @ContributesAndroidInjector
  abstract fun provideChooseFragmentFactory(): ChooseFragment
  
  @ContributesAndroidInjector
  abstract fun provideRectanglesFragmentFactory(): RectanglesFragment
}
