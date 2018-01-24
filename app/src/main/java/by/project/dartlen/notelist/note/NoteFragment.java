package by.project.dartlen.notelist.note;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import by.project.dartlen.notelist.R;
import by.project.dartlen.notelist.di.scope.ActivityScope;
import dagger.android.support.DaggerFragment;

@ActivityScope
public class NoteFragment extends DaggerFragment implements NoteContract.View {

    @Inject
    public NoteFragment(){}

    @Inject
    NoteContract.Presenter mNotePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNotePresenter.takeView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_note, container, false);
        ButterKnife.bind(this, root);
        return root;
    }
}
