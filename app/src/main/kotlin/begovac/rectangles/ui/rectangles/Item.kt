package begovac.rectangles.ui.rectangles

/** Created by juraj begovac on 27/07/2017.  */
class Item(var color: Int, var ordinalNumber: Int, var isSelected: Boolean) {
  
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other?.javaClass != javaClass) return false
    
    other as Item
    
    if (color != other.color) return false
    if (ordinalNumber != other.ordinalNumber) return false
    if (isSelected != other.isSelected) return false
    
    return true
  }
  
  override fun hashCode(): Int {
    var result = color
    result = 31 * result + ordinalNumber
    result = 31 * result + isSelected.hashCode()
    return result
  }
  
  override fun toString(): String {
    return "Item(color=$color, ordinalNumber=$ordinalNumber, isSelected=$isSelected)"
  }
}
