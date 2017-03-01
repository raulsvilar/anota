package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.notes;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cade_a_nota.bigmini.com.br.cade_a_nota.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewNoteFragment extends DialogFragment {

    public static String URL = "URL";

    public NewNoteFragment() {
        // Required empty public constructor
    }

    public static NewNoteFragment newInstance(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(URL, url);
        NewNoteFragment newNoteFragment = new NewNoteFragment();
        newNoteFragment.setArguments(bundle);
        return newNoteFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_note, container, false);
    }

}
