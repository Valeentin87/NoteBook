package model;

public class SaveModification {
    public static void saveAllNote() {
        System.out.println("Каждая заметка сохранена на диске в отдельный файл");
    }

    public static void saveZipFile(){
        System.out.println("записная книжка сохранена в формате ZIP");
    }


    public static void sendToWeb(String web) {
        System.out.println("\t\t\t\t\tЗаписная книжка отправлена по адресу:\n" +
                "\t\t\t\t\t"+web);;
    }
}
