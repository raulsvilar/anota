package cade_a_nota.bigmini.com.br.cade_a_nota.views.activities;

import android.animation.Animator;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import butterknife.BindView;
import butterknife.ButterKnife;
import cade_a_nota.bigmini.com.br.cade_a_nota.R;
import cade_a_nota.bigmini.com.br.cade_a_nota.views.fragments.AboutFragment;
import cade_a_nota.bigmini.com.br.cade_a_nota.views.fragments.NotesFragment;
import cade_a_nota.bigmini.com.br.cade_a_nota.views.fragments.ProfileFragment;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ZXingScannerView.ResultHandler {
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);
        if (savedInstanceState == null) {
            replaceFragment(new NotesFragment());
            bottomNavigationView.getMenu().getItem(0).setChecked(true);
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_notes:
                replaceFragment(new NotesFragment());
                showFloatingButtonAnimatrion();
                break;
            case R.id.my_profile:
                replaceFragment(new ProfileFragment());
               hideFloatingButtonAnimatrion();
                break;
            case R.id.about:
                replaceFragment(new AboutFragment());
                hideFloatingButtonAnimatrion();
                break;
        }
        return false;
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
        //Toast.makeText(this, "oi", Toast.LENGTH_SHORT).show();
    }


    public void startScanner(View view) {
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        List<BarcodeFormat> formats = new ArrayList<>();
        formats.add(BarcodeFormat.QR_CODE);
        mScannerView.setFormats(formats);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        /*
            Chama a proxima tela onde o usuario ira preencher as informações adcionais e salvar a nota
         */


        Log.e("QRCODE", result.getText());
    }


    public void showFloatingButtonAnimatrion() {
        floatingActionButton.setVisibility(View.VISIBLE);
        floatingActionButton.setAlpha(0f);
        floatingActionButton.setScaleX(0f);
        floatingActionButton.setScaleY(0f);
        floatingActionButton.animate()
                .alpha(1)
                .scaleX(1)
                .scaleY(1)
                .setDuration(300)
                .setInterpolator(new OvershootInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        floatingActionButton.animate()
                                .setInterpolator(new LinearOutSlowInInterpolator())
                                .start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();
    }


    public void hideFloatingButtonAnimatrion() {
        if (floatingActionButton.getVisibility() == View.VISIBLE) {
            floatingActionButton.animate()
                    .alpha(0)
                    .scaleX(0)
                    .scaleY(0)
                    .setDuration(300)
                    .setInterpolator(new LinearOutSlowInInterpolator())
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            floatingActionButton.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                            floatingActionButton.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    })
                    .start();
        }
    }

}
