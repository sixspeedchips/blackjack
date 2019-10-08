package edu.cnm.deepdive.blackjack.controller;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.blackjack.R;
import edu.cnm.deepdive.blackjack.model.pojo.HandWithCards;
import edu.cnm.deepdive.blackjack.viewmodel.MainViewModel;

public class DealerHandFragment extends HandFragment {

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    MainViewModel viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    viewModel.getDealerHand().observe(this, (handWithCards) -> {});
  }

  @Override
  public LiveData<HandWithCards> handToObserve(MainViewModel viewModel) {
    return viewModel.getDealerHand();
  }

  @Override
  public int getLayout() {
    return R.layout.fragment_hand;
  }
}
