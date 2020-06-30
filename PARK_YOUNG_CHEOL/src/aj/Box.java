package aj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Box {
    static int n;
    static int[] dice = new int[7]; // 주사위 면당 사용중인 색종이
    static HashMap<Integer, Integer> paper = new HashMap<>();
    static int[][] adjDice = {{}, {2, 3, 4, 6}, {1, 3, 5, 6}, {1, 2, 4, 5}, {1, 3, 5, 6}, {2, 3, 4, 6}, {1, 2, 4, 5}};

    static boolean dfs(int k) {
        if (k == 7) {
            for (int i = 1; i <= 6; i++) {
                for (int j = 0; j < 4; j++) {
                    if (dice[i] == dice[adjDice[i][j]]) {
                        return false;
                    }
                }
            }
            return true;
        }

        // 가지고있는 색종이 찾기
        for (int pk : paper.keySet()) {
            if (paper.get(pk) == 0) continue;
            // 주사위 k번째 면에 pk색종이 붙여보기
            dice[k] = pk;
            // 주사위의 k번째 면과 인접한 면들 중에 동일한 색종이가 있는지 확인
            boolean isSame = false;
            for (int m = 0; m < 4; m++) {
                if (pk == dice[adjDice[k][m]]) {
                    isSame = true;
                    break;
                }
            }
            // 동일한 색종이가 있으면 다른 색종이 선택
            if (isSame) {
                dice[k] = 0;
                continue;
            }
            // 동일한 색종이가 없으면 k면에 pk색종이 사용 가능
            paper.put(pk, paper.get(pk) - 1);
            if (dfs(k + 1)) return true;
            dice[k] = 0;
            paper.put(pk, paper.get(pk) + 1);
        }
        return false;
    }

    public static void main(String[] args) throws IOException {

        // Please Enter Your Code Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(st.nextToken());
            if (paper.containsKey(t)) {
                paper.put(t, paper.get(t) + 1);
            } else {
                paper.put(t, 1);
            }
        }

        System.out.println(dfs(1) ? "YES" : "NO");
    }
}
