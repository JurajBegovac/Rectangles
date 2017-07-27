package begovac.rectangles.di;

import javax.inject.Singleton;

import begovac.rectangles.App;
import begovac.rectangles.repository.RepositoryModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/** Created by juraj begovac on 26/07/2017. */
@Singleton
@Component(
  modules = {
    AndroidSupportInjectionModule.class,
    ActivityBuilder.class,
    AppModule.class,
    RepositoryModule.class
  }
)
public interface AppComponent {

  void inject(App app);

  @Component.Builder
  interface Builder {
    @BindsInstance
    Builder application(App application);

    AppComponent build();
  }
}
