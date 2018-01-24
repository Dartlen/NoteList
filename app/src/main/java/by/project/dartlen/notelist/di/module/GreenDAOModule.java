package by.project.dartlen.notelist.di.module;

import android.content.Context;

import by.project.dartlen.notelist.data.model.DaoMaster;
import by.project.dartlen.notelist.data.model.DaoSession;
import dagger.Module;
import dagger.Provides;

@Module(includes = ApplicationModule.class)
public class GreenDAOModule {

    @Provides
    public DaoSession provideDaoSession(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "notes.db");
        return new DaoMaster(helper.getWritableDb()).newSession();
    }
}
