package begovac.rectangles.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/** Created by juraj begovac on 26/07/2017. */
@Module
public class AppModule {

  @Provides
  @Singleton
  Context context(Application application) {
    return application;
  }
}
