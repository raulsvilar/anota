package cade_a_nota.bigmini.com.br.cade_a_nota.core.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.jgabrielfreitas.layoutid.fragments.InjectLayoutBaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cade_a_nota.bigmini.com.br.cade_a_nota.core.activities.CustomBaseActivity;

/**
 * Created by Kanda on 15/11/2016.
 */

public class CustomBaseFragment extends InjectLayoutBaseFragment {
    CustomBaseActivity customBaseActivity;
    private Unbinder unbinder;

    public CustomBaseFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customBaseActivity = (CustomBaseActivity) getActivity();
    }

    public void onStart() {
        super.onStart();
        unbinder = ButterKnife.bind(this, getView());
        modifyViews();
    }

    public void onStop() {
        super.onStop();

        unbinder.unbind();
    }

    protected void modifyViews() {
    }

    public CustomBaseActivity getCustomBaseActivity() {
        return customBaseActivity;
    }

    public void closeKeyboard() {
        if (getActivity().getCurrentFocus() != null && getActivity().getCurrentFocus().getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }
}
