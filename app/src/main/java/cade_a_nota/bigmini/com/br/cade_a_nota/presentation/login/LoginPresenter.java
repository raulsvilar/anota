package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import cade_a_nota.bigmini.com.br.cade_a_nota.R;


/**
 * Created by Kanda on 09/11/2016.
 */

class LoginPresenter implements GoogleApiClient.OnConnectionFailedListener, OnCompleteListener<AuthResult> {
    private FragmentActivity activity;
    private Fragment fragment;
    private LoginPresenter.LoginStatus status;
    private static final String TAG = LoginPresenter.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInOptions gso;
    static final int RC_SIGN_IN = 1000;

    LoginPresenter(FragmentActivity activity, Fragment fragment, LoginPresenter.LoginStatus status) {
        this.activity = activity;
        this.fragment = fragment;
        this.status = status;
        this.gso = configureGoogleSigIn();
        this.mGoogleApiClient = configureGoogleApiClient();
        this.mAuth = FirebaseAuth.getInstance();
        this.mAuthListener = authStateListener();
        this.mAuth.addAuthStateListener(this.mAuthListener);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    void onStop() {
        if (this.mAuthListener != null) {
            this.mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    private GoogleSignInOptions configureGoogleSigIn() {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .requestId()
                .build();
    }

    private GoogleApiClient configureGoogleApiClient() {
        if (mGoogleApiClient == null || !mGoogleApiClient.isConnected()) {
            mGoogleApiClient = new GoogleApiClient.Builder(activity)
                    .enableAutoManage(activity /* FragmentActivity */, this /* OnConnectionFailedListener */)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        }
        return mGoogleApiClient;
    }

    private FirebaseAuth.AuthStateListener authStateListener() {
        return new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.e("AUTH", firebaseAuth.toString());
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    status.loginWithSuccess();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    void startAuthWithGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        fragment.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    void authWithGoogle(final GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, this);
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (!task.isSuccessful()) {
            Log.w(TAG, "signInWithCredential", task.getException());
            Toast.makeText(activity, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    interface LoginStatus {
        void loginWithSuccess();
    }
}
