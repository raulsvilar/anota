package cade_a_nota.bigmini.com.br.cade_a_nota.core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.facebook.FacebookSdk;
import com.jgabrielfreitas.layoutid.activity.InjectLayoutBaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cade_a_nota.bigmini.com.br.cade_a_nota.core.BaseFragment;

/**
 * Created by Kanda on 15/11/2016.
 */

public class CustomBaseActivity extends InjectLayoutBaseActivity {
    private boolean isButterKnifeBinded = false;
    private Unbinder unbinder;
    protected int containerId = 0;

    public CustomBaseActivity() {
    }

    protected void onStart() {
        super.onStart();
        try {
            bindButterKnife();
            modifyViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    protected void onStop() {
        try {
            unbindButterKnife();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onStop();
    }

    protected void bindButterKnife() {
        if (!isButterKnifeBinded) {
            unbinder = ButterKnife.bind(this);
            isButterKnifeBinded = true;
        }
    }

    protected void unbindButterKnife() {
        if (isButterKnifeBinded) {
            unbinder.unbind();
            isButterKnifeBinded = false;
        }
    }

    protected void modifyViews() {
    }

    public void replaceFragment(BaseFragment fragmentToReplace) {
        //loge("Replacing: " + fragmentToReplace.getClass().getSimpleName());
        getSupportFragmentManager().beginTransaction().replace(getContainerId(), fragmentToReplace).commit();
    }

    public void addFragmentAtStack(BaseFragment fragmentToAdd) {
        //loge("Adding: " + fragmentToAdd.getClass().getSimpleName());
        getSupportFragmentManager().beginTransaction().replace(getContainerId(), fragmentToAdd).addToBackStack(null).commit();
    }

    public int getContainerId() {
        return containerId;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }
}
