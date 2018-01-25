package by.project.dartlen.notelist.note;

import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import by.project.dartlen.notelist.data.model.DaoSession;
import by.project.dartlen.notelist.data.model.Note;
import by.project.dartlen.notelist.data.model.NoteDao;

public class NotePresenter implements NoteContract.Presenter {

    @Inject
    DaoSession daoSession;

    private NoteDao noteDao;

    @Inject
    NotePresenter(){}

    @Nullable
    private NoteContract.View mNoteView;

    @Override
    public void takeView(NoteContract.View view) {
        mNoteView = view;
        noteDao = daoSession.getNoteDao();
    }

    @Override
    public void dropView() {
        mNoteView = null;
    }

    @Override
    public void start() {
        mNoteView.showNotes(takeNotes());
    }

    @Override
    public void fabClicked(String name, String text) {
        if(name.length()>3 && text.length()>3){
            Note tmpNote = new Note();
            tmpNote.setName(name);
            tmpNote.setEntry(text);
            appendNote(tmpNote);
            start();
            mNoteView.showSnackbar("Note saved");
        }else{
            mNoteView.showSnackbar("Low size name or note!");
        }
    }

    private List<Note> takeNotes(){
        return  noteDao.queryBuilder()
                .orderAsc(NoteDao.Properties.Name)
                .list();
    }

    private void appendNote(Note data){
        noteDao.insert(data);
    }

    private void updateNote(Note data){
        noteDao.update(data);
    }

}

