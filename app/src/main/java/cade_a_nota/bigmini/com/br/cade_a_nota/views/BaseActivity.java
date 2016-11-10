package cade_a_nota.bigmini.com.br.cade_a_nota.views;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import cade_a_nota.bigmini.com.br.cade_a_nota.R;

/**
 * Created by Kanda on 09/11/2016.
 */

public class BaseActivity extends AppCompatActivity {

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
}
