package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실버1 - 25분
public class InsertOperator {
    static int N;
    static int[] num;
    static int[] op;
    static int[] targetOp;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    static void cal(int res, int numIdx) {
        if (targetOp[0] == op[0] && targetOp[1] == op[1] && targetOp[2] == op[2] && targetOp[3] == op[3]) {
            max = Math.max(max, res);
            min = Math.min(min, res);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (targetOp[i] == op[i]) continue;
            targetOp[i] += 1;
            cal(calculate(res, i, numIdx), numIdx + 1);
            targetOp[i] -= 1;
        }
    }

    static int calculate(int target, int op, int idx) {
        switch (op) {
            case 0: //add
                return target + num[idx];
            case 1: //sub
                return target - num[idx];
            case 2: //mul
                return target * num[idx];
            case 3: //div
                return target / num[idx];
            default:
                return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        num = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        op = new int[4];
        targetOp = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        cal(num[0], 1);

        System.out.println(max + "\n" + min);
    }
}
