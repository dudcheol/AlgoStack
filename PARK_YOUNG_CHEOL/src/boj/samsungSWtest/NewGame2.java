package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 1시간 20분
 */
public class NewGame2 {
    static int n;
    static int k;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static Horse[] horses;
    static ArrayList<Horse>[][] mapList;
    static int answer = 0;

    static class Horse {
        int name;
        int x;
        int y;
        int dir;

        Horse(int name, int x, int y, int dir) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Horse)
                return this.name == ((Horse) obj).name;
            else return false;
        }
    }

    static void play() {
        boolean breakFlag = false;
        int turn = 1;

        while (!breakFlag) {
            // 종료조건 : 말이 4개 겹칠 때 종료
            if (turn > 1000) {
                answer = -1;
                break;
            }

            for (int i = 0; i < k; i++) { // 4개 말의 행동 반복
                ArrayList<Horse> checkBreakFlag = move(horses[i]);
                if (checkBreakFlag != null){
                    if (checkBreakFlag.size()>=4){
                        breakFlag = true;
                        answer = turn;
                        break;
                    }
                }
            }

            turn++;
        }
    }

    static int reverseDir(int dir) {
        if (dir == 1) {
            return 2;
        } else if (dir == 2) {
            return 1;
        } else if (dir == 3) {
            return 4;
        } else return 3;
    }

    static ArrayList<Horse> move(Horse horse) {
        int preX = horse.x;
        int preY = horse.y;
        int moveX = horse.x + dx[horse.dir - 1];
        int moveY = horse.y + dy[horse.dir - 1];

        if (moveX < 0 || moveY < 0 || moveX >= n || moveY >= n || map[moveX][moveY] == 2) {
            // 체스판을 벗어나는 경우, 파란색인 경우에는 A번 말의 이동 방향을 반대로 하고 한 칸 이동한다
            horse.dir = reverseDir(horse.dir);
            moveX = horse.x + dx[horse.dir - 1];
            moveY = horse.y + dy[horse.dir - 1];
        }
        if (moveX < 0 || moveY < 0 || moveX >= n || moveY >= n || map[moveX][moveY] == 2) {  // 반대로 이동했는데 또 이 조건에 걸리면?
            // 방향을 반대로 바꾼 후에 이동하려는 칸이 파란색, 체스판을 벗어나는 경우에는 이동하지 않고 가만히 있는다.
            return null;
        }

        // 말의 위에있는말까지 함께 이동
        ArrayList<Horse> newList = new ArrayList<>();
        ArrayList<Horse> newMoveList = new ArrayList<>();
        boolean flag = false;
        for (int i = 0; i < mapList[preX][preY].size(); i++) {
            if (mapList[preX][preY].get(i) == horse) flag = true;
            if (!flag) newList.add(mapList[preX][preY].get(i));
            else {
                newMoveList.add(mapList[preX][preY].get(i));
                mapList[preX][preY].get(i).x = moveX;
                mapList[preX][preY].get(i).y = moveY;
            }
        }
        mapList[preX][preY].clear();
        mapList[preX][preY].addAll(newList);

        int color = map[moveX][moveY];
        if (color == 1){
            Collections.reverse(newMoveList);
        }
        mapList[moveX][moveY].addAll(newMoveList);
        return mapList[moveX][moveY];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        mapList = new ArrayList[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                mapList[i][j] = new ArrayList<>();
            }
        }

        horses = new Horse[k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            horses[i] = new Horse(i + 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
            mapList[horses[i].x][horses[i].y].add(horses[i]);
        }

        play();
        System.out.println(answer);
    }
}
