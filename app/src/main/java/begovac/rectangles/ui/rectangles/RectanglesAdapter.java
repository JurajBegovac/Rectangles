package begovac.rectangles.ui.rectangles;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import begovac.rectangles.R;
import butterknife.ButterKnife;

/** Created by juraj begovac on 27/07/2017. */
public class RectanglesAdapter extends RecyclerView.Adapter<RectanglesAdapter.RectangleViewHolder> {

  private final OnItemClickListener onItemClickListener;

  private List<Item> items;
  private int rows;

  public RectanglesAdapter(OnItemClickListener onItemClickListener, List<Item> items, int rows) {
    this.onItemClickListener = onItemClickListener;
    this.items = items;
    this.rows = rows;
  }

  @Override
  public RectangleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
    int measuredHeight = parent.getMeasuredHeight();
    view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, measuredHeight / rows));
    return new RectangleViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RectangleViewHolder holder, int position) {
    holder.bind(position, items.get(position), onItemClickListener);
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public void swapItems(int position1, int position2) {
    Collections.swap(items, position1, position2);
    notifyItemChanged(position1);
    notifyItemChanged(position2);
  }

  public List<Item> getItems() {
    return items;
  }

  public interface OnItemClickListener {
    void onItemClicked(int position, Item item);
  }

  public class RectangleViewHolder extends RecyclerView.ViewHolder {

    private RelativeLayout background;
    private TextView ordinalNumber;

    public RectangleViewHolder(View itemView) {
      super(itemView);
      background = ButterKnife.findById(itemView, R.id.background);
      ordinalNumber = ButterKnife.findById(itemView, R.id.tv_ordinal_number);
    }

    public void bind(final int position, final Item item, final OnItemClickListener listener) {
      background.setOnClickListener(
          new OnClickListener() {
            @Override
            public void onClick(View v) {
              listener.onItemClicked(position, item);
            }
          });
      background.setBackgroundColor(item.getColor());
      ordinalNumber.setTypeface(
          ordinalNumber.getTypeface(), item.isSelected() ? Typeface.BOLD : Typeface.NORMAL);
      ordinalNumber.setText(String.valueOf(item.getOrdinalNumber()));
    }
  }
}
