package by.project.dartlen.notelist;

import by.project.dartlen.notelist.di.AppComponent;
import by.project.dartlen.notelist.di.DaggerAppComponent;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class App extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends  DaggerApplication> applicationInjector(){
        AppComponent appComponets = DaggerAppComponent.builder().application(this).build();
        appComponets.inject(this);
        return appComponets;
    }

}
