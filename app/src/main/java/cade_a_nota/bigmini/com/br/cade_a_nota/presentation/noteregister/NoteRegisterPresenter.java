package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.noteregister;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cade_a_nota.bigmini.com.br.cade_a_nota.Util.FirebaseNodes;
import cade_a_nota.bigmini.com.br.cade_a_nota.model.Note;

/**
 * Created by Kanda on 11/03/2017.
 */

public class NoteRegisterPresenter implements NoteRegisterContract.Presenter {

    private final FirebaseUser user;
    private NoteRegisterContract.View view;
    private DatabaseReference myRef;

    public NoteRegisterPresenter(NoteRegisterContract.View view) {
        this.view = view;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void saveNote(Note note) {
        String id = myRef.push().getKey();
        note.setId(id);
        myRef.child(FirebaseNodes.NODE_NOTES).child(user.getUid()).child(id).setValue(note, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    view.onFail();
                } else {
                    view.onSuccess();
                }
            }
        });
    }
}
