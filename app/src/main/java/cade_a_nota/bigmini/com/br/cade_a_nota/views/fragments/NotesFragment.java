package cade_a_nota.bigmini.com.br.cade_a_nota.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cade_a_nota.bigmini.com.br.cade_a_nota.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotesFragment extends Fragment {


    public NotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

}
