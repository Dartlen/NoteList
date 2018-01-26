package by.project.dartlen.notelist.note;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import by.project.dartlen.notelist.R;
import by.project.dartlen.notelist.data.model.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteHolder> implements Filterable {

    private List<Note> list = new ArrayList<>(0);
    private List<Note> listFiltered = new ArrayList<>(0);
    private Context mContext;
    private OnItemClicked onClick;

    public NoteAdapter(Context context){
        mContext = context;
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent,false));
    }

    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }

    @Override
    public void onBindViewHolder(final NoteHolder holder,final int position) {
        holder.name.setText(listFiltered.get(position).getName());
        if(listFiltered.get(position).getComplete()) {
            holder.text.setPaintFlags(holder.text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.name.setPaintFlags(holder.text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else {
            holder.text.setPaintFlags(0);
            holder.name.setPaintFlags(0);
        }
        holder.text.setText(listFiltered.get(position).getEntry());

        holder.option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onClick.onItemClick(position);

                PopupMenu popupMenu = new PopupMenu(mContext, holder.option);
                if(listFiltered.get(position).getComplete()) {
                    popupMenu.inflate(R.menu.menu_note_complete);
                }else {
                    popupMenu.inflate(R.menu.menu_note);
                }
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.mnu_item_complete:
                                onClick.onItemClickComplete(listFiltered.get(position));
                                break;
                            case R.id.mnu_item_edite:
                                onClick.onItemClickEdite(listFiltered.get(position));
                                break;
                            case R.id.mnu_item_delete:
                                onClick.onItemClickDelete(list.get(position));
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
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    listFiltered = list;
                } else {
                    ArrayList<Note> filteredList = new ArrayList<>();
                    for (Note androidVersion : list) {
                        if (androidVersion.getEntry().toLowerCase().contains(charString) ||
                                androidVersion.getName().toLowerCase().contains(charString) ) {

                            filteredList.add(androidVersion);
                        }
                    }
                    listFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listFiltered = (ArrayList<Note>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return listFiltered.size();
    }

    public void addAll(List<Note> list){
        for (Note result : list) {
            add(result);
        }
    }

    public void add(Note r) {
        listFiltered.add(r);
        list.add(r);
        notifyItemInserted(list.size() - 1);
        notifyItemInserted(listFiltered.size() - 1);

    }

    public void clearAll(){
        list.clear();
        listFiltered.clear();
    }

    public void checkedNote(Note data){
        for(Note result: list)
            if(data.getName().equals(result.getName())) {
                result.setComplete(data.getComplete());
                break;
            }
        notifyDataSetChanged();
    }

    public void deleteNote(Note data){
        for(Note result: list)
            if(data.getName().equals(result.getName())) {
                list.remove(result);
                listFiltered.remove(result);
                notifyItemRemoved(list.indexOf(result));
                notifyItemRangeChanged(list.indexOf(result), list.size());
                break;
            }
    }
    public void updateNote(Note data){
        for(Note result: list)
            if(data.getName().equals(result.getName())) {
                list.set(list.indexOf(result), data);
                listFiltered.set(list.indexOf(result), data);
                notifyDataSetChanged();
                break;
            }
    }

    public void append(Note r) {
        list.add(r);
        notifyItemInserted(list.size() - 1);
    }
}
