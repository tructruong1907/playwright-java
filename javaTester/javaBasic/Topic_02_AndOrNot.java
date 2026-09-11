package javaBasic;

public class Topic_02_AndOrNot {
    static void main(String[] args) {
        boolean employeeA, employeeB, result;

        employeeA = true;
        employeeB = false;
        result = (employeeA && employeeB);
        System.out.println(result);

        employeeA = false;
        employeeB = true;
        result = (employeeA || employeeB);
        System.out.println(result);
        System.out.println(!result);
    }
}
