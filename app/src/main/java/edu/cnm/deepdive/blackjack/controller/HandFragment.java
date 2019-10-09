package edu.cnm.deepdive.blackjack.controller;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.blackjack.R;
import edu.cnm.deepdive.blackjack.model.entity.Card;
import edu.cnm.deepdive.blackjack.model.pojo.HandWithCards;
import edu.cnm.deepdive.blackjack.viewmodel.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class HandFragment extends Fragment {

  private ArrayAdapter<Card> adapter;
  private MainViewModel viewModel;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(getLayout(), container, false);
    ListView cards = view.findViewById(R.id.cards);
    adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1);
    cards.setAdapter(adapter);

    return view;
  }


  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    handToObserve(viewModel).observe(this, (handWithCards) -> {
      adapter.clear();
      adapter.addAll(handWithCards.getCards());
    });
  }

  public abstract LiveData<HandWithCards> handToObserve(MainViewModel viewModel);

  public abstract int getLayout();


  protected MainViewModel getViewModel() {
    return viewModel;
  }


}
