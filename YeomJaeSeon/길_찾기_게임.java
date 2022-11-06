package problems1;

import java.util.Arrays;
import java.util.Collections;

public class 길_찾기_게임 {
    static int idx;
    static int[][] answer;

    public static void main(String[] args) {
        길_찾기_게임 instance = new 길_찾기_게임();
        System.out.println(Arrays.deepToString(instance.solution(
                new int[][]{{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}}
        )));
    }
    public int[][] solution(int[][] nodeinfo) {
        Node[] nodes = new Node[nodeinfo.length];

        for(int i = 0; i < nodeinfo.length; i++){
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1, null, null);
        }

        // 정렬
        Arrays.sort(nodes, (o1, o2) -> {
            if(o1.y != o2.y){
                return o2.y - o1.y;
            }

            return o1.x - o2.x;
        });

        // make binary tree
        Node root = nodes[0];
        for(int i = 1; i < nodes.length; i++){
            insertNode(root, nodes[i]);
        }
        answer = new int[2][nodeinfo.length];

        // 전위 순회
        preOrder(root);

        idx = 0;
        // 후위 순회
        lastOrder(root);

        return answer;
    }
    private void insertNode(Node parent, Node child){
        // setting left node
        if(parent.x > child.x){
            if(parent.left == null) parent.left = child;
            else insertNode(parent.left, child);
        }
        // setting right node
        else{
            if(parent.right == null) parent.right = child;
            else insertNode(parent.right, child);
        }
    }

    private void preOrder(Node root){
        if(root != null){
            answer[0][idx++] = root.number;
            preOrder(root.left);
            preOrder(root.right);
        }
    }
    private void lastOrder(Node root){
        if(root != null){
            lastOrder(root.left);
            lastOrder(root.right);
            answer[1][idx++] = root.number;
        }
    }
}

class Node{
    int x;
    int y;
    int number;
    Node left;
    Node right;

    public Node(int x, int y,int value, Node left, Node right) {
        this.x = x;
        this.y = y;
        this.number = value;
        this.left = left;
        this.right = right;
    }
}

/**
 * 재귀함수를 이용한 이진트리 만들기
 * 재귀함수를 이용한 전위 순회, 후위 순회
 * - 전위 : 뿌리 -> 좌 -> 우
 * - 후위 : 좌 -> 우 -> 뿌리
 */