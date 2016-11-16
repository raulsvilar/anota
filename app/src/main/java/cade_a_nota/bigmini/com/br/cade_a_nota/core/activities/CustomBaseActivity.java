package cade_a_nota.bigmini.com.br.cade_a_nota.core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.facebook.FacebookSdk;
import com.jgabrielfreitas.layoutid.activity.InjectLayoutBaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cade_a_nota.bigmini.com.br.cade_a_nota.R;
import cade_a_nota.bigmini.com.br.cade_a_nota.core.fragments.BaseFragment;

/**
 * Created by Kanda on 15/11/2016.
 */

public class CustomBaseActivity extends InjectLayoutBaseActivity {
    protected int containerId = 0;

    public CustomBaseActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
    }

    public void replaceFragment(BaseFragment fragmentToReplace) {
        //loge("Replacing: " + fragmentToReplace.getClass().getSimpleName());
        getSupportFragmentManager().beginTransaction().replace(getContainerId(), fragmentToReplace).commit();
    }

    public void addFragmentAtStack(BaseFragment fragmentToAdd) {
        //loge("Adding: " + fragmentToAdd.getClass().getSimpleName());
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentToAdd).addToBackStack(null).commit();
    }

    public int getContainerId() {
        return containerId;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }
}
