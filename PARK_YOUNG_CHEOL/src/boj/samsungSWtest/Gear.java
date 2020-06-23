package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실버1 - 1시간
public class Gear {
    static String[] gears = new String[5];
    static int[] score = {0, 1, 2, 4, 8};
    static int k;

    static void rotate(int idx, int dir, boolean l, boolean r) {
        // 현재 기어의 왼쪽, 오른쪽 기어 idx
        int leftIdx = idx - 1;
        int rightIdx = idx + 1;

        // 현재 기어와 왼쪽 기어 비교
        if (l && leftIdx >= 1 && gears[idx].charAt(6) != gears[leftIdx].charAt(2)) {
            // 다르면 왼쪽 기어 반대방향으로 회전
            rotate(leftIdx, -dir, true, false);
            /* 왼쪽 톱니바퀴에 진입한 후에 그 톱니바퀴가 현재 톱니바퀴가 되어 회전하는 작업을 "ㄱ"줄에서
            * 진행하므로 여기서 회전작업을 해버리면 중복 회전시키게 된다. */
//            gears[leftIdx] = clockRotate(gears[leftIdx], -dir);
        }

        if (r && rightIdx <= 4 && gears[idx].charAt(2) != gears[rightIdx].charAt(6)) {
            // 다르면 오른쪽 기어 반대방향으로 회전
            rotate(rightIdx, -dir, false, true);
//            gears[rightIdx] = clockRotate(gears[rightIdx], -dir);
        }

        // 현재 기어 회전시키기
        /* ㄱ */gears[idx] = clockRotate(gears[idx], dir);
    }

    static String clockRotate(String state, int dir) {
        if (dir == 1) { // 시계 방향
            return state.charAt(state.length() - 1) + state.substring(0, state.length() - 1);
        } else { // 반시계 방향
            return state.substring(1) + state.charAt(0);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        gears[1] = br.readLine();
        gears[2] = br.readLine();
        gears[3] = br.readLine();
        gears[4] = br.readLine();

        k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            rotate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), true, true);
        }

        int answer = 0;

        for (int i = 1; i <= 4; i++) {
            if (gears[i].charAt(0) == '1') {
                // S극이면 점수가 있음
                answer += score[i];
            }
        }

        System.out.println(answer);
    }
}
