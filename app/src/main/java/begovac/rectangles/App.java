package begovac.rectangles;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import begovac.rectangles.di.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;

/** Created by juraj begovac on 26/07/2017. */
public class App extends Application implements HasActivityInjector {

  @Inject DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

  @Override
  public void onCreate() {
    super.onCreate();
    Realm.init(this);
    DaggerAppComponent.builder().application(this).build().inject(this);
  }

  @Override
  public AndroidInjector<Activity> activityInjector() {
    return dispatchingActivityInjector;
  }
}
