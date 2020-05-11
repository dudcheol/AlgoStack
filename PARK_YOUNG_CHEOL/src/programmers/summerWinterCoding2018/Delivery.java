package programmers.summerWinterCoding2018;

import java.util.HashMap;

/**
 * 1시간 10분
 *
 * #1. Node 클래스에 함수를 추가해서 새로운 edge가 입력될 때 마다
 * 기존의 edge와 비교해서 전역변수에 저장하도록 했으면 더 깔끔했을 것 같다.
 */
public class Delivery {
    static int answer;
    static Node[] nodes;
    static boolean[] visited;
    static int limit;

    static class Node {
        int name;
        HashMap<Node, Integer> edge;
        boolean deliverable;

        Node(int name) {
            this.name = name;
            this.edge = new HashMap<>();
            this.deliverable = false;
        }

        /* #1
        * void addEdge(int name, int cost){
        *   // 입력된 edge가 기존의 edge에 존재하는지 확인! 만약에 있다면,
        *   // edge.get(name)과 새로입력된 cost를 비교해서 더 작은 값을 넣는다
        *   // 없다면 그냥 그대로 추가~ edge.put~
        * }
        * */
    }

    static int solution(int N, int[][] road, int K) {
        // 0은 비어있음
        nodes = new Node[N + 1];
        visited = new boolean[N + 1];
        limit = K;

        for (int i = 0; i < N + 1; i++) {
            nodes[i] = new Node(i);
        }

        for (int[] r : road) {
            // r[0] -> r[1] 로 가는 비용 r[2] 를 가지는 노드 생성
            if (nodes[r[0]].edge.containsKey(nodes[r[1]])) {
                // 이미 동일한 경로가 있다면 더 적은 비용을 저장
                /* #1로 했으면 덜 헷갈렸을 것이다
                * 아니면, 따로 전역변수를 두어서 덜 헷갈리게 하는게 나을듯 */
                nodes[r[0]].edge.put(nodes[r[1]], nodes[r[0]].edge.get(nodes[r[1]]) > r[2] ? r[2] : nodes[r[0]].edge.get(nodes[r[1]]));
            } else {
                nodes[r[0]].edge.put(nodes[r[1]], r[2]);
            }
            // r[1] -> r[0] 로 가는 비용 r[2] 를 가지는 노드 생성
            if (nodes[r[1]].edge.containsKey(nodes[r[0]])) {
                // 이미 동일한 경로가 있다면 더 적은 비용을 저장
                nodes[r[1]].edge.put(nodes[r[0]], nodes[r[1]].edge.get(nodes[r[0]]) > r[2] ? r[2] : nodes[r[1]].edge.get(nodes[r[0]]));
            } else {
                nodes[r[1]].edge.put(nodes[r[0]], r[2]);
            }
        }

        dfs(nodes[1], 0);

        // 노드 중 deliverable 이 true인 노드의 갯수가 정답
        for (Node node : nodes) {
            if (node.deliverable) answer++;
        }
        return answer;
    }

    static void dfs(Node node, int cost) {
        if (cost > limit) {
            return;
        }

        node.deliverable = true;
        for (Node edge : node.edge.keySet()) {
            if (visited[edge.name]) continue;
            visited[node.name] = true;
//            System.out.println("move node : " + node.name + " -> " + edge.name);
            dfs(edge, cost + node.edge.get(edge));
            visited[node.name] = false;
        }
    }

    public static void main(String[] args) {
        int N = 5;
        int[][] road = {{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}};
        int K = 3;

//        int N = 6;
//        int[][] road = {{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}};
//        int K = 4;

        System.out.println(solution(N, road, K));
    }
}
