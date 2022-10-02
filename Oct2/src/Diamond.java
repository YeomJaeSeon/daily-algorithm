import java.util.Scanner;



    public class Diamond {
        public static void main(String[] args) {


            Scanner sc = new Scanner(System.in);
            int count = 0, i, j, l, num;
            System.out.print("다이아몬드 중앙 숫자입력(홀수만):");
            num = sc.nextInt();
            sc.nextLine();
            if (num % 2 == 0) {
                System.out.println("홀수만입력가능");
            } else {

                for (i = 1; i <= num; i = i + 2) {

                    for (j = num; j >= i;
                         j = j - 2)
                    {
                        System.out.print(" ");

                    }
                    for (l = 1; l <= i; l++) {
                        System.out.print("*");
                    }
                    System.out.println();
                }
                for (i = 1; i <= num - 2; i = i + 2) {

                    for(j = 1; j <= i;
                        j = j + 2)
                    {
                        System.out.print(" ");
                    }
                    System.out.print(" ");
                    for (l = num - 2; l >= i; l--) {
                        System.out.print("*");
                    }
                    System.out.println();
                }
            }
        }
    }


