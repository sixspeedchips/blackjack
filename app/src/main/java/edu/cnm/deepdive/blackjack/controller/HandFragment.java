package edu.cnm.deepdive.blackjack.controller;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class HandFragment extends Fragment {

  private ArrayAdapter<Card> adapter;
  private MainViewModel viewModel;
  private TextView hardValue;
  private TextView softValue;
  private TextView bustedValue;
  private TextView blackjackValue;
  private TextView hardSoftDivider;
  private HandWithCards hand;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(getLayout(), container, false);
    ListView cards = view.findViewById(R.id.cards);
    adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
    cards.setAdapter(adapter);
    hardValue = view.findViewById(R.id.hard_value);
    softValue = view.findViewById(R.id.soft_value);
    bustedValue = view.findViewById(R.id.busted_value);
    blackjackValue = view.findViewById(R.id.blackjack_value);
    hardSoftDivider = view.findViewById(R.id.hard_soft_divider);
    return view;
  }


  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    handToObserve(viewModel).observe(this, (handWithCards) -> {
      hand = handWithCards;
      adapter.clear();
      adapter.addAll(handWithCards.getCards());
      updateValues(handWithCards);
    });
  }

  protected void updateValues(HandWithCards handWithCards) {
    int hard = handWithCards.getHardValue();
    int soft = handWithCards.getSoftValue();

    int numberCards = handWithCards.getCards().size();
    bustedValue.setVisibility(View.GONE);
    hardValue.setVisibility(View.GONE);
    softValue.setVisibility(View.GONE);
    blackjackValue.setVisibility(View.GONE);
    hardSoftDivider.setVisibility(View.GONE);

    if (hard > 21) {
      bustedValue.setText(String.valueOf(hard));
      bustedValue.setVisibility(View.VISIBLE);
    }
    else if (soft == 21 && numberCards == 2) {
      blackjackValue.setVisibility(View.VISIBLE);
    } else {
      hardValue.setText(String.valueOf(hard));
      hardValue.setVisibility(View.VISIBLE);
      if(soft > hard){
        softValue.setText(String.valueOf(soft));
        softValue.setVisibility(View.VISIBLE);
        hardSoftDivider.setVisibility(View.VISIBLE);
      }
    }
  }

  public abstract LiveData<HandWithCards> handToObserve(MainViewModel viewModel);

  public abstract int getLayout();

  protected MainViewModel getViewModel() {
    return viewModel;
  }

  protected HandWithCards getHand() {
    return hand;
  }
}
