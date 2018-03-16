package by.project.dartlen.notelist.note;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import by.project.dartlen.notelist.R;
import dagger.android.support.DaggerAppCompatActivity;

public class NoteActivity extends DaggerAppCompatActivity {

    @Inject
    NoteFragment noteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction()
                                       .add(R.id.fragment, noteFragment)
                                       .addToBackStack("notefragment")
                                       .commit();

    }

    @Override
    public void onBackPressed() {

    }
}
