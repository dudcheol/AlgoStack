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

    static void printTree(int depth, Node curNode, int idx) {
        ArrayList<Node> childs = curNode.child;

        if (depth == 0)
            // 루트노드 출력
            System.out.print(String.format("[%03d]--", curNode.id));
        else {
            // 현재노드의 부모노드가 가진 자식노드들의 수에 따라 노드 앞에 출력될 문자(?--)가 달라진다.
            if (curNode.parent.child.size() > 1)
                System.out.print(curNode.parent.child.size() - 1 == idx ? "L--" : "+--");
            else
                System.out.print("---");

            // 현재노드에 자식이 있으면 노드 뒤에 --가 있어야 하고, 한 칸 띄워야함
            System.out.print(String.format("[%03d]", curNode.id));
            if (curNode.child.size() == 0)
                System.out.print("\n");
            else
                System.out.print("--");
        }

        // 현재노드의 자식노드를 방문
        for (int i = 0; i < childs.size(); i++) {
            // 루트노드를 출력하기 전에, 앞에 공백이 되는 부분을 먼저 출력
            if (depth == 0 && i != 0) System.out.print("       ");
            else if (i != 0) {
                // 첫번째 자식노드가 아니라면 앞에 공백이 필요함
                System.out.print("       ");
                // 현재노드가 부모노드의 마지막 자식노드가 아니고,
                // 현재노드의 첫번째 자식노드가 아닐 경우에 현재노드의 형제노드들을 연결해줄 필요가 있다.
                for (int j = 0; j < depth; j++)
//                    if (curNode.parent.child.size() - 1 != idx)
//                        System.out.print("|         ");
//                    else
                        System.out.print("          ");
            }

            Node child = childs.get(i);
            printTree(depth + 1, child, i);
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

        printTree(0, nodes[root], 0);
    }
}

/**
 * test case
 6
 5
 30
 30 54 1 2 45 123
 30 54
 30 2
 30 45
 54 1
 45 123

 13
 12
 1
 1 2 3 4 5 6 7 8 9 10 11 12 13
 1  2
 1 3
 1 4
 2 5
 2 6
 3 7
 7 8
 4 9
 9 10
 10 11
 10 12
 10 13

 10
 9
 1
 1 2 3 4 5 6 7 8 9 10
 1 2
 1 3
 1 4
 2 5
 5 6
 3  7
 4 8
 8 9
 9 10

 21
 20
 1
 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21
 1 2
 2 5
 5 11
 5 12
 2 6
 6 13
 6 14
 14 15
 1 3
 3 7
 3 8
 1 4
 4 9
 9 16
 16 19
 16 20
 20 21
 4 10
 10 17
 17  18
 */