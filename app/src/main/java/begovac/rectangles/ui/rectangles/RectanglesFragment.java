package begovac.rectangles.ui.rectangles;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import begovac.rectangles.R;
import begovac.rectangles.model.Data;
import begovac.rectangles.model.Rectangle;
import begovac.rectangles.repository.Repository;
import begovac.rectangles.ui.rectangles.RectanglesAdapter.OnItemClickListener;
import begovac.rectangles.utils.ViewUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import java8.util.function.Function;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

public class RectanglesFragment extends Fragment {

  @Inject Repository<Data> dataRepository;

  @BindView(R.id.grid)
  RecyclerView grid;

  Unbinder unbinder;
  private RectanglesAdapter rectanglesAdapter;

  private SparseArray itemsToSwap = new SparseArray();

  public RectanglesFragment() {
    // Required empty public constructor
  }

  public static RectanglesFragment newInstance() {
    return new RectanglesFragment();
  }

  @Override
  public void onAttach(Context context) {
    AndroidSupportInjection.inject(this);
    super.onAttach(context);
    ViewUtils.hideKeyboard(context);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_rectangles, container, false);
    unbinder = ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Data data = dataRepository.getFirst();
    if (data == null) {
      Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
      return;
    }
    setGridView(data);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @OnClick(R.id.btn_swap)
  public void onSwapClicked() {
    if (itemsToSwap.size() < 2) {
      Toast.makeText(
              getContext(),
              R.string.rectangles_fragment_swap_error_not_selected,
              Toast.LENGTH_SHORT)
          .show();
      return;
    }

    // swap items
    int position1 = itemsToSwap.keyAt(0);
    int position2 = itemsToSwap.keyAt(1);
    rectanglesAdapter.swapItems(position1, position2);

    // un check swapped items so user can swap some other items
    itemsToSwap.clear();
    List<Item> items = rectanglesAdapter.getItems();
    items.get(position1).setSelected(false);
    items.get(position2).setSelected(false);
    rectanglesAdapter.notifyItemChanged(position1);
    rectanglesAdapter.notifyItemChanged(position2);

    // update items
    updateItems();
  }

  private void setGridView(Data data) {
    LayoutManager layoutManager = new GridLayoutManager(getContext(), data.columns());
    grid.setLayoutManager(layoutManager);

    OnItemClickListener onItemClickListener =
        new OnItemClickListener() {
          @Override
          public void onItemClicked(int position, Item item) {

            boolean selected = !item.isSelected();
            if (selected) {
              if (itemsToSwap.size() >= 2) {
                Toast.makeText(
                        getContext(), R.string.rectangles_fragment_swap_error, Toast.LENGTH_SHORT)
                    .show();
                return;
              }
              itemsToSwap.append(position, item);
            } else {
              itemsToSwap.remove(position);
            }

            item.setSelected(selected);
            rectanglesAdapter.notifyItemChanged(position);
          }
        };

    rectanglesAdapter =
        new RectanglesAdapter(
            onItemClickListener, itemsFromRectangles(data.rectangles()), data.rows());
    grid.setAdapter(rectanglesAdapter);
  }

  private List<Item> itemsFromRectangles(List<Rectangle> rectangles) {
    return StreamSupport.stream(rectangles)
        .map(
            new Function<Rectangle, Item>() {
              @Override
              public Item apply(Rectangle rectangle) {
                return new Item(rectangle.color(), rectangle.ordinalNumber(), false);
              }
            })
        .collect(Collectors.<Item>toList());
  }

  private void updateItems() {
    List<Item> items = rectanglesAdapter.getItems();
    Data data =
        dataRepository.getFirst().toBuilder().rectangles(rectanglesFromItems(items)).build();
    dataRepository.deleteAll();
    dataRepository.save(data);
  }

  private List<Rectangle> rectanglesFromItems(List<Item> items) {
    List<Rectangle> rectangles = new ArrayList<>(items.size());

    for (int i = 0; i < items.size(); i++) {
      Item item = items.get(i);
      rectangles.add(Rectangle.create(item.getOrdinalNumber(), item.getColor(), i));
    }
    return rectangles;
  }
}
