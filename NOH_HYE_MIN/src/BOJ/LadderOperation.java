package BOJ;

import java.util.Scanner;

public class LadderOperation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 세로선
        int m = sc.nextInt(); // 가로선
        int h = sc.nextInt(); // 가로선을 넣을 수 있는 위치 개수

        boolean[][] horizontalLine = new boolean[h+1][n+1];
        for(int i=0; i<m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            horizontalLine[a][b+1] = true;
            horizontalLine[a][b] = true;
        }

        for(boolean[] v:horizontalLine){
            for(boolean v2:v){
                if(v2==false){
                    System.out.print('0');
                }else{
                    System.out.print('1');
                }
            }
            System.out.println();
        }
        // 가로줄을 한개씩 추가하며 모든 케이스 체크하기..?
        // 다리가 b, b+1사이에 가로선이 2개 있으면 항상 b->b, b+1->b+1로 움직이는듯?
        // 2개 2개 짝이 지어진다면, 엇갈려놓이지 않고 1번 2번 2번 1번 이렇게감싸지게 놓이면 됨...
        // 담을때 연결된 [ 세로줄 정보 b, b+1 묶고]key로 행정보를 value로 저장
        // 담은 정보와 규칙활용해서 가로 줄 추가 여부 선택스. 끝
    }
}
