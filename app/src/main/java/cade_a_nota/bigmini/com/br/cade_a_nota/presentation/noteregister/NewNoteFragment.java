package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.noteregister;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cade_a_nota.bigmini.com.br.cade_a_nota.R;
import cade_a_nota.bigmini.com.br.cade_a_nota.model.Note;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewNoteFragment extends DialogFragment implements NoteRegisterContract.View {

    @BindView(R.id.noteDescription)
    EditText noteDescription;
    @BindView(R.id.textInputLayout)
    TextInputLayout noteName;
    @BindView(R.id.saveButton)
    AppCompatButton saveButton;

    private Note newNote;
    public static String URL = "URL";
    private NoteRegisterContract.Presenter presenter;

    public NewNoteFragment() {
        // Required empty public constructor
    }

    public static NewNoteFragment newInstance(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(URL, url);
        NewNoteFragment newNoteFragment = new NewNoteFragment();
        newNoteFragment.setArguments(bundle);
        return newNoteFragment;
    }

    public String getURL() {
        return getArguments().getString(URL);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter = new NoteRegisterPresenter(this);
        newNote = new Note();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFragmentTheme);
        View v = inflater.inflate(R.layout.fragment_new_note, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @OnClick({R.id.saveButton, R.id.cancelButton})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveButton:
                if (noteName.getEditText().getText() != null && noteName.getEditText().getText().toString().length() < 5) {
                    noteName.setError("Nome inválido");
                } else {
                    newNote.setNoteTitle(noteName.getEditText().getText().toString());
                    newNote.setNoteDescription(noteDescription.getText().toString());
                    newNote.setUrl(getURL());
                    presenter.saveNote(newNote);
                    disableSaveButton();
                }
                break;
            case R.id.cancelButton:
                getActivity().finish();
                dismiss();
                break;
        }
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getActivity(), "Nota Adicionada com sucesso", Toast.LENGTH_LONG).show();
        getActivity().finish();
        dismiss();
    }

    @Override
    public void onFail() {
        Toast.makeText(getActivity(), "Erro ao adicionar a nota, tente novamente", Toast.LENGTH_LONG).show();
    }

    private void enableSaveButton() {
        saveButton.setClickable(true);
    }

    private void disableSaveButton() {
        saveButton.setClickable(false);
        saveButton.setLongClickable(false);
    }
}
