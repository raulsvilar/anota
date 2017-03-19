package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import cade_a_nota.bigmini.com.br.cade_a_nota.R;


/**
 * Created by Kanda on 09/11/2016.
 */

public class BaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    public void showDialogMessage(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.msg_title_dialog));
        builder.setMessage(msg);
        builder.setPositiveButton(getString(R.string.msg_close), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
    }

    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(this, "", getString(R.string.msg_loading), false, false);
        }
    }

    public void removeProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }


    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }

    public void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

    }
}
