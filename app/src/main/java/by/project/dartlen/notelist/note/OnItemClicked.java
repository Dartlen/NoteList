package by.project.dartlen.notelist.note;

import by.project.dartlen.notelist.data.model.Note;

public interface OnItemClicked {
    void onItemClickDelete(Note data);
    void onItemClickComplete(Note data);
    void onItemClickEdite(Note data);
}
