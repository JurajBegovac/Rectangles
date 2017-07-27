package begovac.rectangles.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import begovac.rectangles.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Navigator, HasSupportFragmentInjector {
  
  @Inject lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
  
  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    
    if (savedInstanceState == null) {
      navigate(AppScreen.Home)
    }
  }
  
  override fun supportFragmentInjector(): AndroidInjector<Fragment> {
    return fragmentDispatchingAndroidInjector
  }
  
  override fun navigateToFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
  }
  
  override fun navigateToFragmentAndAddToBackStack(fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.container, fragment)
        .addToBackStack(null)
        .commit()
  }
}
