package cade_a_nota.bigmini.com.br.cade_a_nota.views.activities;

import android.content.Intent;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.jgabrielfreitas.layoutid.annotations.InjectLayout;

import butterknife.BindView;
import butterknife.OnClick;
import cade_a_nota.bigmini.com.br.cade_a_nota.R;
import cade_a_nota.bigmini.com.br.cade_a_nota.core.activities.BaseActivity;
import cade_a_nota.bigmini.com.br.cade_a_nota.presenter.LoginPresenter;
import cade_a_nota.bigmini.com.br.cade_a_nota.views.MainActivity;

@InjectLayout(layout = R.layout.activity_login)
public class LoginActivity extends BaseActivity implements LoginPresenter.LoginStatus {
    private CallbackManager callbackManager;
    private LoginPresenter presenter;
    @BindView(R.id.loginFacebook)
    LoginButton loginButton;

    @Override
    protected void onStart() {
        super.onStart();
        presenter = new LoginPresenter(this);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(callbackManager, presenter.facebookCallback());
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void loginWithSucess() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LoginPresenter.RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                presenter.authWithGoogle(account);
            } else {
                showDialogMessage(getString(R.string.err_message_login));
            }
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @OnClick({R.id.googleLoginSignInButton})
    public void onClick(View v) {
        presenter.sigInWithGoogle();
    }
}
