package javaBasic;

public class Topic_05_Condition {
    static void main (String[] args)
    {
        //if - chỉ có 1 điều kiện
        String fullName = "Cố Yến Thần";
        if (fullName.equals("Cố Yến Thần")){
            System.out.println("Đây là nhân vật chính");
        }

        //if - else - có 2 điều kiện
        String browserName = "Firefox";
        if (browserName.equals("Firefox")){
            System.out.println("Init firefox or Safari");
        } else{
            System.out.println("Init Chrome or Edge");
        }

        //if - else - if -else - có nhiều điều kiện lồng ghép
        float point = 5.0f;
        if(point < 5.0){
            System.out.println("Yếu");
        } else if (point >= 5.0 && point < 7.0){
            System.out.println("Trung Bình");
        } else if(point >=7.0 && point < 9.0){
            System.out.println("Khá");
        } else{
            System.out.println("Giói");
        }

        //switch - case
        String env = "Dev";
        String url = null;

        switch (env){
            case "local":
                url = "https://local:8888.com";
                break;
            case "Dev":
                url = "https://dev.rode.com/";
                break;
            case "QA":
                url = "https://qa.rode.com/";
                break;
            case "Prod":
                url = "https://rode.com/";
                break;
            default:
                throw new RuntimeException("Invalid env");
        }
        System.out.println("URL: " + url);

    }
}
