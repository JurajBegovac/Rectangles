package begovac.rectangles.repository.realm

import begovac.rectangles.model.Data
import begovac.rectangles.model.Rectangle
import begovac.rectangles.repository.Repository
import io.realm.Realm

/** Created by juraj begovac on 26/07/2017.  */
class RealmDataRepository : Repository<Data> {
  
  override val first: Data?
    get() {
      val data = Realm.getDefaultInstance().where(RealmData::class.java).findFirst() ?: return null
      
      val rectangles = data.rectangles
          .map { Rectangle(it.ordinalNumber, it.color, it.indexInList) }
      
      return Data(data.rows, data.columns, rectangles)
    }
  
  override fun save(item: Data) {
    val realm = Realm.getDefaultInstance()
    
    realm.beginTransaction()
    val realmData = realm.createObject(RealmData::class.java)
    realmData.rows = item.rows
    realmData.columns = item.columns
    item.rectangles
        .forEach {
          val realmRectangle = realm.createObject(RealmRectangle::class.java)
          realmRectangle.ordinalNumber = it.ordinalNumber
          realmRectangle.color = it.color
          realmRectangle.indexInList = it.indexInList
          realmData.rectangles.add(realmRectangle)
        }
    realm.commitTransaction()
  }
  
  override fun deleteAll() {
    val realm = Realm.getDefaultInstance()
    realm.beginTransaction()
    realm.deleteAll()
    realm.commitTransaction()
  }
}
