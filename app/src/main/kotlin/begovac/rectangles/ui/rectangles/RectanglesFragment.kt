package begovac.rectangles.ui.rectangles

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import begovac.rectangles.R
import begovac.rectangles.model.Data
import begovac.rectangles.model.Rectangle
import begovac.rectangles.repository.Repository
import begovac.rectangles.ui.rectangles.RectanglesAdapter.OnItemClickListener
import begovac.rectangles.utils.hideKeyboard
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_rectangles.*
import javax.inject.Inject

class RectanglesFragment : Fragment() {
  
  companion object {
    fun newInstance(): RectanglesFragment {
      return RectanglesFragment()
    }
  }
  
  @Inject lateinit var dataRepository: Repository<Data>
  
  private val itemsToSwap: SparseArray<Item> = SparseArray()
  
  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
    context?.hideKeyboard()
  }
  
  override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
      inflater.inflate(R.layout.fragment_rectangles, container, false)
  
  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val data = dataRepository.first
    if (data == null) {
      Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
      return
    }
    setGridView(data)
    btn_swap.setOnClickListener {
      if (itemsToSwap.size() < 2) {
        Toast.makeText(context,
                       R.string.rectangles_fragment_swap_error_not_selected,
                       Toast.LENGTH_SHORT).show()
        return@setOnClickListener
      }
      val rectanglesAdapter = grid.adapter as RectanglesAdapter
      // swap items
      val position1 = itemsToSwap.keyAt(0)
      val position2 = itemsToSwap.keyAt(1)
      rectanglesAdapter.swapItems(position1, position2)
      
      // un check swapped items so user can swap some other items
      itemsToSwap.clear()
      val items = rectanglesAdapter.items
      items[position1].isSelected = false
      items[position2].isSelected = false
      rectanglesAdapter.notifyItemChanged(position1)
      rectanglesAdapter.notifyItemChanged(position2)
      
      // update items
      updateItems()
    }
  }
  
  private fun setGridView(data: Data) {
    
    val onItemClickListener = object : OnItemClickListener {
      override fun onItemClicked(position: Int, item: Item) {
        val selected = !item.isSelected
        if (selected) {
          if (itemsToSwap.size() >= 2) {
            Toast.makeText(context,
                           R.string.rectangles_fragment_swap_error,
                           Toast.LENGTH_SHORT).show()
            return
          }
          itemsToSwap.append(position, item)
        } else {
          itemsToSwap.remove(position)
        }
        
        item.isSelected = selected
        (grid.adapter as RectanglesAdapter).notifyItemChanged(position)
      }
      
    }
    
    grid.apply {
      val gridlayoutManager = GridLayoutManager(context, data.columns)
      layoutManager = gridlayoutManager
      adapter = RectanglesAdapter(
          onItemClickListener, itemsFromRectangles(data.rectangles), data.rows)
    }
  }
  
  private fun itemsFromRectangles(rectangles: List<Rectangle>) =
      rectangles.map { Item(it.color, it.ordinalNumber, false) }
  
  private fun rectanglesFromItems(items: List<Item>) =
      items.mapIndexed { index, item ->
        Rectangle(item.ordinalNumber, item.color, index)
      }
  
  private fun updateItems() {
    val items = (grid.adapter as RectanglesAdapter).items
    val data = dataRepository.first!!.copy(rectangles = rectanglesFromItems(items))
    dataRepository.deleteAll()
    dataRepository.save(data)
  }
}
