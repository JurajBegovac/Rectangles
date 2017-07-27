package begovac.rectangles.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.inject.Inject;

import begovac.rectangles.R;
import begovac.rectangles.model.Data;
import begovac.rectangles.repository.Repository;
import begovac.rectangles.ui.Navigator;
import begovac.rectangles.ui.choose.ChooseScreen;
import begovac.rectangles.ui.rectangles.RectanglesScreen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public class HomeFragment extends Fragment {

  @Inject Navigator navigator;
  @Inject Repository<Data> dataRepository;

  @BindView(R.id.btn_continue)
  Button btnContinue;

  Unbinder unbinder;

  public HomeFragment() {
    // Required empty public constructor
  }

  public static HomeFragment newInstance() {
    return new HomeFragment();
  }

  @Override
  public void onAttach(Context context) {
    AndroidSupportInjection.inject(this);
    super.onAttach(context);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home, container, false);
    unbinder = ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    btnContinue.setEnabled(containsSomeData());
    btnContinue.setClickable(containsSomeData());
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @OnClick(R.id.btn_start)
  public void onStartClicked() {
    navigator.navigate(ChooseScreen.create());
  }

  @OnClick(R.id.btn_continue)
  public void onContinueClicked() {
    navigator.navigate(RectanglesScreen.create());
  }

  private boolean containsSomeData() {
    return dataRepository.getFirst() != null;
  }
}
