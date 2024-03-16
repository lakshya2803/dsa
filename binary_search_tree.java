import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class binary_search_tree {
    class Node{
        int data;
        Node left,right;
        
        public Node(int key){
            data= key;
            left = null;
            right = null;
        }
    }
    Node root;
    Node temp;

    void preorder(Node root){
        if(root != null){
            System.out.println(" "+root.data);
            preorder(root.left);
            preorder(root.right);
        }
    }

    void inorder(Node root){
        if(root != null){
            inorder(root.left);
            System.out.println(" "+root.data);
            inorder(root.right);
        }
    }

    void levelorder(Node root){  /*level order means print root and add left and 
                                    right in queue then pop()*/
        if(root == null){
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()){
            Node currNode = q.remove();
            if(currNode == null){
                System.out.println();
                if(q.isEmpty()){
                    break;
                }
                else{
                    q.add(null);
                }
            }
            else{
                System.out.print(currNode.data + " ");
                if(currNode.left != null){
                    q.add(currNode.left);
                }
                if(currNode.right != null){
                    q.add(currNode.right);
                }
            }
        }
    }
    public Node createBinaryTree(Node temp1,int value){
        if(temp1 == null){
            Node newNode = new Node(value);
            temp1 = newNode;
            return temp1;
        }
        if(value>temp1.data){
            temp1.right = createBinaryTree(temp1.right,value);
        }
        else{
            temp1.left = createBinaryTree(temp1.left, value);
        }
        return temp1;
    }

    Node delete(Node root,int value){
        if(root.data > value){
            root.left = delete(root.left,value);
        }
        else if(root.data < value){
            root.right = delete(root.right,value);
        }
        else{
            //case 1 when leaf node is to be deleted
            if(root.left == null && root.right == null){
                return null;
            }
            //case 2 when one child node is to be deleted
            else if(root.left == null){
                return root.right;
            }
            else if(root.right == null){
                return root.left;
            }
            //case 3 when node is a two child node 
            Node is = inordersucc(root.right);
            root.data = is.data;
            root.right = delete(root.right, is.data);
        }
        return root;
    }
    public Node inordersucc(Node root){
        while(root.left != null){
            root = root.left;
        }
        return root;
    }

    public int height(Node root){
        if(root == null){
            return 0;
        }
        else{
            int lheight = height(root.left);
            int rheight = height(root.right);

            if(lheight>rheight){
                return lheight+1;
            }
            else{
                return rheight+1;
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        binary_search_tree bs = new binary_search_tree();
        Node root = null;
        int i=1;
        while(i==1){
            System.out.println("press\n 1.for creating binary tree\n 2.for traversal\n 3.deleting\n 4.Operations\n 5.Exit");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Enter the value to be inserted");
                    int value= sc.nextInt();
                    root = bs.createBinaryTree(root,value);
                    int j=1;
                    while(j==1){
                        System.out.println("press\n 1.To add more\n 2.Exit");
                        int ch1 = sc.nextInt();
                        switch (ch1) {
                            case 1:
                                System.out.println("Enter the data to be inserted:");
                                int d = sc.nextInt();
                                bs.createBinaryTree(root, d);
                                break;
                            case 2:
                                j=0;
                                break;
                            default:
                                System.out.println("Invalid input");
                                break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Press\n 1. inorder traversal\n 2. preorder\n 3. level order");
                    int ch2 = sc.nextInt();
                    switch (ch2) {
                        case 1:
                            bs.inorder(root);
                            break;
                        case 2:
                            bs.preorder(root);
                            break;
                        case 3:
                            bs.levelorder(root);
                            break;
                        default:
                            System.out.println("Invalid Input");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Enter the value for deleting: ");
                    int vald = sc.nextInt();
                    bs.delete(root, vald);
                    System.out.println("Deleted successfully");
                    break;
                case 4:
                    System.out.println("Press\n 1.height of bst");
                    ch2 = sc.nextInt();
                    switch (ch2) {
                        case 1:
                            bs.height(root);
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                    break;
                case 5:
                    i=0;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }

        }
        sc.close();

    }
}
