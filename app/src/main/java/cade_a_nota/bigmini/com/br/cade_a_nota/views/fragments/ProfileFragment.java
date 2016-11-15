package cade_a_nota.bigmini.com.br.cade_a_nota.views.fragments;


import android.support.v4.app.Fragment;

import com.jgabrielfreitas.layoutid.annotations.InjectLayout;

import butterknife.OnClick;
import cade_a_nota.bigmini.com.br.cade_a_nota.R;
import cade_a_nota.bigmini.com.br.cade_a_nota.core.fragments.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
@InjectLayout(layout = R.layout.fragment_profile)
public class ProfileFragment extends BaseFragment {
    public ProfileFragment() {
        // Required empty public constructor
    }

    @OnClick({R.id.excludeAccountButton})
    public void onClick() {

    }
}
