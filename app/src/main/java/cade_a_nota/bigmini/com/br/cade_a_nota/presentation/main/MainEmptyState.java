package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cade_a_nota.bigmini.com.br.cade_a_nota.R;
import cade_a_nota.bigmini.com.br.cade_a_nota.presentation.scan.ScanActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainEmptyState extends Fragment {


    public MainEmptyState() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_empty_notes, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @OnClick(R.id.addNote)
    public void onClick(View v) {
        startActivity(new Intent(getActivity(), ScanActivity.class));
    }
}
