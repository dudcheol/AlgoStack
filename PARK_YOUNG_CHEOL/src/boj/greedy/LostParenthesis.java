package boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실버2 - 27분
public class LostParenthesis {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        String numStr = s.replaceAll("[^0-9]", " ");
        String opStr = s.replaceAll("[0-9]", " ");

        StringTokenizer st = new StringTokenizer(numStr);
        int[] nums = new int[st.countTokens()];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(opStr);
        char[] ops = new char[st.countTokens()];
        for (int i = 0; i < ops.length; i++) {
            ops[i] = st.nextToken().charAt(0);
        }

        int plus = nums[0];
        int minus = 0;

        boolean isPlus = true; // t=양수 f=음수

        for (int i = 0; i < ops.length; i++) {
            if (isPlus && ops[i] == '+') {
                plus += nums[i + 1];
            } else {
                isPlus = false;
                minus += nums[i + 1];
            }
        }

        System.out.println(plus - minus);
    }
}
