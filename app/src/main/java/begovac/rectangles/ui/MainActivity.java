package begovac.rectangles.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import begovac.rectangles.R;
import begovac.rectangles.ui.choose.ChooseFragment;
import begovac.rectangles.ui.home.HomeFragment;
import begovac.rectangles.ui.home.HomeScreen;
import begovac.rectangles.ui.rectangles.RectanglesFragment;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity
    implements Navigator, HasSupportFragmentInjector {

  @Inject DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      navigate(HomeScreen.create());
    }
  }

  @Override
  public AndroidInjector<Fragment> supportFragmentInjector() {
    return fragmentDispatchingAndroidInjector;
  }

  @Override
  public void navigate(AppScreen screen) {
    switch (screen.screen()) {
      case HOME:
        navigateToFragment(HomeFragment.newInstance());
        break;
      case CHOOSE:
        navigateToFragmentAndAddToBackStack(ChooseFragment.newInstance());
        break;
      case RECTANGLES:
        navigateToFragmentAndAddToBackStack(RectanglesFragment.newInstance());
        break;
    }
  }

  private void navigateToFragment(Fragment fragment) {
    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
  }

  private void navigateToFragmentAndAddToBackStack(Fragment fragment) {
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.container, fragment)
        .addToBackStack(null)
        .commit();
  }
}
