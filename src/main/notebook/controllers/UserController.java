package controllers;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;

    }

    public void saveNote(Note note) {
        repository.CreateNote(note);
    }

    public void updateNote(Note note) {
        repository.NewVersionNote(note);
    }

    public void deleteNote(String id) {
        repository.delNoteRepo(id);
    }

    public void saveEach(){model.SaveModification.saveAllNote();}
    public void saveZip(){model.SaveModification.saveZipFile();}
    public void sendWeb(){
        String web = views.ViewUser.prompt("\t\t\t\t\tВведите адрес электронной почты, на которую необходимо отправить данные книжки");
        model.SaveModification.sendToWeb(web);
    }


    public void viewAllNotes() {
        List<Note> notes = repository.getAllNotes();
        for (Note note : notes) {
            System.out.println(note);
        }
    }


    public Note readNote(String noteId) throws Exception {
        List<Note> notes = repository.getAllNotes();
        for (Note note : notes) {
            if (note.getId().equals(noteId)) {
                return note;
            }
        }

        throw new Exception("User not found");
    }
}
