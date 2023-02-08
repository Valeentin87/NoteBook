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
        CommandsSave commandsSave = CommandsSave.NONE;
        while (true) {
            String command = prompt("************** Добро пожаловать в записную книжку *************\n" +
                    "Введите команду:\nДобавить заметку:\n\t\t\t\t - CREATE\nПрочитать заметку:\n\t\t\t\t" +
                    " - READ\nУдалить заметку:\n\t\t\t\t - DELETE\n" +
                    "Подготовить к отправке:\n\t\t\t\t - SAVE\n"+
                    "Выйти:\n\t\t\t\t - EXIT\n" +
                    "Отредактировать заметку:\n\t\t\t\t - UPDATE\n" +
                    "Поле для ввода команды: ");
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
                        userController.updateNote(note);
                        System.out.println("Заметка отредактирована");

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case DELETE:
                    System.out.println("Ниже указан список имеющихся заметок: ");
                    userController.viewAllNotes();
                    String id2 = prompt("наберите порядковый номер заметки какую хотите удалить: ");
                    try {
                        userController.deleteNote(id2);
                        System.out.println("Заметка под номером "+id2+" успешно удалена");
                    } catch (Exception e){
                        throw new RuntimeException(e);
                    }
                    break;
                case SAVE:
                    String commandSave = prompt("***** Выберите вариант обработки данных для отправки (сохранения) ******\n" +
                            "Сохранить каждую заметку в отдельном фале:\n" +
                            "\t\t\t\t\t\t - SAVEEACHNOTE\n" +
                            "Сохранить записную книжку в архивном файле:\n" +
                            "\t\t\t\t\t\t - SAVETOZIP\n" +
                            "Отправить записную книжку на адрес электронной почты:\n" +
                            "\t\t\t\t\t\t - SENDTOWEB\n" +
                            "Выйти на уровень выше:\n" +
                            "\t\t\t\t\t\t - EXIT\nПоле для ввода команды: ");
                    commandsSave = CommandsSave.valueOf(commandSave);
                    if (commandsSave == CommandsSave.EXIT) break;
                    switch (commandsSave){
                        case SAVEEACHNOTE:
                            userController.saveEach();
                            break;
                        case SAVETOZIP:
                            userController.saveZip();
                            break;
                        case SENDTOWEB:
                            userController.sendWeb();
                    }

            }
        }
    }

    public static String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}