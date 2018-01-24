package by.project.dartlen.notelist.note;

import android.provider.ContactsContract;

import by.project.dartlen.notelist.base.BasePresenter;
import by.project.dartlen.notelist.base.BaseView;

public interface NoteContract {
    interface View extends BaseView<NoteContract.Presenter> {

    }
    interface Presenter extends BasePresenter<NoteContract.View>{

    }
}
