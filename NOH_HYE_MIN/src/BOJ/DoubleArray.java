package BOJ;

import java.lang.reflect.Array;
import java.util.*;

public class DoubleArray {
    static int time = 0;
    static int r, c, k;
    static int[][] A = new int[102][102];
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
        if(time>100 || A[r][c] == k){
            if(time>100){
                time = -1;
            }
            return;
        } else {
            int[][] tmp = new int[102][102];
            if(row>=col){
                // R 연산 수행
                int newCol = 0;
                for(int i=1; i<=row;i++){
                    int[] sortedRow = sort(A[i],col);
                    for(int j=1; j<=sortedRow.length; j++){
                        tmp[i][j] = sortedRow[j-1];
                    }
                    if(newCol<=sortedRow.length){
                        newCol = sortedRow.length;
                    }
                }
                if (newCol > 100){
                    newCol = 100;
                }
                calc(tmp, row, newCol);
            } else {
                int newRow = 0;
                // C 연산
                for(int i=1; i<=col; i++){
                    int[] colList = new int[row+1];
                    for(int j=1; j<=row; j++){
                        colList[j] = A[j][i];
                    }
                    int[] sortedCol = sort(colList,row);
                    for(int k=1; k<=sortedCol.length; k++){
                        tmp[k][i] = sortedCol[k-1];
                    }
                    if(newRow<=sortedCol.length){
                        newRow = sortedCol.length;
                    }
                }
                if (newRow > 100){
                    newRow = 100;
                }
                calc(tmp, newRow, col);
            }
            time++;
        }
    }
    static int[] sort(int[] rowValue, int len){
        int[] cnt = new int[101];
        for(int i=1; i<=len; i++){
            if(rowValue[i]!=0) {
                cnt[rowValue[i]]++;
            }
        }
        // value기준 오름차순 정렬, key 기준 내림차순 정렬
        ArrayList<Counter> cntList = new ArrayList<>();
        for(int i=0; i<cnt.length; i++){
            if(cnt[i]!=0){
                cntList.add(new Counter(i, cnt[i]));
            }
        }
        Collections.sort(cntList);
        int[] sorted = new int[cntList.size()*2];
        int j=0;
        for(int i=0; i<cntList.size(); i++){
            sorted[j] = cntList.get(i).k;
            sorted[j+1] = cntList.get(i).v;
            j+=2;
        }
        return sorted;
    }
}
class Counter implements Comparable<Counter>{
    int k;
    int v;

    public Counter(int k, int v){
        this.k = k;
        this.v = v;
    }

    @Override
    public int compareTo(Counter counter) {
        if(this.v == counter.v){
            return this.k >= counter.k ? 1 : -1;
        }
        return this.v >= counter.v ? 1 : -1;
    }
}