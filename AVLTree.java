class Node {
    int key, height;
    Node left, right;

    Node(int key) {
        this.key = key;
        height = 1;
    }
}

public class AVLTree {

    Node root;

    int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    int getBalance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    Node insert(Node node, int key) {

        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL Rotation
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // RR Rotation
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // LR Rotation
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL Rotation
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        AVLTree tree = new AVLTree();

        int[] timestamps = {
            1000, 2000, 3000, 4000, 5000,
            6000, 7000, 8000, 9000, 10000
        };

        for (int ts : timestamps) {
            tree.root = tree.insert(tree.root, ts);
        }

        System.out.println("Inorder Traversal:");
        tree.inorder(tree.root);

        System.out.println("\n\nAVL Height = " + tree.height(tree.root));
    }
}
