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
 * -- 개선 --
 * 메모리 : 15884kb
 * 시간 : 340ms
 * 문제에서 "정답이 3보다 큰 값이면 -1을 출력한다. 또, 불가능한 경우에도 -1을 출력한다"라는 조건이 있으므로
 * 선을 0~3개 순서로 그리다가 한 번이라도 조건에 만족한다면 더이상 탐색할 필요가 없이 그것이 정답이 된다.
 * 따라서, 더이상의 탐색을 중단함으로써 시간을 개선했다.
 */
public class Ladder {
    static int N;
    static int M;
    static int H;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static int drawNumber = 1;

    // 가능한 모든 경우를 구하자
    static boolean dfs(int y, int addLine, int size /* 사다리에 그을 수 있는 선의 수 */) {
        // 기저
        if (addLine == size) {
            if (checkLadder()) {
                answer = Math.min(answer, addLine);
                return true;
            }
            return false;
        }

        // 사다리에 가로선 그리기
        for (int i = y; i <= H; i++) {
            for (int j = 1; j < N; j++) { // 마지막은 선을 그을 수 없으므로 j < N
                int current = map[i][j];
                int next = map[i][j + 1];
                if (current != -1 || next != -1) continue;
                addLine(i, j);
                /* 한 번이라도 조건에 만족해서 true가 리턴된다면 더이상 탐색할 필요 없음 */
                if (dfs(i, addLine + 1, size)) return true;
                removeLine(i, j);
            }
        }
        return false;
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

        /* 사다리에 선을 그을 수 있는 갯수를 지정 */
        for (int i = 0; i <= 3; i++)
            if (dfs(1, 0, i)) break;
        System.out.println(answer > 3 ? -1 : answer);
    }
}
