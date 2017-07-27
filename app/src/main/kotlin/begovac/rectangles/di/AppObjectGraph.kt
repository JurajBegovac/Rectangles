package begovac.rectangles.di

import android.app.Application
import android.content.Context
import begovac.rectangles.App
import begovac.rectangles.repository.RepositoryModule
import begovac.rectangles.ui.MainActivity
import begovac.rectangles.ui.main.di.MainActivityModule
import begovac.rectangles.ui.main.di.MainFragmentProvider
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/** Created by juraj begovac on 27/07/2017. */

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class,
                             ActivityBuilder::class,
                             AppModule::class,
                             RepositoryModule::class))
interface AppComponent {
  
  fun inject(app: App)
  
  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: App): Builder
    
    fun build(): AppComponent
  }
}

@Module
class AppModule {
  
  @Provides
  @Singleton
  fun context(application: Application): Context {
    return application
  }
}

@Module
abstract class ActivityBuilder {
  
  @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class,
                                                MainFragmentProvider::class))
  abstract fun bindMainActivity(): MainActivity
}
