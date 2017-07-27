package begovac.rectangles.repository;

import javax.inject.Singleton;

import begovac.rectangles.model.Data;
import begovac.rectangles.repository.realm.RealmDataRepository;
import dagger.Module;
import dagger.Provides;

/** Created by juraj begovac on 26/07/2017. */
@Module
public class RepositoryModule {

  @Singleton
  @Provides
  Repository<Data> dataRepository() {
    return new RealmDataRepository();
  }
}
