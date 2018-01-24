package by.project.dartlen.notelist.di.module;

import by.project.dartlen.notelist.di.scope.ActivityScope;
import by.project.dartlen.notelist.note.NoteActivity;
import by.project.dartlen.notelist.note.NoteModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = {NoteModule.class, GreenDAOModule.class})
    abstract NoteActivity noteActivity();
}
