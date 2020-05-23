package BOJ;

import java.util.*;

public class DoubleArray {
    static int time = 0;
    static int r, c, k;
    static int[][] A = new int[101][101];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        k = sc.nextInt();
        for(int i=1; i<=3; i++){
            for(int j=1; j<=3; j++){
                A[i][j] = sc.nextInt();
            }
        }

        calc(A, 3, 3);
        System.out.println(time);
    }
    static void calc(int[][] A, int row, int col){
        if(A[r][c] == k || time>100){
            if(time>100){
                time = -1;
            }
            return;
        } else {
            if(row>=col){
                // R 연산 수행
                int newCol = 0;
                for(int i=1; i<=row;i++){
                    int[] sortedRow = sort(A[i]);
                    for(int j=1; j<=sortedRow.length; j++){
                        A[i][j] = sortedRow[j-1];
                    }
                    if(newCol<=sortedRow.length){
                        newCol = sortedRow.length;
                    }
                }
                calc(A, row, newCol);
            } else {
                int newRow = 0;
                // C 연산
                calc(A, newRow, col);
            }
            time++;
        }
    }
    static int[] sort(int[] rowValue){
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for(int v:rowValue){
            if(cnt.containsKey(v)){
                cnt.replace(v, cnt.get(v)+1);
            }
            else {
                cnt.put(v, 1);
            }
        }
        // value기준 오름차순 정렬, key 기준 내림차순 정렬

        // 리스트에 옮겨닮고 반환
        return sorted;
    }
}
