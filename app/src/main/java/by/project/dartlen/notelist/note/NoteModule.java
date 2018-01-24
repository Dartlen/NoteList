package by.project.dartlen.notelist.note;

import by.project.dartlen.notelist.di.scope.ActivityScope;
import by.project.dartlen.notelist.di.scope.FragmentScope;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class NoteModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract NoteFragment noteFragment();

    @ActivityScope
    @Binds abstract NoteContract.Presenter notePresenter(NotePresenter presenter);
}
