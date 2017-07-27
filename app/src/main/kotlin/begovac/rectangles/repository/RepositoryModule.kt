package begovac.rectangles.repository

import begovac.rectangles.model.Data
import begovac.rectangles.repository.realm.RealmDataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/** Created by juraj begovac on 26/07/2017.  */
@Module
class RepositoryModule {
  
  @Singleton
  @Provides
  fun dataRepository(): Repository<Data> = RealmDataRepository()
}
