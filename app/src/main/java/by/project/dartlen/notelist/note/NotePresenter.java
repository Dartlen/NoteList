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
    private boolean flagedite;
    private Note editeNote;

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
            if(flagedite){
                editeNote.setName(name);
                editeNote.setEntry(text);
                updateNote(editeNote);
                mNoteView.clearInputDialog();
                mNoteView.updateNote(editeNote);
            }else {
                Note tmpNote = new Note();
                tmpNote.setName(name);
                tmpNote.setEntry(text);
                tmpNote.setComplete(false);
                appendNote(tmpNote);
                //start();
                mNoteView.appendNote(tmpNote);
                mNoteView.clearInputDialog();
                mNoteView.showSnackbar("Note saved");
            }
        }else{
            mNoteView.showSnackbar("Short name or note!");
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

    @Override
    public void onItemDeleteClicked(Note data) {
        noteDao.delete(data);
        mNoteView.deletedNote(data);
        //start();

        //mNoteView.showSnackbar("Note deleted");
    }

    @Override
    public void onItemCompleteClicked(Note data) {
        if(data.getComplete()){
            data.setComplete(false);
        }else {
            data.setComplete(true);
        }
        updateNote(data);
        mNoteView.checkedNote(data);
        //start();
        //mNoteView.showSnackbar("Note updated");
    }

    @Override
    public void onClickedEditeNote(boolean flag, Note data) {
        flagedite = flag;
        editeNote = data;
    }
}

