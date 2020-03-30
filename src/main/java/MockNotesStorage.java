import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.List;

public class MockNotesStorage implements NotesStorage {

    private Multimap<String, Note> notesStorageMap = ArrayListMultimap.create();

    private boolean listStatus = true;

    @Override
    public boolean isEmpty() {
        return listStatus;
    }

    @Override
    public void add(Note note) {
        notesStorageMap.put(note.getName(),note);
        listStatus = false;
    }

    @Override
    public List<Note> getAllNotesOf(String name) {
        return (List<Note>) notesStorageMap.get(name);
    }

    @Override
    public void clear() {
        notesStorageMap.clear();
        listStatus = true;
    }
}
