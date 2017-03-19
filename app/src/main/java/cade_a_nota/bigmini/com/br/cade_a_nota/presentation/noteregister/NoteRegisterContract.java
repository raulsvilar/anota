package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.noteregister;

import cade_a_nota.bigmini.com.br.cade_a_nota.model.Note;

/**
 * Created by Kanda on 11/03/2017.
 */

public interface NoteRegisterContract {
    interface View {
        void onSuccess();

        void onFail();
    }

    interface Presenter {
        void saveNote(Note note);
    }
}
