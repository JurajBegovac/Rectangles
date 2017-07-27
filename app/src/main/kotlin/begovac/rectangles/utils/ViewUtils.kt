package begovac.rectangles.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

/** Created by juraj begovac on 27/07/2017.  */

fun Context.hideKeyboard() {
  val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  
  // check if no view has focus:
  val v = (this as Activity).currentFocus ?: return
  
  inputManager.hideSoftInputFromWindow(v.windowToken, 0)
}
