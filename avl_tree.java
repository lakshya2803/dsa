import java.util.*;
public class avl_tree {
    class Node {
        int data;
        Node left, right;
        int height;

        Node(int value) {
            data = value;
            height = 1;
        }
    }
    Node root;

    int height(Node x) {
        if (x == null) {
            return 0;
        }
        return x.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // function to calculate balance factor
    int getbalance(Node n) {
        if (n == null) {
            return 0;
        }
        return (height(n.left) - height(n.right));
    }

    Node insertion(Node temp1, int value) {
        if (temp1 == null) {
            return new Node(value);
            
        }

        if (temp1.data > value) {
            temp1.left = insertion(temp1.left, value);
        } else if (temp1.data < value) {
            temp1.right = insertion(temp1.right, value);
        } else { // duplicate keys not allowed
            return temp1;
        }

        temp1.height = 1 + max(height(temp1.left), height(temp1.right));
        int balancef = getbalance(temp1);
        /*
         * Now checking the balance factor of the nodes
         * and applying the rotations
         */
        // left left case
        if (balancef > 1 && value < temp1.left.data) {
            return rightrotation(temp1);
        }
        // right right case
        if (balancef < -1 && value > temp1.right.data) {
            return leftrotation(temp1);
        }
        // left right case
        if (balancef > 1 && value > temp1.left.data) {
            temp1.left = leftrotation(temp1.left);
            return rightrotation(temp1);
        }
        // right left case
        if (balancef < -1 && value < temp1.right.data) {
            temp1.right = rightrotation(temp1.right);
            return leftrotation(temp1);
        }

        return temp1;
    }

    Node leftrotation(Node x) {
        Node y = x.right;
        Node z = y.left;
        y.left = x;
        z = x.right;
        x.height = max(height(x.left), height(x.right));
        y.height = max(height(y.left), height(y.right));
        return y;
    }

    Node rightrotation(Node x) {
        Node y = x.left;
        Node z = y.right;
        y.right = x;
        z = x.left;

        x.height = max(height(x.left), height(x.right));
        y.height = max(height(y.left), height(y.right));
        return y;
    }

    // void levelorder(Node root) { // level order means print root and add left and
    //     if (root == null) {      //right in queue then pop() 
    //         return;
    //     }
    //     Queue<Node> q = new LinkedList<>();
    //     q.add(root);
    //     q.add(null);
    //     while (!q.isEmpty()) {
    //         Node currNode = q.remove();
    //         if (currNode == null) {
    //             System.out.println();
    //             if (q.isEmpty()) {
    //                 break;
    //             } else {
    //                 q.add(null);
    //             }
    //         } else {
    //             System.out.print(currNode.data + " ");
    //             if (currNode.left != null) {
    //                 q.add(currNode.left);
    //             }
    //             if (currNode.right != null) {
    //                 q.add(currNode.right);
    //             }
    //         }
    //     }
    // }

    void preorder(Node root){
        if(root != null){
            System.out.println(" "+root.data);
            preorder(root.left);
            preorder(root.right);
        }
    }

    public static void main(String[] args) {
        avl_tree avl = new avl_tree();
        avl.root = avl.insertion(avl.root, 5);
        avl.insertion(avl.root, 10);
        avl.insertion(avl.root, 15);
        avl.insertion(avl.root, 20);
        avl.insertion(avl.root, 25);
        avl.insertion(avl.root, 30);
        avl.preorder(avl.root);
        
    }
}
