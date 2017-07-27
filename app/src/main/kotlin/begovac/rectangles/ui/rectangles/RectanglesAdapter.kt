package begovac.rectangles.ui.rectangles

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.RelativeLayout
import android.widget.TextView
import begovac.rectangles.R
import java.util.*

/** Created by juraj begovac on 27/07/2017.  */
class RectanglesAdapter(private val onItemClickListener: RectanglesAdapter.OnItemClickListener,
                        val items: List<Item>,
                        private val rows: Int) : RecyclerView.Adapter<RectanglesAdapter.RectangleViewHolder>() {
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RectangleViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid, parent, false)
    val measuredHeight = parent.measuredHeight
    view.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, measuredHeight / rows)
    return RectangleViewHolder(view)
  }
  
  override fun onBindViewHolder(holder: RectangleViewHolder, position: Int) {
    holder.bind(position, items[position], onItemClickListener)
  }
  
  override fun getItemCount(): Int {
    return items.size
  }
  
  fun swapItems(position1: Int, position2: Int) {
    Collections.swap(items, position1, position2)
    notifyItemChanged(position1)
    notifyItemChanged(position2)
  }
  
  interface OnItemClickListener {
    fun onItemClicked(position: Int, item: Item)
  }
  
  inner class RectangleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    
    private val background: RelativeLayout = itemView.findViewById(R.id.background) as RelativeLayout
    private val ordinalNumber: TextView = itemView.findViewById(R.id.tv_ordinal_number) as TextView
    
    fun bind(position: Int, item: Item, listener: OnItemClickListener) {
      background.setOnClickListener { listener.onItemClicked(position, item) }
      background.setBackgroundColor(item.color)
      ordinalNumber.setTypeface(
          ordinalNumber.typeface, if (item.isSelected) Typeface.BOLD else Typeface.NORMAL)
      ordinalNumber.text = item.ordinalNumber.toString()
    }
  }
}
