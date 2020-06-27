package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 50분
 */
public class Thirty {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String nums = br.readLine();
        int[] numCnt = new int[10];

        int sum = 0;
        for (int i = 0; i < nums.length(); i++) {
            int num = nums.charAt(i) - '0';
            numCnt[num]++;
            sum += num;
        }

        if (numCnt[0] == 0 || sum % 3 != 0) System.out.println(-1);
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 9; i >= 0; i--) {
                for (int j = 0; j < numCnt[i]; j++) {
                    sb.append(i);
                }
            }
            System.out.println(sb);
        }
        br.close();
    }
}
