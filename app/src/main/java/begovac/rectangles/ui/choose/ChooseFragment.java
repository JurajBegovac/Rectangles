package begovac.rectangles.ui.choose;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import begovac.rectangles.R;
import begovac.rectangles.model.Data;
import begovac.rectangles.model.Rectangle;
import begovac.rectangles.repository.Repository;
import begovac.rectangles.ui.Navigator;
import begovac.rectangles.ui.rectangles.RectanglesScreen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public class ChooseFragment extends Fragment {

  @Inject Navigator navigator;
  @Inject Repository<Data> dataRepository;

  @BindView(R.id.et_num_rows)
  EditText etNumRows;

  @BindView(R.id.et_num_columns)
  EditText etNumColumns;

  @BindView(R.id.btn_draw)
  Button btnDraw;

  Unbinder unbinder;

  public ChooseFragment() {
    // Required empty public constructor
  }

  public static ChooseFragment newInstance() {
    return new ChooseFragment();
  }

  @Override
  public void onAttach(Context context) {
    AndroidSupportInjection.inject(this);
    super.onAttach(context);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_choose, container, false);
    unbinder = ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @OnClick(R.id.btn_draw)
  public void onBtnDrawClicked() {
    if (generateAndSaveData()) {
      navigator.navigate(RectanglesScreen.create());
    }
  }

  private int getRows() {
    String rows = etNumRows.getText().toString();
    if (rows.isEmpty()) return -1;
    return Integer.parseInt(rows);
  }

  private int getColumns() {
    String columns = etNumColumns.getText().toString();
    if (columns.isEmpty()) return -1;
    return Integer.parseInt(columns);
  }

  private void showRowError(String error) {
    etNumRows.setError(error);
  }

  private void showColumnError(String error) {
    etNumColumns.setError(error);
  }

  private boolean generateAndSaveData() {
    int rows = getRows();
    if (rows < 0) {
      showRowError(getString(R.string.choose_fragment_rows_error));
      return false;
    }
    int columns = getColumns();
    if (columns < 0) {
      showColumnError(getString(R.string.choose_fragment_column_error));
      return false;
    }

    dataRepository.deleteAll();
    dataRepository.save(Data.create(rows, columns, generateRectangles(rows, columns)));
    return true;
  }

  private List<Rectangle> generateRectangles(int rows, int columns) {
    int numOfItems = rows * columns;
    List<Rectangle> rectangles = new ArrayList<>(numOfItems);

    Random rnd = new Random();

    for (int i = 0; i < numOfItems; i++) {
      int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
      rectangles.add(Rectangle.create(i, color, i));
    }

    return rectangles;
  }
}
