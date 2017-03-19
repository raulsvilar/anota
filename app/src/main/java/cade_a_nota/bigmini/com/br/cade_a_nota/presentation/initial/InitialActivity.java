package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.initial;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;

import cade_a_nota.bigmini.com.br.cade_a_nota.R;
import cade_a_nota.bigmini.com.br.cade_a_nota.presentation.base.BaseActivity;
import cade_a_nota.bigmini.com.br.cade_a_nota.presentation.login.LoginActivity;
import cade_a_nota.bigmini.com.br.cade_a_nota.presentation.main.MainActivity;

public class InitialActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        execute();
    }

    public void execute() {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            addFragment(new PermissionFragment());
        }
    }
}
