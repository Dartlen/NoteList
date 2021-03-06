package by.project.dartlen.notelist.note;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.colorpicker.ColorPickerDialog;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.project.dartlen.notelist.R;
import by.project.dartlen.notelist.data.model.Note;
import by.project.dartlen.notelist.di.scope.ActivityScope;
import dagger.android.support.DaggerFragment;

@ActivityScope
public class NoteFragment extends DaggerFragment implements NoteContract.View, OnItemClicked {

    @Inject
    public NoteFragment(){}

    @Inject
    NoteContract.Presenter mNotePresenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout relativeLayout;

    /*@BindView(R.id.InputDialog)
    TextView nameNote;

    @BindView(R.id.InputDialog2)
    TextView textNote;*/

    private EditText userInputDialogEditText;
    private EditText userInputDialogEditText2;
    private NoteAdapter noteAdapter;
    private AlertDialog alertDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mNotePresenter.takeView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_note, container, false);
        getActivity().invalidateOptionsMenu();
        ButterKnife.bind(this, root);

        initInputDialog();


        fab.setOnClickListener(view -> alertDialog.show());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        noteAdapter = new NoteAdapter(getContext());
        noteAdapter.setOnClick(this);
        recyclerView.setAdapter(noteAdapter);
        mNotePresenter.start();

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (noteAdapter != null) noteAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    private void initInputDialog(){
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getActivity());
        final View mView = layoutInflaterAndroid.inflate(R.layout.input_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getActivity());
        alertDialogBuilderUserInput.setView(mView);

        userInputDialogEditText = (EditText) mView.findViewById(R.id.InputDialog);
        userInputDialogEditText2 = (EditText) mView.findViewById(R.id.InputDialog2);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Ok", (dialogBox, id) -> mNotePresenter.fabClicked(userInputDialogEditText.getText().toString(),
                        userInputDialogEditText2.getText().toString()))

                .setNegativeButton("Cancel",
                        (dialogBox, id) -> {
                            dialogBox.cancel();
                            mNotePresenter.onClickedEditeNote(false, null);
                            clearInputDialog();
                        });

        alertDialog = alertDialogBuilderUserInput.create();
    }

    @Override
    public void clearInputDialog() {
        userInputDialogEditText.setText("");
        userInputDialogEditText2.setText("");
    }

    @Override
    public void showSnackbar(String text) {
        Snackbar.make(recyclerView, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void showNotes(List<Note> notes) {
        noteAdapter.clearAll();
        noteAdapter.notifyDataSetChanged();
        noteAdapter.addAll(notes);
    }

    @Override
    public void onItemClickDelete(Note data) {
        mNotePresenter.onItemDeleteClicked(data);
    }

    @Override
    public void onItemClickComplete(Note data) {
        mNotePresenter.onItemCompleteClicked(data);
    }

    @Override
    public void onItemClickedSetColor(Note data) {
        //mNotePresenter.onItemSelectClicked(data);
        showColourPicker(data);
    }

    @Override
    public void onItemClickEdite(Note data) {
        userInputDialogEditText.setText(data.getName());
        userInputDialogEditText2.setText(data.getEntry());
        alertDialog.show();
        mNotePresenter.onClickedEditeNote(true, data);
    }

    @Override
    public void checkedNote(Note data) {
        noteAdapter.checkedNote(data);
    }

    @Override
    public void deletedNote(Note data) {
        noteAdapter.deleteNote(data);
    }

    @Override
    public void updateNote(Note data) {
        noteAdapter.updateNote(data);
    }

    @Override
    public void appendNote(Note data) {
        noteAdapter.append(data);
    }

    @Override
    public void setColorNote(Note data) {

    }

    public void showColourPicker(Note data) {
        final ColorPickerDialog colorPickerDialog = new ColorPickerDialog();
        colorPickerDialog.initialize(R.string.colors,
                new int[] {
                        getResources().getColor(R.color.item),
                        getResources().getColor(R.color.colorAccent),
                        getResources().getColor(R.color.colorPrimaryDark),
                        getResources().getColor(R.color.green)


                }, getResources().getColor(R.color.cardview_light_background), 4, 2);

        colorPickerDialog.setOnColorSelectedListener(color -> {
            //noteAdapter.setColor(data, color);
            mNotePresenter.onSelectedColor(data, color);
        });

        android.app.FragmentManager fm = getActivity().getFragmentManager();
        colorPickerDialog.show(fm, "colorpicker");
    }

    @Override
    public void showSetColor(Note data) {
        noteAdapter.updateNote(data);
    }
}
