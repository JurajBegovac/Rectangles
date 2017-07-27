package begovac.rectangles.model

/** Created by juraj begovac on 27/07/2017. */

data class Rectangle(val ordinalNumber: Int, val color: Int, val indexInList: Int)

data class Data(val rows: Int, val columns: Int, val rectangles: List<Rectangle>)
