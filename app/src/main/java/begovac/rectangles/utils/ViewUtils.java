package begovac.rectangles.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/** Created by juraj begovac on 27/07/2017. */
public class ViewUtils {

  public static void hideKeyboard(Context ctx) {
    InputMethodManager inputManager =
        (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);

    // check if no view has focus:
    View v = ((Activity) ctx).getCurrentFocus();
    if (v == null) return;

    inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
  }
}
