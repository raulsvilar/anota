package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


/**
 * Created by Kanda on 09/11/2016.
 */

class LoginPresenter {
    private LoginPresenter.LoginStatus status;
    private static final String TAG = LoginPresenter.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    static final int RC_SIGN_IN = 1000;

    LoginPresenter(LoginPresenter.LoginStatus status) {
        this.status = status;
        this.mAuth = FirebaseAuth.getInstance();
        this.mAuthListener = authStateListener();
        this.mAuth.addAuthStateListener(this.mAuthListener);
    }


    void onStop() {
        if (this.mAuthListener != null) {
            this.mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    private FirebaseAuth.AuthStateListener authStateListener() {
        return firebaseAuth -> {
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
        };
    }

    interface LoginStatus {
        void loginWithSuccess();
    }
}
