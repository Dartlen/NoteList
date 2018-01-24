package by.project.dartlen.notelist.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(active = true, nameInDb = "ListNote")
public class ListNote {

    private long noteId;

    @Id
    private Long id;

    @NotNull
    private String name;

    @ToOne(joinProperty = "noteId")
    private Note note;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1748025742)
    private transient ListNoteDao myDao;

    @Generated(hash = 151132423)
    public ListNote(long noteId, Long id, @NotNull String name) {
        this.noteId = noteId;
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 738194242)
    public ListNote() {
    }

    public long getNoteId() {
        return this.noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Generated(hash = 1056330060)
    private transient Long note__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1544665371)
    public Note getNote() {
        long __key = this.noteId;
        if (note__resolvedKey == null || !note__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            NoteDao targetDao = daoSession.getNoteDao();
            Note noteNew = targetDao.load(__key);
            synchronized (this) {
                note = noteNew;
                note__resolvedKey = __key;
            }
        }
        return note;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 372886723)
    public void setNote(@NotNull Note note) {
        if (note == null) {
            throw new DaoException(
                    "To-one property 'noteId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.note = note;
            noteId = note.getId();
            note__resolvedKey = noteId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 186445484)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getListNoteDao() : null;
    }

}
