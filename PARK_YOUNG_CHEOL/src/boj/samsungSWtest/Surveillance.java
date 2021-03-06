package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 1시간 15분 - 시간초과
 * 1시간 30분 - 정답!
 */
public class Surveillance {
    static int n;
    static int m;
    static int[][] map;
    static int cctvCnt = 0;
    static ArrayList<int[]> cctvPos;
    static int[] dx = {-1, 0, 1, 0};  // dx dy 90도 회전 -> 상 우 하 좌
    static int[] dy = {0, 1, 0, -1};
    static int answer = Integer.MAX_VALUE; /* 최솟값을 구해야 하므로 최댓값으로 초기화 */

    static int stoi(String str) {
        return Integer.parseInt(str);
    }

    // 깊은복사
    static int[][] deepCopy(int[][] targetMap) {
        int[][] res = new int[n][m];
        for (int i = 0; i < targetMap.length; i++)
            System.arraycopy(targetMap[i], 0, res[i], 0, targetMap[0].length);
        return res;
    }

    /**
     * 감시 가능 영역 표시하기
     * @param x cctv x좌표
     * @param y cctv y좌표
     * @param dir 영역을 표시할 방향 0:상,1:우,2:하,3:좌
     * @param map 영역을 표시할 map
     * @return 감시 가능 영역을 -1로 표시한 map
     */
    static int[][] drawLine(int x, int y, int dir, int[][] map) {
        int px = dx[dir];
        int py = dy[dir];

        x += px;
        y += py;
        while (x >= 0 && y >= 0 && x < n && y < m) {
            if (map[x][y] == 0) {
                map[x][y] = -1;
            } else if (map[x][y] == 6) { // 벽을 만나면 반복문 종료
                break;
            }
            x += px;
            y += py;
        }
        return map;
    }

    static void dfs(int k, int[][] changedMap) {
        if (k == cctvCnt) {
            // 0의 갯수를 센다
            int ans = 0;
            for (int[] m : changedMap) {
                for (int _m : m) {
                    if (_m == 0) ans++;
                }
            }
            answer = Math.min(answer, ans);
            return;
        }

        /* 여기에 for문이 있으면 안되지..
        * 왜? 이미 dfs로 진입해 온 지금의 시점에서 더 반복할건 없으니까
        * 단지 감시 가능 영역을 표시하는 작업만 하면 됨 */
        //for (int p = lastPos; p < cctvPos.size(); p++) {
        int[] cPos = cctvPos.get(k);
        int i = cPos[0];
        int j = cPos[1];

        switch (changedMap[i][j]) {
            case 1:
                dfs(k + 1, drawLine(i, j, 0, deepCopy(changedMap)));
                dfs(k + 1, drawLine(i, j, 1, deepCopy(changedMap)));
                dfs(k + 1, drawLine(i, j, 2, deepCopy(changedMap)));
                dfs(k + 1, drawLine(i, j, 3, deepCopy(changedMap)));
                break;
            case 2:
                int[][] upDownMap = deepCopy(changedMap);
                drawLine(i, j, 0, upDownMap);
                drawLine(i, j, 2, upDownMap);
                dfs(k + 1, upDownMap);

                int[][] leftRightMap = deepCopy(changedMap);
                drawLine(i, j, 1, leftRightMap);
                drawLine(i, j, 3, leftRightMap);
                dfs(k + 1, leftRightMap);
                break;
            case 3:
                int[][] upRight = deepCopy(changedMap);
                drawLine(i, j, 0, upRight);
                drawLine(i, j, 1, upRight);
                dfs(k + 1, upRight);

                int[][] rightDown = deepCopy(changedMap);
                drawLine(i, j, 1, rightDown);
                drawLine(i, j, 2, rightDown);
                dfs(k + 1, rightDown);

                int[][] leftDown = deepCopy(changedMap);
                drawLine(i, j, 3, leftDown);
                drawLine(i, j, 2, leftDown);
                dfs(k + 1, leftDown);

                int[][] leftUp = deepCopy(changedMap);
                drawLine(i, j, 3, leftUp);
                drawLine(i, j, 0, leftUp);
                dfs(k + 1, leftUp);
                break;
            case 4:
                int[][] lur = deepCopy(changedMap);
                drawLine(i, j, 3, lur);
                drawLine(i, j, 0, lur);
                drawLine(i, j, 1, lur);
                dfs(k + 1, lur);

                int[][] urd = deepCopy(changedMap);
                drawLine(i, j, 0, urd);
                drawLine(i, j, 1, urd);
                drawLine(i, j, 2, urd);
                dfs(k + 1, urd);

                int[][] rdl = deepCopy(changedMap);
                drawLine(i, j, 1, rdl);
                drawLine(i, j, 2, rdl);
                drawLine(i, j, 3, rdl);
                dfs(k + 1, rdl);

                int[][] dlu = deepCopy(changedMap);
                drawLine(i, j, 2, dlu);
                drawLine(i, j, 3, dlu);
                drawLine(i, j, 0, dlu);
                dfs(k + 1, dlu);
                break;
            case 5:
                int[][] urdl = deepCopy(changedMap);
                drawLine(i, j, 0, urdl);
                drawLine(i, j, 1, urdl);
                drawLine(i, j, 2, urdl);
                drawLine(i, j, 3, urdl);
                dfs(k + 1, urdl);
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        map = new int[n][m];
        cctvPos = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvCnt++;
                    cctvPos.add(new int[]{i, j});
                }
            }
        }

        dfs(0, map);
        System.out.println(answer);
    }
}
