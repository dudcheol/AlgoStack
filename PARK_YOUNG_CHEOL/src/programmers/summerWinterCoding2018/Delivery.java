package programmers.summerWinterCoding2018;

import java.util.HashMap;

/**
 * 1시간 10분
 * <p>
 * #1. Node 클래스에 함수를 추가해서 새로운 edge가 입력될 때 마다
 * 기존의 edge와 비교해서 전역변수에 저장하도록 했으면 더 깔끔했을 것 같다.
 * <p>
 * #2. 문제는 정확히 보고 주어진 값도 확실히 보고 넘어간다.
 * 이거 대충 보고 넘어가다가 N이 주어진지도 모르고 node만들때부터 복잡해서 오래걸릴뻔함
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

        /* #1 */
        void addEdge(Node node, int cost) {
            if (this.edge.containsKey(node)) {
                // 이미 있는 edge라면 cost가 더 작은 것으로 변경
                this.edge.put(node, this.edge.get(node) > cost ? cost : this.edge.get(node));
            } else {
                this.edge.put(node, cost);
            }
        }
    }

    static int solution(int N, int[][] road, int K) { /* #2 */
        // 0은 비어있음
        nodes = new Node[N + 1];
        visited = new boolean[N + 1];
        limit = K;

        for (int i = 0; i < N + 1; i++) {
            nodes[i] = new Node(i);
        }

        for (int[] r : road) {
            // r[0] -> r[1] 로 가는 비용 r[2] 를 가지는 노드 생성
            nodes[r[0]].addEdge(nodes[r[1]], r[2]);
            // r[1] -> r[0] 로 가는 비용 r[2] 를 가지는 노드 생성
            nodes[r[1]].addEdge(nodes[r[0]], r[2]);
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
