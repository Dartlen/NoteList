package by.project.dartlen.notelist.note;

import android.support.v7.widget.RecyclerView;
import android.telecom.TelecomManager;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.project.dartlen.notelist.R;

public class NoteHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textViewHead)
    TextView name;

    @BindView(R.id.textViewDesc)
    TextView text;

    @BindView(R.id.textViewOptions)
    TextView option;

    public NoteHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
