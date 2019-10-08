package edu.cnm.deepdive.blackjack.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.blackjack.R;
import edu.cnm.deepdive.blackjack.model.pojo.HandWithCards;
import edu.cnm.deepdive.blackjack.viewmodel.MainViewModel;

public class PlayerHandFragment extends HandFragment {

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    MainViewModel viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    viewModel.getPlayerHand().observe(this, (handWithCards) -> {});


  }


  @Override
  public LiveData<HandWithCards> handToObserve(MainViewModel viewModel) {
    return viewModel.getPlayerHand();
  }

  @Override
  public int getLayout() {
    return R.layout.fragment_player_hand;
  }
}
