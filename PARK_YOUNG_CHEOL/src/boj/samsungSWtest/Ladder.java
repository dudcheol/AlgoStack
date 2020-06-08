package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1시간 25분
 * 메모리 : 16564kb
 * 시간 : 1348ms
 *
 */
public class Ladder {
    static int N;
    static int M;
    static int H;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static int drawNumber = 1;

    // 가능한 모든 경우를 구하자
    static void dfs(int y, int addLine) {
        // 기저
        if (addLine > 3 || checkLadder()) {
            answer = Math.min(answer, addLine);
            return;
        }

        // 사다리에 가로선 그리기
        for (int i = y; i <= H; i++) {
            for (int j = 1; j < N; j++) { // 마지막은 선을 그을 수 없으므로 j < N
                int current = map[i][j];
                int next = map[i][j + 1];
                if (current != -1 || next != -1) continue;
                addLine(i, j);
                dfs(i, addLine + 1);
                removeLine(i, j);
            }
        }
    }

    static void addLine(int y, int x) {
        drawNumber++;
        map[y][x] = drawNumber;
        map[y][x + 1] = drawNumber;
    }

    static void removeLine(int y, int x) {
        map[y][x] = -1;
        map[y][x + 1] = -1;
    }

    static boolean checkLadder() {
        // i가 i로 가는지 확인
        for (int x = 1; x <= N; x++) {
            int cursor = x;
            for (int y = 1; y <= H; y++) {
                int current = map[y][cursor];
                if (current != -1) {
                    // 연결된 선 찾으면 연결된 곳으로 이동
                    if (cursor - 1 >= 1 && map[y][cursor - 1] == current) {
                        // 좌로 연결된 경우
                        cursor = cursor - 1;
                    } else if (cursor + 1 <= N && map[y][cursor + 1] == current) {
                        // 우로 연결된 경우
                        cursor = cursor + 1;
                    }
                }
            }
            if (cursor != x) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H + 1][N + 1];
        // 맵 초기화
        for (int[] m : map) Arrays.fill(m, -1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = x1 + 1;
            map[y][x1] = drawNumber;
            map[y][x2] = drawNumber;
            drawNumber++;
        }

        dfs(1, 0);
        System.out.println(answer > 3 ? -1 : answer);
    }
}
