package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.notelist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import cade_a_nota.bigmini.com.br.cade_a_nota.R;
import cade_a_nota.bigmini.com.br.cade_a_nota.Util.FirebaseNodes;
import cade_a_nota.bigmini.com.br.cade_a_nota.model.Note;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotesFragment extends Fragment {

    @BindView(R.id.noteList)
    RecyclerView noteList;


    public NotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_notes, container, false);
        ButterKnife.bind(this, v);
        noteList.setHasFixedSize(true);
        noteList.setLayoutManager(new LinearLayoutManager(getActivity()));
        execute();
        return v;
    }

    private void execute() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String user = mAuth.getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child(FirebaseNodes.NODE_NOTES).child(user);
        FirebaseRecyclerAdapter mAdapter = new FirebaseRecyclerAdapter<Note, ListHolder>(Note.class, R.layout.item_note_layout, ListHolder.class, myRef) {
            @Override
            protected void populateViewHolder(ListHolder viewHolder, final Note model, int position) {
                viewHolder.setName(model.getNoteTitle());
                viewHolder.itemView.setOnLongClickListener(v -> {
                    myRef.child(model.getId()).removeValue();
                    return true;
                });
            }
        };
        noteList.setAdapter(mAdapter);
    }
}
