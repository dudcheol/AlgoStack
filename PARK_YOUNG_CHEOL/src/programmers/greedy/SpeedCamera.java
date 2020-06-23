package programmers.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class SpeedCamera {

    static int solution(int[][] routes) {

        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int diff1 = o1[1] - o1[0];
                int diff2 = o2[1] - o2[0];
                if (diff1 >= diff2) {
                    return 1;
                } else return -1;
            }
        });

        ArrayList<int[]> target = new ArrayList<>();
        target.add(routes[0]);

        for (int i = 1; i < routes.length; i++) {
            int[] range = routes[i];
            int j;
            for (j = 0; j < target.size(); j++) {
                int[] t = target.get(j);
                if ((t[0] >= range[0] && t[0] <= range[1]) || (t[1] >= range[0] && t[1] <= range[1])) {
                    // 범위 안에 하나라도 들어가면 카메라 필요 없음
                    break;
                }
            }
            if (j == target.size())
                // target을 전부 순회했는데 범위안에 든 적이 없다면 카메라 추가
                target.add(range);
        }

        return target.size();
    }

    public static void main(String[] args) {
//        int[][] routes = {{-20, 15}, {-14, -5}, {-18, -13}, {-5, -3}};
        int[][] routes = {{0,0},{0,0},{2,2}};
        System.out.println(solution(routes));
    }
}
