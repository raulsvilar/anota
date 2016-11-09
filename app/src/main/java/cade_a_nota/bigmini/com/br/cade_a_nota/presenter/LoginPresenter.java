package cade_a_nota.bigmini.com.br.cade_a_nota.presenter;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cade_a_nota.bigmini.com.br.cade_a_nota.views.LoginActivity;

/**
 * Created by Kanda on 09/11/2016.
 */

public class LoginPresenter {
    private LoginActivity activity;
    private static final String TAG = "LOGIN";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public LoginPresenter(LoginActivity activity, FirebaseAuth firebaseAuth, FirebaseAuth.AuthStateListener mAuthListener) {
        this.activity = activity;
        this.mAuth = firebaseAuth;
        this.mAuth = FirebaseAuth.getInstance();
        this.mAuthListener = mAuthListener;
        this.mAuthListener = authStateListener();
        this.mAuth.addAuthStateListener(this.mAuthListener);
    }

    public FacebookCallback<LoginResult> facebookCallback() {
        return new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.e(TAG, "canceled");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("Error", error.getCause() + error.getMessage());
            }
        };
    }

    private FirebaseAuth.AuthStateListener authStateListener() {
        return new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    activity.onSucess();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }


    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void onStop() {
        if (this.mAuthListener != null) {
            this.mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public interface LoginStatus {
        void onSucess();
    }
}
