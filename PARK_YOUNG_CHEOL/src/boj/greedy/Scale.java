package boj.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 골드3 - 30분
 * 프로그래머스에서 풀어봤던 문제
 */
public class Scale {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        if (nums[0] != 1) {
            System.out.println(1);
            return;
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                // 마지막 원소이면 마지막 원소까지 더하고 종료
                sum += nums[i];
                break;
            }
            if ((sum += nums[i]) < nums[i + 1]) {
                // sum보다 작으면 종료하는게 맞지만,
                // sum의 바로 다음 숫자가 nums에 있다면 sum+1 또한 측정가능하므로
                // 종료하지 않는다.
                if (!(sum + 1 == nums[i + 1])) break;
            }
        }

        System.out.println(sum + 1);
    }
}
