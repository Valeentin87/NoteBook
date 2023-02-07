package views;

import controllers.UserController;
import model.Note;


import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com = Commands.NONE;

        while (true) {
            String command = prompt("Введите команду:\nДобавить заметку - CREATE\nПрочитать заметку - READ\nВыйти - EXIT\n" +
                    "отредактировать заметку - UPDATE\n");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    String heading = prompt("Заголовок: ");
                    String textNote = prompt("Текст заметки: ");
                    String date = prompt("Дата записи: ");
                    userController.saveNote(new Note(heading,textNote,date));
                    break;
                case READ:
                    String id = prompt("Заметку под каким номером хотите прочитать: ");
                    try {
                        Note note = userController.readNote(id);
                        System.out.println(note);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;


                case UPDATE:
                    System.out.println("Ниже указан список имеющихся заметок: ");
                    userController.viewAllNotes();
                    String id1 = prompt("наберите порядковый номер какую хотите изменить: ");
                    try {
                        Note note = userController.readNote(id1);
                        System.out.println(note);
                        //note.setHeading(prompt("Введите новый текст заголовка: "));
                        //note.setTextNote(prompt("Введите новый текст заметки: "));
                        //note.setDate(prompt("Введите новую дату заметки: "));
                        userController.updateNote(note);
                        System.out.println("Заметка отредактирована");

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        }
    }

    public static String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}