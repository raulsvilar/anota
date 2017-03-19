package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.main;

/**
 * Created by infra on 28/02/2017.
 */

interface MainContract {
    interface View {
        void emptyState();

        void singedOut();

        void noteList();
    }

    interface Presenter {
        void execute(View view);

        void listenerEmptyState();

        void onStart();

        void onStop();
    }
}
