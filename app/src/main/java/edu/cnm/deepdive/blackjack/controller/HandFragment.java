package edu.cnm.deepdive.blackjack.controller;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.cnm.deepdive.blackjack.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HandFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_hand, container, false);



    return view;
  }

}
