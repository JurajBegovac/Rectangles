package begovac.rectangles.ui.choose

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import begovac.rectangles.R
import begovac.rectangles.model.Data
import begovac.rectangles.model.Rectangle
import begovac.rectangles.repository.Repository
import begovac.rectangles.ui.AppScreen
import begovac.rectangles.ui.Navigator
import begovac.rectangles.ui.navigate
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_choose.*
import java.util.*
import javax.inject.Inject

class ChooseFragment : Fragment() {
  
  companion object {
    fun newInstance(): ChooseFragment {
      return ChooseFragment()
    }
  }
  
  @Inject lateinit var navigator: Navigator
  @Inject lateinit var dataRepository: Repository<Data>
  
  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }
  
  override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
      inflater.inflate(R.layout.fragment_choose, container, false)
  
  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    btn_draw.setOnClickListener {
      if (generateAndSaveData())
        navigator.navigate(AppScreen.Rectangles)
    }
  }
  
  private fun generateAndSaveData(): Boolean {
    val rows = et_num_rows.getValue()
    if (rows < 0) {
      et_num_rows.error = getString(R.string.choose_fragment_rows_error)
      return false
    }
    val columns = et_num_columns.getValue()
    if (columns < 0) {
      et_num_columns.error = getString(R.string.choose_fragment_column_error)
      return false
    }
    
    dataRepository.deleteAll()
    dataRepository.save(Data(rows, columns, generateRectangles(rows, columns)))
    return true
  }
  
  private fun generateRectangles(rows: Int, columns: Int): List<Rectangle> {
    val numOfItems = rows * columns
    val rnd = Random()
    
    return IntRange(0, numOfItems - 1)
        .map {
          val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
          Rectangle(it, color, it)
        }
  }
  
  private fun EditText.getValue(): Int {
    val rows = text.toString()
    if (rows.isEmpty()) return -1
    return Integer.parseInt(rows)
  }
}
