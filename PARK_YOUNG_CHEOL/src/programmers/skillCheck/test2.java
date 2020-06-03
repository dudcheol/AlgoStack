package programmers.skillCheck;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * n개의 섬 사이에 다리를 건설하는 비용(costs)이 주어질 때,
 * 최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용을 return 하도록 solution을 완성하세요.
 * 다리를 여러 번 건너더라도, 도달할 수만 있으면 통행 가능하다고 봅니다.
 * 예를 들어 A 섬과 B 섬 사이에 다리가 있고, B 섬과 C 섬 사이에 다리가 있으면 A 섬과 C 섬은 서로 통행 가능합니다.
 */
public class test2 {
    static int N;
    static int answer = Integer.MAX_VALUE;

    static class Node {
        int name;
        HashMap<Node, Integer> bridge;

        Node(int name) {
            this.name = name;
            this.bridge = new HashMap<>();
        }

        void connectTo(Node node, int cost) {
            bridge.put(node, cost);
        }
    }

    static int solution(int n, int[][] costs) {
        N = n;

        // 노드생성
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        // 노드연결
        for (int i = 0; i < costs.length; i++) {
            int[] target = costs[i];
            nodes[target[0]].connectTo(nodes[target[1]], target[2]);
            nodes[target[1]].connectTo(nodes[target[0]], target[2]);
        }

        sol(0, nodes[0], 0, null);

        return answer;
    }

    static void sol(int k, Node curNode, int cost, Node preNode) {
        if (k == N) { // 전부 방문했으면
            answer = Math.min(answer, cost);
            return;
        }

//        // 현재 노드에서 갈 수 있는 노드 중 가장 짧은 것 찾기
//        ArrayList<Node> nextNode = new ArrayList<>();
//        int minCost = Integer.MAX_VALUE;
//        for (Node node : curNode.bridge.keySet()) {
//            if (nextNode.size() == 0) {
//                nextNode.add(node);
//                minCost = node.bridge.get(curNode);
//                continue;
//            }
//            if (node.bridge.get(curNode) < minCost) {
//                nextNode.clear();
//                nextNode.add(node);
//            } else if (node.bridge.get(curNode) == minCost) {
//                nextNode.add(node);
//            }
//        }

        for (Node next : curNode.bridge.keySet()) {
            if (preNode != null && next == preNode) continue;
            sol(k + 1, next, cost + next.bridge.get(curNode), curNode);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};

        System.out.println(solution(n, costs)); // 답 : 4
    }
}
