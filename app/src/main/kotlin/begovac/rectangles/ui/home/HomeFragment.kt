package begovac.rectangles.ui.home

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import begovac.rectangles.R
import begovac.rectangles.model.Data
import begovac.rectangles.repository.Repository
import begovac.rectangles.ui.AppScreen
import begovac.rectangles.ui.Navigator
import begovac.rectangles.ui.navigate
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment() {
  
  @Inject lateinit var navigator: Navigator
  @Inject lateinit var dataRepository: Repository<Data>
  
  companion object {
    fun newInstance(): HomeFragment {
      return HomeFragment()
    }
  }
  
  override fun onAttach(context: Context?) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }
  
  override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
      inflater.inflate(R.layout.fragment_home, container, false)
  
  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    btn_continue.isEnabled = containsSomeData()
    btn_continue.isClickable = containsSomeData()
    
    btn_start.setOnClickListener { navigator.navigate(AppScreen.Choose) }
    btn_continue.setOnClickListener { navigator.navigate(AppScreen.Rectangles) }
  }
  
  private fun containsSomeData() = dataRepository.first != null
  
}
