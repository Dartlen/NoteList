package by.project.dartlen.notelist.note;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import by.project.dartlen.notelist.data.model.DaoSession;

public class NotePresenter implements NoteContract.Presenter {

    @Inject
    DaoSession daoSession;

    @Inject
    NotePresenter(){}

    @Nullable
    private NoteContract.View mNoteView;

    @Override
    public void takeView(NoteContract.View view) {
        mNoteView = view;
    }

    @Override
    public void dropView() {
        mNoteView = null;
    }
}
