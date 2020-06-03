package programmers.skillCheck.try1;

import java.util.ArrayList;

/**
 * 프로그래머스 - 섬 연결하기
 *
 * n개의 섬 사이에 다리를 건설하는 비용(costs)이 주어질 때,
 * 최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용을 return 하도록 solution을 완성하세요.
 * 다리를 여러 번 건너더라도, 도달할 수만 있으면 통행 가능하다고 봅니다.
 * 예를 들어 A 섬과 B 섬 사이에 다리가 있고, B 섬과 C 섬 사이에 다리가 있으면 A 섬과 C 섬은 서로 통행 가능합니다.
 *
 * 그리디 문제
 *
 * 그래프를 그려가며 어렵게 풀 필요 없이
 * adj 행렬을 이용하여 연결됐을 때마다 방문여부를 확인하고 가장 최소의 비용으로 갈 수 있는 곳을 선택하면 된다
 */
public class test2 {

    static int solution(int n, int[][] costs) {
        int answer = 0;
        // 연결하기
        int[][] adj = new int[n][n];

        for (int i = 0; i < costs.length; i++) {
            int[] target = costs[i];
            adj[target[0]][target[1]] = adj[target[1]][target[0]] = target[2];
        }

        boolean[] visit = new boolean[n];
        visit[0] = true;
        ArrayList<Integer> connect = new ArrayList<>();
        connect.add(0);

        while (connect.size() < n) {
            int minIdx = -1;
            int min = Integer.MAX_VALUE;

            for (int i : connect) {
                for (int j = 0; j < n; j++) {
                    if (adj[i][j] <= 0) continue;
                    if (visit[j]) continue;
                    if (adj[i][j] < min) {
                        min = adj[i][j];
                        minIdx = j;
                    }
                }
            }
            connect.add(minIdx);
            visit[minIdx] = true;
            answer += min;
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};

        System.out.println(solution(n, costs)); // 답 : 4
    }
}
