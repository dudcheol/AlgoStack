package boj.samsungSWtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DoubleArray {
    static int r;
    static int c;
    static int k;
    static int[][] ary = new int[100][100];
    static boolean breakFlag = false;
    static int rMaxSize = Integer.MIN_VALUE;
    static int cMaxSize = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        for (int[] _ary : ary) {
            Arrays.fill(_ary, -1);
        }

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                ary[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while (true) {
            if (ary[r][c] == k || breakFlag) {
                break;
            }
            if (time > 100) {
                time = -1;
                break;
            }

            if (rMaxSize >= cMaxSize) { // R연산
                operateR();
            } else { //C연산
                operateC();
            }
            time++;
        }

        System.out.println(time);
    }

    static void operateR() {
        HashMap<Integer, Integer> map = new HashMap<>();
        boolean opBreakFlag = false;
        for (int i = 0; i < ary.length; i++) {
            map.clear();
            for (int j = 0; j < ary.length; j++) {
                if (ary[i][j] == -1)
                    break;

                int target = ary[i][j];
                if (map.containsKey(target)) {
                    map.put(target, map.get(target) + 1);
                } else {
                    map.put(target, 1);
                }
            }
            opBreakFlag = sort(map, i, 'r');
        }
        breakFlag = opBreakFlag;
    }

    static void operateC() {
        HashMap<Integer, Integer> map = new HashMap<>();
        boolean opBreakFlag = false;
        for (int i = 0; i < ary.length; i++) {
            map.clear();
            for (int j = 0; j < ary.length; j++) {
                if (ary[j][i] == -1)
                    break;

                int target = ary[j][i];
                if (map.containsKey(target)) {
                    map.put(target, map.get(target) + 1);
                } else {
                    map.put(target, 1);
                }
            }
            opBreakFlag = sort(map, i, 'c');
        }
        breakFlag = opBreakFlag;
    }

    static boolean sort(HashMap<Integer, Integer> map, int i, char type) {
        ArrayList<Map.Entry<Integer, Integer>> listEnt = new ArrayList<>(map.entrySet());
        boolean opBreakFlag = false;

        Collections.sort(listEnt, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                if (o1.getValue() == o2.getValue()) {
                    return o1.getKey().compareTo(o2.getKey());
                } else return o1.getValue().compareTo(o2.getValue());
            }
        });

        if (type == 'r') {
            int jIdx = 0;
            for (Map.Entry<Integer, Integer> s : listEnt) {
                if (jIdx == 100) {
                    opBreakFlag = true;
                    break;
                }
                ary[i][jIdx] = s.getKey();
                ary[i][jIdx + 1] = s.getValue();
                jIdx += 2;
            }
            for (int k = jIdx; k < ary.length; k++) {
                if (k < 0 || ary[i][k] == -1) break;
                ary[i][k] = -1;
            }
            cMaxSize = Math.max(cMaxSize, Math.max(jIdx - 2, 0));
        } else {
            int jIdx = 0;
            for (Map.Entry<Integer, Integer> s : listEnt) {
                if (jIdx == 100) {
                    opBreakFlag = true;
                    break;
                }
                ary[jIdx][i] = s.getKey();
                ary[jIdx + 1][i] = s.getValue();
                jIdx += 2;
            }
            for (int k = jIdx; k < ary.length; k++) {
                if (k < 0 || ary[k][i] == -1) break;
                ary[k][i] = -1;
            }
            rMaxSize = Math.max(rMaxSize, Math.max(jIdx - 2, 0));
        }

        return opBreakFlag;
    }
}
