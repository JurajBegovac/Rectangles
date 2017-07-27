package begovac.rectangles.ui

import android.support.v4.app.Fragment
import begovac.rectangles.ui.choose.ChooseFragment
import begovac.rectangles.ui.home.HomeFragment
import begovac.rectangles.ui.rectangles.RectanglesFragment

/** Created by juraj begovac on 26/07/2017.  */

sealed class AppScreen {
  object Home : AppScreen()
  object Choose : AppScreen()
  object Rectangles : AppScreen()
}

interface Navigator {
  fun navigateToFragment(fragment: Fragment)
  fun navigateToFragmentAndAddToBackStack(fragment: Fragment)
}

fun Navigator.navigate(screen: AppScreen): Unit = when (screen) {
  AppScreen.Home -> navigateToFragment(HomeFragment.newInstance())
  AppScreen.Choose -> navigateToFragmentAndAddToBackStack(ChooseFragment.newInstance())
  AppScreen.Rectangles -> navigateToFragmentAndAddToBackStack(RectanglesFragment.newInstance())
}
