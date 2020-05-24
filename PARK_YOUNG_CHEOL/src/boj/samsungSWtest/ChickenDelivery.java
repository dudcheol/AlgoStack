package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 1시간 15분
 * 매번 하는 실수 또 했다.......... ㅡㅡ 이제진짜하지말자!!
 */
public class ChickenDelivery {
    static int n;
    static int m;
    static int[][] map;
    static HashMap<Integer, int[]> house;
    static HashMap<Integer, int[]> chicken;
    static boolean[] selected;
    static int answer = Integer.MAX_VALUE;

    static void dfs(int selectedCnt, int selectPos /* 이거실수함!! */) {
        if (selectedCnt == m) {
            // 각 집당 선택된 m개 치킨집과의 거리 중 가까운 것들 구해서 더하기
            int chickenLength = 0;
            for (int i = 0; i < house.size(); i++) {
                int[] cHouse = house.get(i);
                int x = cHouse[0];
                int y = cHouse[1];
                int length = Integer.MAX_VALUE;
                int cnt = 0;
                for (int j = 0; j < chicken.size(); j++) {
                    if (cnt == m) break;
                    if (!selected[j]) continue;
                    int[] chickenPos = chicken.get(j);
                    length = Math.min(length, Math.abs(chickenPos[0] - x) + Math.abs(chickenPos[1] - y));
                    cnt++;
                }
                chickenLength += length;
            }
            answer = Math.min(answer, chickenLength);
            return;
        }

        // 치킨집 m개 선택하기
        for (int i = selectPos; i < chicken.size(); i++) {
            if (selected[i]) continue;
            selected[i] = true;
            dfs(selectedCnt + 1, i /* 다음 for문이 i에서부터 시작되도록 해야 중복을 막을 수 있다 */);
            selected[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        house = new HashMap<>();
        chicken = new HashMap<>();

        int houseId = 0;
        int chickenId = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    house.put(houseId++, new int[]{i, j});
                } else if (map[i][j] == 2) {
                    chicken.put(chickenId++, new int[]{i, j});
                }
            }
        }
        selected = new boolean[chicken.size()];

        dfs(0, 0);
        System.out.println(answer);
    }
}
