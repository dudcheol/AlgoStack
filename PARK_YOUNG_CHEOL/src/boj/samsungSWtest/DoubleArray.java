package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 정말..오래걸린문제!!!
 * 배열 범위를 다뤄야 하는 문제는 손으로 그려보던가 해서
 * 섬세하게 다루도록 한다
 * 어림잡아 break하거나 continue 해버리는데서 찾기힘든 실수가 발생할 수 있다!!!!
 *
 * 또, 배열 사이즈가 계속 달라지는 문제는
 * temp 배열을 만들어서 덮어씌우는 방법을 사용하도록 하자
 */
public class DoubleArray {
    static int r;
    static int c;
    static int k;
    static int[][] ary = new int[100][100];
    static int rMaxSize = 3;
    static int cMaxSize = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                ary[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while (true) {
            if (ary[r][c] == k) {
                break;
            }
            if (time > 100) {
                time = -1;
                break;
            }

            String flag = "";
            if (rMaxSize >= cMaxSize) { // R연산
                operateR();
                flag+="R";
            } else { //C연산
                operateC();
                flag+="C";
            }

//            System.out.println("-------------- " + flag +"연산 ---------");
//            for (int i=0;i<rMaxSize;i++){
//                for (int j=0;j<cMaxSize;j++){
//                    System.out.print(ary[i][j] + " ");
//                }
//                System.out.println();
//            }

            time++;
        }

        System.out.println(time);
    }

    static void operateR() {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<ArrayList<Map.Entry<Integer, Integer>>> sortedList = new ArrayList<>();
        int tempCMaxSize = 0;
        for (int i = 0; i < rMaxSize; i++) {
            map.clear();
            for (int j = 0; j < cMaxSize; j++) {
                if(ary[i][j] == 0) continue;
                int target = ary[i][j];
                if (map.containsKey(target)) {
                    map.put(target, map.get(target) + 1);
                } else {
                    map.put(target, 1);
                }
            }
            ArrayList<Map.Entry<Integer, Integer>> sorted = sort(map, i, 'r');
            tempCMaxSize = Math.max(tempCMaxSize, sorted.size() * 2);
            sortedList.add(sorted);
        }
        cMaxSize = tempCMaxSize;
        drawMap(sortedList, 0);
    }

    static void operateC() {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<ArrayList<Map.Entry<Integer, Integer>>> sortedList = new ArrayList<>();
        int tempRMaxSize = 0;
        for (int i = 0; i < cMaxSize; i++) {
            map.clear();
            for (int j = 0; j < rMaxSize; j++) {
                int target = ary[j][i];
                if (target == 0) continue;
                if (map.containsKey(target)) {
                    map.put(target, map.get(target) + 1);
                } else {
                    map.put(target, 1);
                }
            }
            ArrayList<Map.Entry<Integer, Integer>> sorted = sort(map, i, 'c');
            tempRMaxSize = Math.max(tempRMaxSize, sorted.size() * 2);
            sortedList.add(sorted);
        }
        rMaxSize = tempRMaxSize;
        drawMap(sortedList, 1);
    }

    static ArrayList<Map.Entry<Integer, Integer>> sort(HashMap<Integer, Integer> map, int i, char type) {
        ArrayList<Map.Entry<Integer, Integer>> listEnt = new ArrayList<>(map.entrySet());

        Collections.sort(listEnt, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                if (o1.getValue() == o2.getValue()) {
                    return o1.getKey().compareTo(o2.getKey());
                } else return o1.getValue().compareTo(o2.getValue());
            }
        });
        return listEnt;
    }

    static void drawMap(ArrayList<ArrayList<Map.Entry<Integer, Integer>>> sortedList, int type) {
        int[][] temp = new int[100][100];
        if (type == 0) { // R 연산
            for (int i = 0; i < sortedList.size(); i++) {
                ArrayList<Map.Entry<Integer, Integer>> sorted = sortedList.get(i);
                int mapEntryIdx = 0;
                for (int j = 0; j < sorted.size() * 2; j += 2) {
                    temp[i][j] = sorted.get(mapEntryIdx).getKey();
                    temp[i][j + 1] = sorted.get(mapEntryIdx).getValue();
                    mapEntryIdx++;
                }
            }
            ary = temp;
        } else { // C 연산
            for (int i = 0; i < sortedList.size(); i++) {
                ArrayList<Map.Entry<Integer, Integer>> sorted = sortedList.get(i);
                int mapEntryIdx = 0;
                for (int j = 0; j < sorted.size() * 2; j += 2) {
                    temp[j][i] = sorted.get(mapEntryIdx).getKey();
                    temp[j + 1][i] = sorted.get(mapEntryIdx).getValue();
                    mapEntryIdx++;
                }
            }
            ary = temp;
        }
    }
}
