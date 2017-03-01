package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.login;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import cade_a_nota.bigmini.com.br.cade_a_nota.R;
import cade_a_nota.bigmini.com.br.cade_a_nota.presentation.main.MainActivity;

import static android.app.Activity.RESULT_CANCELED;


public class LoginFragment extends Fragment implements LoginPresenter.LoginStatus {
    private LoginPresenter presenter;

    @BindView(R.id.logo)
    TextView logo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Baloo-Regular.ttf");
        logo.setTypeface(face);
        presenter = new LoginPresenter(getActivity(), this, this);
        presenter.startAuthWithGoogle();
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void loginWithSuccess() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LoginPresenter.RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                presenter.authWithGoogle(account);
            } else {
                Log.e("a", "a");
            }
        } else if (resultCode == RESULT_CANCELED) {
            Log.e("b", "b");
        }
    }
}
