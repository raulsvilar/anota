package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cade_a_nota.bigmini.com.br.cade_a_nota.R;
import cade_a_nota.bigmini.com.br.cade_a_nota.presentation.base.BaseActivity;
import cade_a_nota.bigmini.com.br.cade_a_nota.presentation.initial.InitialActivity;
import cade_a_nota.bigmini.com.br.cade_a_nota.presentation.notelist.NotesFragment;
import cade_a_nota.bigmini.com.br.cade_a_nota.presentation.scan.ScanActivity;

public class MainActivity extends BaseActivity implements MainContract.View {
    @BindView(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;
    private MainContract.Presenter presenter;
    @BindView(R.id.ads)
    AdView ads;

    @Override
    protected void onStart() {
        super.onStart();
        presenter = new MainPresenter();
        presenter.onStart();
        presenter.execute(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MobileAds.initialize(this, "ca-app-pub-2158352779117766~4658949532");
        getSupportActionBar().setTitle("Minhas Notas");
        AdRequest adRequest = new AdRequest.Builder().build();
        ads.loadAd(adRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }


    @OnClick(R.id.floating_action_button)
    public void onClick() {
        startActivity(new Intent(this, ScanActivity.class));
    }


    @Override
    public void emptyState() {
        addFragment(new MainEmptyState());
        floatingActionButton.hide();
    }

    @Override
    public void singedOut() {
        Toast.makeText(this, "Por favor, faça login novamente", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, InitialActivity.class));
        finish();
    }

    @Override
    public void noteList() {
        addFragment(new NotesFragment());
        floatingActionButton.show();
    }

    public void checkPermissions() {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(this, InitialActivity.class));
            finish();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

    }
}
