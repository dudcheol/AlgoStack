package programmers.greedy;

import java.util.Arrays;

public class SpeedCamera {

    static int solution(int[][] routes) {
        int answer = 0;

        Arrays.sort(routes, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        int camera = -30000;

        for (int i=0;i<routes.length;i++) {
            if (camera < routes[i][0]){
                camera = routes[i][1];
                answer += 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] routes = {{-20, 15}, {-14, -5}, {-18, -13}, {-5, -3}};
//        int[][] routes = {{0, 0}, {0, 0}, {2, 2}};
        System.out.println(solution(routes));
    }
}
