package BOJ;

import java.util.Scanner;

public class RoboticVacuum {
    static int n,m,r,c,d;
    static int posCnt = 0;
    static int[][] map;
    static boolean[][] visited;
    static int cnt = 0;
    // 북 동 남 서의 왼쪽 방향
    static int[] lr = { 0, -1, 0, 1};
    static int[] lc = { -1, 0, 1, 0};

    // 북 동 남 서의 뒤쪽 방향
    static int[] br = { 1, 0, -1, 0};
    static int[] bc = { 0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();
        d = sc.nextInt();
        map = new int[n][m];
        visited = new boolean[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                map[i][j] = sc.nextInt();
            }
        }
        clean(r,c,d);
        System.out.println(cnt);
   }
   static void clean(int r, int c, int d){
        posCnt = 0;
        if(map[r][c] == 0 && !visited[r][c]){
            visited[r][c] = true;
            cnt++;
        }
        rotateNext(r, c, d);
   }
   static void rotateNext(int r, int c, int d){
        int newR = lr[d]+r;
        int newC = lc[d]+c;
        if(map[newR][newC] == 0 && !visited[newR][newC]){
            clean(newR, newC, (d+3)%4);
        } else {
            if(posCnt==4){
                posCnt=0;
                int backR = br[d]+r;
                int backC = bc[d]+c;

                if(map[backR][backC]==1){
                    return;
                }
                rotateNext(backR, backC, d);
            } else {
                posCnt++;
                rotateNext(r, c, (d+3)%4);
            }
        }
   }
}
