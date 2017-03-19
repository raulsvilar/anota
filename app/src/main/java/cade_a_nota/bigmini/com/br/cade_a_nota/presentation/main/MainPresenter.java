package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.main;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cade_a_nota.bigmini.com.br.cade_a_nota.Util.FirebaseNodes;

/**
 * Created by infra on 28/02/2017.
 */

class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    public FirebaseUser user;
    private FirebaseAuth mAuth;
    private ValueEventListener eventListener;

    @Override
    public void execute(MainContract.View view) {
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        this.view = view;
        listenerEmptyState();
    }


    @Override
    public void listenerEmptyState() {
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("tomanocu","tomanocu");
                if (dataSnapshot.getChildrenCount() == 0)
                    view.emptyState();
                else
                    view.noteList();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myRef.child(FirebaseNodes.NODE_NOTES).child(user.getUid()).addValueEventListener(eventListener);
    }

    @Override
    public void onStart() {
        if (mAuthListener == null) {
            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user1 = firebaseAuth.getCurrentUser();
                    if (user1 == null) {
                        view.singedOut();
                    } else {
                        user = user1;
                    }
                }
            };
        } else {
            mAuth.addAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onStop() {
        mAuth.removeAuthStateListener(mAuthListener);
        myRef.child(FirebaseNodes.NODE_NOTES).child(user.getUid()).removeEventListener(eventListener);
    }

}
