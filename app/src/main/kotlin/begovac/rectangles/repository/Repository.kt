package begovac.rectangles.repository

/** Created by juraj begovac on 26/07/2017.  */
interface Repository<Item> {
  
  val first: Item?
  
  fun save(item: Item)
  
  fun deleteAll()
}
