import java.util.Scanner;
public class Test {
    public static void main (String[] args){
        System.out.println("Введите выражение без пробелов, напр. 2*2 или V+IX ");
        Scanner input = new Scanner(System.in);
        String s1 = input.nextLine();
        System.out.println("Ответ: " + Main.calc(s1));
        }

    }
class Main{
    public static String calc(String s1){
        if(s1.matches(".*[^XVLCI0-9*/+-].*")){
            throw new IllegalArgumentException("Неверный формат ввода");
        }
        String begin = String.valueOf(s1.charAt(0));
        if(begin.matches("[-+/*]")){
            throw new IllegalArgumentException("Неверный формат ввода");
        }
        String operation = "";
        int num1, num2,  solution = 0; String romanSolution = null;
        boolean num1IsRoman = true, num2IsRoman = true;
        String [] numbers = s1.split("[-+/*]");
        if(numbers.length>=3){
            throw   new IllegalArgumentException("Вы можете совешить только одну операцию за раз");
        }

        if(s1.matches(".*[IXVLCivxlc].*")){
            if(numbers[0].matches(".*[0-9].*")){
                throw new IllegalArgumentException("Вы не можете одновременно использовать и римские и арабские цифры");
            }
            if(numbers[1].matches(".*[0-9].*")){
                throw new IllegalArgumentException("Вы не можете одновременно использовать и римские и арабские цифры");
            }
            if(numbers[0].matches(".*[^XVICL].*")){
                throw new IllegalArgumentException("Неверный формат ввода");
            }
            RomanDigit roman1 = RomanDigit.valueOf(numbers[0]);
            RomanDigit roman2 = RomanDigit.valueOf(numbers[1]);
            num1 = Integer.parseInt(roman1.getValue());
            num2 = Integer.parseInt(roman2.getValue());
        } else {
            num1 = Integer.parseInt(numbers[0]);
            num2 = Integer.parseInt(numbers[1]);

        }
        if (num1>10 || num2>10 || num1 == 0 || num2 == 0){
            throw new IllegalArgumentException("Числа должны быть не больше 10 и не меньше 1");

        }
        int x = numbers[0].length();

        if (x==1){
            operation = String.valueOf(s1.charAt(1));

        }
        if (x==2){
            operation = String.valueOf(s1.charAt(2));
        }
        if (x==3){
            operation = String.valueOf(s1.charAt(3));
        }

        switch (operation){
            case "+":
                solution = num1  + num2  ;
                break;
            case "-":
                solution = num1  - num2 ;
                break;
            case "*":
                solution = num1* num2;
                break;
            case "/":
                solution = num1 / num2;
                break;
        }
        if(s1.matches(".*[IXVivx].*")){
            if(solution<1){
                throw new IllegalArgumentException ("В римской системе нет нуля и отрицательных чисел");
            }
            romanSolution = toRoman(solution);
             return romanSolution;
        } else {
            String stringSolution = String.valueOf(solution);
            return stringSolution;
        }
    }
    static String toRoman(int number){
        for (RomanDigit romanDigit: RomanDigit.values()){
            if( Integer.parseInt(romanDigit.getValue()) == number){
                return romanDigit.getKey();
            }
        }
        return null;
    }
}