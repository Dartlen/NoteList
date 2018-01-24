package by.project.dartlen.notelist.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import by.project.dartlen.notelist.data.model.DaoSession;
import by.project.dartlen.notelist.di.module.ActivityBindingModule;
import by.project.dartlen.notelist.di.module.ApplicationModule;
import by.project.dartlen.notelist.di.module.GreenDAOModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {ApplicationModule.class,
        GreenDAOModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication>{

    Context bindContext();
    DaoSession bindDaoSession();

    @Component.Builder
    interface Builder{
        @BindsInstance
        AppComponent.Builder application(Application application);
        AppComponent build();
    }
}
