package swea.ct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 루트있는 트리를 입력으로받아 아래와같이 출력하는 프로그램을 작성하라.
 * 트리의 각 노드에는 1,000 미만의 자연수가 저장되어있다.
 * 트리의 노드 연결관계는 다음과 같이 표현해야 한다.
 * 아래 출력에서 루트에는 자식이 3개있고 그 자식들 중 하나는 더이상 자식이 없는 것임을 알 수 있을것이다.
 * [030]--+--[054]-----[001]
 * 　　　　+--[002]
 * 　　　　L--[045]-----[123]
 */
public class _8_5 {
    static int n;
    static int e;
    static int root;
    static Node[] nodes = new Node[1000];

    static class Node {
        int id;
        Node parent;
        ArrayList<Node> child;

        Node(int id) {
            this.id = id;
            parent = null;
            child = new ArrayList<>();
        }
    }

    static void printTree(int depth, Node curNode, boolean isLast) {
        // 기저
        if (curNode.child.size() == 0) {
            if (curNode.parent.child.size() > 1) {
                System.out.print("+--");
            } else System.out.print("-----");
            System.out.print(String.format("[%03d]", curNode.id));
            System.out.print("\n\t   ");
            return;
        }

        Node parent = curNode.parent;
        ArrayList<Node> childs = curNode.child;

        if (depth == 0) System.out.print(String.format("[%03d]--", curNode.id));
        else {
            if (isLast) System.out.print("+--");
            else {
                if (curNode.parent.child.size() == 1) System.out.print("-----");
                else System.out.print("L--");
            }
            System.out.print(String.format("[%03d]", curNode.id));
        }

        for (int i = 0; i < childs.size(); i++) {
            Node child = childs.get(i);
            printTree(depth + 1, child, i != childs.size() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        // 노드 갯수, 간선 개수, 루트노드id, 노드알려줌, 연결된 노드
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        e = Integer.parseInt(br.readLine());
        root = Integer.parseInt(br.readLine());

        // 노드 생성
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int curNode = Integer.parseInt(st.nextToken());
            nodes[curNode] = new Node(curNode);
        }

        // 루트 노드는 부모 노드가 없다
        nodes[root].parent = null;

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            // 입력받은 것들로 노드 연결하기
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            nodes[parent].child.add(nodes[child]);
            nodes[child].parent = nodes[parent];
        }

        printTree(0, nodes[root], false);
    }
}
