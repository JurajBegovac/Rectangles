package begovac.rectangles.ui.main.di

import begovac.rectangles.ui.MainActivity
import begovac.rectangles.ui.Navigator
import dagger.Module
import dagger.Provides

/** Created by juraj begovac on 26/07/2017.  */
@Module
class MainActivityModule {
  
  @Provides
  fun navigator(mainActivity: MainActivity): Navigator = mainActivity
}
