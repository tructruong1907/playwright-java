package javaBasic;

public class Topic_04_Properties {
    static void main (String[] args) {
        System.out.println(System.getProperty("os.name")); //tên hệ điều hành
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(System.getProperty("java.version"));
    }
}
