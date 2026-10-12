package javaKeywords;

public class Computer {
    //Access modifier - phạm vi truy cập
    //public - protected - default - private
    //private: 1 biến/hàm thì chỉ có clas hiện tại mới sử dụng được
   // private String ramName = "Kingston 8Gb";

    private void touchPC(){

    }

    //default - được phép truy cập trong cùng 1 package
    String ssd = "EVO";

    //protected - được phép truy cập qua package khác thông qua việc kế thừa
    protected String mainName = "";

    //public - class/package nào sử dụng cũng được
    public String lcdName = "Dell U1234";
    public void showLcdName(){

    };

    static void main(String[] args){
        Computer com = new Computer();
        //com.ramName = "Samsung 16Gb"; //có thể gọi ramName vì cùng chung 1 class Computer
        com.touchPC();
    }

    //biến - Variable/Properties
    //1- bíến non-static - không define từ khóa nào trong biến
    //Nếu muốn truy cập vào thì phải thông qua instnace/đối tượng, phải new class lên mới sử dụng được
    String fullName;
    int comSize = 15;

    //2- biến static - không cần thông qua đối tượng vẫn truy cập được
    //Chia sẻ dữ liệu cho tất cả các luồng (Thread)
    static String cityName = "Ho Chi Minh";

    //3- biến final - được sử dụng khi không muốn gán lại dữ liệu (dữ liệu cố định, không cần sửa)
    final String addressName = "456 Lê Lai";

    //4- biến toàn cục - xét trong phạm vi Class (global)
    //Trong 1 Class thì 1 biến được gọi là toàn cục khi được khai báo trong phạm vi class

    //5- biến cục bộ - xét trong phạm vi Class (local)
    //Khai báo ở phạm vị: trong hàm/tham số của hàm/trong khối lệnh
    public void printRam(String ramName){
        //biến ramName này khác biến ramName được khai báo ở trên

        String hddName="";

        for(int i=0; i<0; i++){
            System.out.println(i);
            System.out.println(ramName);
            System.out.println(hddName);

            if(i>0){
                int number = 5;

                System.out.println(ramName);
                System.out.println(hddName);
            }
        }
    }

    //hàm - Method/Function
    //1- hàm non-static - không có từ khóa "static"

    //2- hàm static
    //Gọi ra sử dụng ở phạm vi lớp
    public static void showLCD(){

    }

    //3 - Hàm final - không thể override - tinh abtraction (trừu tượng)
    //Không cho phép các lớp con viết lại hàm này, chỉ được sử dụng thôi
    public static final void showSSD(){

    }

    //4- hàm abstract - bắt buộc các lớp con phải viết lại
    //không có code bên trong, chỉ có tên hàm thôi
    //hàm abstract bị báo lỗi ở đây do hàm đang chứa không phải là abstract (hàm non-abstract k thể chứa hàm abstract)
    //public abstract void showNetwork();

    //Áp dụng được tính chất trừu tượng thể hiện qua abstract và interface
    //chứa hàm abstract

    //lớp - Class - đơn kế thừa (sử dung extends)
    //1- Final class - không cho bất kỳ lớp nào kế thừa nó cả
    //2- abstract class - chứa cả non-abstract method và abstract method
    //3- non-abstract class - chỉ được phép chứa non-abstract method thôi

    //interface - sử dụng implements
    // có chức năng giống abstract class nhưng không chứa non-abstract method
    //Cho phép đa kế thừa: 1 class có thể kế thừa n interface

}
