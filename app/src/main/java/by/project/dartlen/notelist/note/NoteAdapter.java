package by.project.dartlen.notelist.note;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import by.project.dartlen.notelist.R;
import by.project.dartlen.notelist.data.model.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteHolder>{

    private List<Note> list = new ArrayList<>(0);
    private Context mContext;
    public NoteAdapter(Context context){
        mContext = context;
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent,false));
    }

    @Override
    public void onBindViewHolder(final NoteHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.text.setText(list.get(position).getEntry());

        //final RecyclerItem itemList = listItems.get(position);
        //holder.txtTitle.setText(itemList.getTitle());
        //holder.txtDescription.setText(itemList.getDescription());
        holder.option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Display option menu

                PopupMenu popupMenu = new PopupMenu(mContext, holder.option);
                popupMenu.inflate(R.menu.menu_note);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.mnu_item_save:
                                //Toast.makeText(mContext, "Saved", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.mnu_item_delete:
                                //Delete item
                                //listItems.remove(position);
                                notifyDataSetChanged();
                                //Toast.makeText(mContext, "Deleted", Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<Note> list){
        for (Note result : list) {
            add(result);
        }
    }

    public void add(Note r) {
        list.add(r);
        notifyItemInserted(list.size() - 1);
    }

    public void clearAll(){
        list.clear();
    }
}
