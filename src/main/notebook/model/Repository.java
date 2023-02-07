package model;

import java.util.List;

public interface Repository {
    List<Note> getAllNotes();
    String CreateNote(Note note);
    String NewVersionNote(Note note);
}
