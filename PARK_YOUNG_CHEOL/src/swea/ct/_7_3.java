package swea.ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 과제3
 * m개의 원소를 가진 집합에서 n개의 원소를 가진 집합으로 가는 전사함수의 개수를 출력하는 프로그램을 작성하라.
 * m과 n의 값을 바꾸어 보면서 값이 너무 커지지 않는 입력의 범위가 어느 정도인지 확인해 보라.
 */
public class _7_3 {
    static int m, n;
    static int[] memoM;
    static int[] visitedN;
    static int answer;

    static void connect(int k) {
        if (k == m + 1) {
            // n함수에 모두 연결되어 있는지 확인
            for (int i = 1; i <= n; i++) {
                if (visitedN[i] == 0) return;
            }
            // n에 전부 연결이 되어 있다면
            for (int i = 1; i <= m; i++) {
                System.out.println(i + " -> " + memoM[i]);
            }
            System.out.println("------------------");
            answer++;
            return;
        }
        for (int i = 1; i <= n; i++) {
            memoM[k] = i;
            /* visitedN을 true/false로 하면, 중복의 경우를 생각할 수 없게 된다.
             * 따라서, 갯수를 카운트하여 0인 경우 연결되지 않았음을 판단한다. */
            visitedN[i] += 1;
            connect(k + 1);
            visitedN[i] -= 1;
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("전사함수 m, n을 입력하시오. ex)3 2");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        memoM = new int[m + 1];
        visitedN = new int[n + 1];

        connect(1);
        System.out.println("전사함수의 개수는 " + answer + "개 입니다.");
    }
}
