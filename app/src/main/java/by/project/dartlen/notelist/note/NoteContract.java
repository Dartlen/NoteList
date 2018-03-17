package by.project.dartlen.notelist.note;

import java.util.List;

import by.project.dartlen.notelist.base.BasePresenter;
import by.project.dartlen.notelist.base.BaseView;
import by.project.dartlen.notelist.data.model.Note;

public interface NoteContract {
    interface View extends BaseView<NoteContract.Presenter> {
        void showSnackbar(String text);
        void showNotes(List<Note> notes);
        void clearInputDialog();
        void checkedNote(Note data);
        void deletedNote(Note data);
        void updateNote(Note data);
        void appendNote(Note data);
        void setColorNote(Note data);
        void showSetColor(Note data);
    }
    interface Presenter extends BasePresenter<NoteContract.View>{
        void fabClicked(String name, String text);
        void start();
        void onItemDeleteClicked(Note data);
        void onItemCompleteClicked(Note data);
        void onClickedEditeNote(boolean flag, Note data);
        void onItemSelectClicked(Note data);
        void onSelectedColor(Note data, int color);
    }
}
