package BOJ;

import java.awt.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

public class BabyShark {
    static int n;
    static int[][] map;
    static Shark shark;
    public static int[] dx = { 0, 0, 1, -1 };
    public static int[] dy = { 1, -1, 0, 0 };
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        map = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j]==9){
                    shark = new Shark(i, j);
                    map[i][j] = 0;
                }
            }
        }

        int sum = 0;
        int cnt = 1;
        while(cnt>0){
            cnt = bfs();
            if(cnt>0){
                sum+=cnt;
            }
        }
        System.out.println(sum);
    }
    public static int bfs() {
        int[][] check = new int[n][n]; // check 변수 초기화
        Queue<Point> q = new LinkedList<Point>();
        q.offer(new Point(shark.x, shark.y));
        check[shark.x][shark.y] = 1;

        int FishCheck = 0;
        Shark fish = new Shark(n, n);
        loop: while (!q.isEmpty()) {
            int r = q.peek().x;
            int c = q.poll().y;

            for (int d = 0; d < dx.length; d++) {
                int nr = r + dx[d];
                int nc = c + dy[d];

                // 지나갈 수 있는 곳: 자신보다 큰 물고기가 없는 곳
                if (isIn(nr, nc) && check[nr][nc] == 0 && map[nr][nc] <= shark.size) {
                    check[nr][nc] = check[r][c] + 1;
                    q.offer(new Point(nr, nc));

                    // 위치가 더 커질 경우, 더이상 확인할 필요 X
                    if (FishCheck != 0 && FishCheck < check[nr][nc]) {
                        break loop;
                    }

                    // 처음 먹을 수 있는 자기보다 물고기가 발견 되었을 때
                    if (0 < map[nr][nc] && map[nr][nc] < shark.size && FishCheck == 0) {
                        FishCheck = check[nr][nc];
                        fish.x = nr;
                        fish.y = nc;
                        fish.size = map[nr][nc];
                    }
                    // 같은 위치에 여러 마리 있을 경우, 가장 위의 가장 왼쪽 물고기부터 먹음
                    else if (FishCheck == check[nr][nc] && 0 < map[nr][nc] && map[nr][nc] < shark.size) {
                        if (nr < fish.x) { // 가장 위에 있는 거 우선권
                            fish.x = nr;
                            fish.y = nc;
                            fish.size = map[nr][nc];
                        } else if (nr == fish.x && nc < fish.y) { // 다 가장 위일 경우, 가장 왼쪽 우선권
                            fish.x = nr;
                            fish.y = nc;
                            fish.size = map[nr][nc];
                        }

                    }

                }else if(isIn(nr, nc) && check[nr][nc] == 0) {
                    check[nr][nc] = -1;
                }
            }
        }
        // idx 초과 안날 경우
        if (fish.x != n && fish.y != n) {
            eat(fish);
        }

        return (FishCheck - 1);
    }
    public static void eat(Shark fish){
        shark.x = fish.x;
        shark.y = fish.y;
        map[fish.x][fish.y] = 0;

        shark.ate++;
        if(shark.size<7 && shark.ate==shark.size){
            shark.ate = 0;
            shark.size++;
        }
    }
    public static boolean isIn(int x, int y){
        return(0<=x && x<n && 0<=y && y<n);
    }

}
class Shark{
    int x;
    int y;
    int size;
    int ate;
    Shark(int x, int y){
        this.x = x;
        this.y = y;
        this.size = 2;
        this.ate = 0;
    }
}
