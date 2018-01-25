package by.project.dartlen.notelist.note;

import java.util.List;

import by.project.dartlen.notelist.base.BasePresenter;
import by.project.dartlen.notelist.base.BaseView;
import by.project.dartlen.notelist.data.model.Note;

public interface NoteContract {
    interface View extends BaseView<NoteContract.Presenter> {
        void showSnackbar(String text);
        void showNotes(List<Note> notes);
    }
    interface Presenter extends BasePresenter<NoteContract.View>{
        void fabClicked(String name, String text);
        void start();
    }
}
