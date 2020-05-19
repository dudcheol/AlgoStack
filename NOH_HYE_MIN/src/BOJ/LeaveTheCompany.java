package BOJ;
import java.util.Scanner;

public class LeaveTheCompany {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] T = new int[N+5];
        int[] P = new int[N+5];
        for(int i=0; i<N; i++){
            T[i] = sc.nextInt();
            P[i] = sc.nextInt();
        }

        int[] maxPay = new int[N+5];
        int max = 0;
        for(int day=0; day<N+1; day++){
            maxPay[day] = Math.max(max, maxPay[day]);
            if(day+T[day]<=N+1){
                maxPay[day+T[day]] = Math.max(maxPay[day+T[day]], maxPay[day]+P[day]);
            }
            if(max<maxPay[day]){
                max = maxPay[day];
            }
        }
        System.out.println(max);
    }
}
