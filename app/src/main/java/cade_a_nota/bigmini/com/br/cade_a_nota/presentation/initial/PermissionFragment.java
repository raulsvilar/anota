package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.initial;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cade_a_nota.bigmini.com.br.cade_a_nota.R;
import cade_a_nota.bigmini.com.br.cade_a_nota.presentation.login.LoginFragment;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class PermissionFragment extends Fragment {

    @BindView(R.id.primaryAction)
    Button primaryAction;
    @BindView(R.id.secondaryAction)
    Button secondaryAction;

    public PermissionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_permission, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        execute();
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    public void permissionSuccess() {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, new LoginFragment()).commitAllowingStateLoss();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    public void neverAskAgain() {
        changeVisibility();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    public void onRationale(PermissionRequest request) {
        changeVisibility();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnClick(R.id.primaryAction)
    public void clickPrimaryAction() {
        PermissionFragmentPermissionsDispatcher.permissionSuccessWithCheck(this);
    }

    @OnClick(R.id.secondaryAction)
    public void clickSecondaryAction() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    private void changeVisibility() {
        if (primaryAction.getVisibility() == View.GONE) {
            primaryAction.setVisibility(View.VISIBLE);
            secondaryAction.setVisibility(View.GONE);
        } else if (secondaryAction.getVisibility() == View.GONE) {
            secondaryAction.setVisibility(View.VISIBLE);
            primaryAction.setVisibility(View.GONE);
        }
    }

    private void execute() {
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA);
        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
            changeVisibility();
        } else if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            permissionSuccess();
        }
    }
}
