package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * 골드4 - 3시간
 * 밑에 힌트있는데 안봤음.. 하..ㅡㅡ
 * 근데 애초에 너무 어렵게 푼듯.........ㅠㅠ
 */
public class DragonCurve {
    static int n;
    static int[][] dragonInfo;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static ArrayList<Dot> list;
    static int currentG;

    static class Dot {
        int x;
        int y;

        Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this.getClass() != obj.getClass()) return false;
            return (((Dot) obj).x == this.x) && (((Dot) obj).y == this.y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    // 기준점 x,y를 이용하여 드래곤커브를 시뮬레이션 한다.
    static void simulate(Dot sd, int g) {
        // 기저
        if (g == currentG) return;

        int size = list.size() - 2; // 마지막 값은 기준점이므로 제외
        for (int i = size; i >= 0; i--) {
            Dot d = list.get(i);

            int diffX = d.x - sd.x;
            int diffY = d.y - sd.y;

            list.add(new Dot(sd.x -/*실수*/ diffY, sd.y + diffX));
        }
        simulate(list.get(list.size() - 1), g + 1);
    }

    static boolean isSquare(Dot dot, HashSet<Dot> dots){
        int[][] square = {{0,-1},{1,-1},{1,0}};
        for (int i=0;i<square.length;i++){
            int x = dot.x+square[i][0];
            int y = dot.y+square[i][1];
            if (!dots.contains(new Dot(x,y))) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dragonInfo = new int[n][4];
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                dragonInfo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        HashSet<Dot> res = new HashSet<>();
        for (int i = 0; i < n; i++) {
            // 0세대 드래곤 커브의 좌표를 set에 넣음
            list = new ArrayList<>();

            int x0 = dragonInfo[i][0];
            int y0 = dragonInfo[i][1];
            int d = dragonInfo[i][2];
            currentG = dragonInfo[i][3];

            int x1 = x0 + dx[d];
            int y1 = y0 + dy[d];

            list.add(new Dot(x0, y0));
            list.add(new Dot(x1, y1));

            simulate(list.get(list.size() - 1), 0);

            HashSet<Dot> set = new HashSet<>(list);
            res.addAll(set);
        }

        // result를 가지고 사각형 만들어지는지 확인
        int answer = 0;
        for (Dot d : res) {
            if(isSquare(d, res)) answer++;
        }
        System.out.println(answer);
    }
}
