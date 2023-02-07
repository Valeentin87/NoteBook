package controllers;

import model.Repository;
import model.Note;
import model.FileOperationImpl;

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

    public String updateNote(Note note) {
        return repository.NewVersionNote(note);
    }


    public void viewAllNotes() {
        List<Note> notes = repository.getAllNotes();
        for (Note note : notes) {
            System.out.println(note);
        }
    }

    /*
    public void updateNote(String noteId) {
        String noteHeading = views.ViewUser.prompt("Введите новый текст заголовка: ");
        String noteText = views.ViewUser.prompt("Введите новый текст заметки: ");
        String noteDate = views.ViewUser.prompt("Введите новую дату заметки: ");
        List<Note> notes = repository.getAllNotes();
        List<String> list = new ArrayList<>();
        for (Note note : notes) {
            if (note.getId().equals(noteId)) {
                note.setHeading(noteHeading);
                note.setTextNote(noteText);
                note.setDate(noteDate);
                list.add(model.NoteMapper.map(note));
                System.out.println("успешно!!!!!!!!!!!!!");
            }
            list.add(model.NoteMapper.map(note));
        }
        model.FileOperationImpl.saveAllLines(list);

    }

     */



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
