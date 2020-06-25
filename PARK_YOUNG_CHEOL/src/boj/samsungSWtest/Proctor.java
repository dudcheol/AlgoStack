package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Proctor {
    static int n;
    static int[] people;
    static int b;
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        people = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        /* n=1,000,000, b=1, c=1 인 경우 총 감독관의 수는 1,000,000 * 1,000,000 = 1조
        * int가 제공할 수 있는 약 21억의 숫자를 넘어서므로 long을 사용해야 함 */
        long/* int 범위를 넘어갈 수 있음! */ answer = 0;
        for (int i = 0; i < n; i++) {
            int bSub = people[i] - b;
            answer += 1;

            if (bSub > 0) {
                answer += (bSub / c);
                if (bSub % c != 0) answer += 1;
            }
        }

        System.out.println(answer);
    }
}
