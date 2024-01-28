package ru.tarabne.data;

public enum Locale {
    RU("Зубенко Михаил Петрович",
            "mafioznik@mail.ru",
            "ул. Пушкина, д.32, кв. 74",
            "пр. Ленина, д.1, кв. 13"),
    EN("Zubenko Mikhail Petrovich",
            "mafioznik@yahoo.com",
            "2912 PHILADELPHIA PIKE CLAYMONT DE 19703-2517 USA",
            "310 HAINES ST NEWARK DE 19717-5229 USA");

    public final String fullName;
    public final String email;
    public final String currentAddress;
    public final String permanentAddress;


    Locale(String fullName, String email, String currentAddress, String permanentAddress) {
        this.fullName = fullName;
        this.email = email;
        this.currentAddress = currentAddress;
        this.permanentAddress = permanentAddress;
    }
}
