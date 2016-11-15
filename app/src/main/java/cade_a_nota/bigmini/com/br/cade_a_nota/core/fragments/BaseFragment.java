package cade_a_nota.bigmini.com.br.cade_a_nota.core.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import cade_a_nota.bigmini.com.br.cade_a_nota.core.activities.BaseActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {
    private BaseActivity baseActivity;
    public BaseFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.baseActivity = (BaseActivity) getActivity();
    }

    protected void instanceViews() {}

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }
    protected void showProgress() {
        getBaseActivity().showProgress();
    }

    protected void removeProgress() {
        getBaseActivity().removeProgress();
    }

    public <T extends View> T findView(int viewId) {
        return (T) getBaseActivity().findViewById(viewId);
    }
}
