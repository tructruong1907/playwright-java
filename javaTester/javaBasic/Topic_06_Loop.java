package javaBasic;

import java.util.ArrayList;
import java.util.List;

public class Topic_06_Loop {
    static void main(String[] args) {
    //Trong trường hợp cần duyệt qua 1 ds dữ liệu
    //Duyệt qua hết toàn bộ
    //Gặp 1 điều kiện thỏa mãn thì dừng lại
    //Kết hợp cả vòng lặp vs điều kiện

    int employeeSize = 10;
    //for
    for( int i = 1; i <=employeeSize; i++) {
        if (i == 5) {
            System.out.println("Employee " + i);
        }

        List<String> productName =  new ArrayList<String>();
        productName.add("Product 1");
        productName.add("Product 2");
        productName.add("Product 3");

        for (i = 0; i < productName.size(); i++) {
            if(productName.get(i).equals("Product 1")) {
                System.out.println("Click to Edit Icon");
            }
        }

        //for - each
        for(String name: productName) {
            if(name.equals("Product 1")) {
                System.out.println("Click to Edit Icon");
            }
        }

        //While
        int x = 1;
        while(x < employeeSize){
            System.out.println("Employee " + x);
            x++;
        }

        //do-while
        int y = 0;
        do {
            System.out.println("Employee " + y);
            y++;
        }  while (y < employeeSize);

    }
}
}
