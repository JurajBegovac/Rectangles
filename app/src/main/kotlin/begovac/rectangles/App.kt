package begovac.rectangles

import android.app.Activity
import android.app.Application
import begovac.rectangles.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.realm.Realm
import javax.inject.Inject

/** Created by juraj begovac on 26/07/2017.  */
class App : Application(), HasActivityInjector {
  
  @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
  
  override fun onCreate() {
    super.onCreate()
    Realm.init(this)
    DaggerAppComponent.builder().application(this).build().inject(this)
  }
  
  override fun activityInjector(): AndroidInjector<Activity> {
    return dispatchingActivityInjector
  }
}
