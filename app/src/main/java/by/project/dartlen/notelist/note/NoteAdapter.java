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
    private String charString="";
    private List<Note> lis;
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
        if(listFiltered.size()==0)
            lis = list;
        if(list.get(position).getColor()!=0)
            holder.relative.setBackgroundColor(list.get(position).getColor());
        holder.name.setText(lis.get(position).getName());
        if(lis.get(position).getComplete()) {
            holder.text.setPaintFlags(holder.text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.name.setPaintFlags(holder.text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else {
            holder.text.setPaintFlags(0);
            holder.name.setPaintFlags(0);
        }
        holder.text.setText(lis.get(position).getEntry());

        holder.option.setOnClickListener(v -> {
            //onClick.onItemClick(position);
            PopupMenu popupMenu = new PopupMenu(mContext, holder.option);
            if(lis.get(position).getComplete()) {
                popupMenu.inflate(R.menu.menu_note_complete);
            }else {
                popupMenu.inflate(R.menu.menu_note);
            }
            popupMenu.setOnMenuItemClickListener(item -> {

                switch (item.getItemId()) {
                    case R.id.mnu_item_complete:
                        onClick.onItemClickComplete(lis.get(position));
                        break;
                    case R.id.mnu_item_edite:
                        onClick.onItemClickEdite(lis.get(position));
                        break;
                    case R.id.mnu_item_delete:
                        onClick.onItemClickDelete(list.get(position));
                        break;
                    case R.id.mnu_item_set_color:
                        onClick.onItemClickedSetColor(list.get(position));
                    default:
                        break;
                }
                return false;
            });
            popupMenu.show();
        });
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                charString = charSequence.toString();

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
        if(charString.isEmpty())
            return list.size();
        else
            return listFiltered.size();
    }

    public void addAll(List<Note> list){
        for (Note result : list) {
            add(result);
        }
    }

    public void add(Note r) {
        //listFiltered.add(r);
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
                //listFiltered.remove(result);
                notifyItemRemoved(list.indexOf(result));
                notifyItemRangeChanged(list.indexOf(result), list.size());
                notifyDataSetChanged();
                break;
            }
    }
    public void updateNote(Note data){
        for(Note result: list)
            if(data.getName().equals(result.getName())) {
                list.set(list.indexOf(result), data);
                //listFiltered.set(list.indexOf(result), data);
                notifyDataSetChanged();
                break;
            }
    }

    public void append(Note r) {
        list.add(r);
        //listFiltered.add(r);
        notifyItemInserted(list.size() - 1);
        notifyDataSetChanged();
    }


}
