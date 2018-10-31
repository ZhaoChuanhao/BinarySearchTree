import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author chuanhao.zhao@hand-china.com
 * @version 1.0
 * @name BST
 * @description 二分搜索树
 * @date 2018/10/23
 */
public class BST<T extends Comparable<T>> {

    private class Node{
        private T data;
        private Node left;
        private Node right;
        private Node(T data){
            this.data = data;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 向二分搜索树中添加元素
    public void add(T data){
        root = add(root, data);
    }

    // 向二分搜索树中添加元素，返回插入后的二分搜索树的根，递归算法
    private Node add(Node node, T data){
        if (node == null){
            return new Node(data);
        }

        if (data.compareTo(node.data) < 0){
            node.left = add(node.left, data);
        }else if (data.compareTo(node.data) > 0){
            node.right = add(node.right, data);
        }
        return node;
    }

    // 查找二分搜索树中是否包含该元素
    public boolean contains(T data){
        return contains(root, data);
    }

    // 查找以node为根的二分搜索树中是否包含该元素
    private boolean contains(Node node, T data){
        if (node == null || data == null){
            return false;
        }

        if (data.compareTo(node.data) == 0){
            return true;
        }else if (data.compareTo(node.data) < 0){
            return contains(node.left, data);
        }else {
            return contains(node.right, data);
        }
    }

    // 二分搜索树的前序遍历（前中后序均为深度优先遍历）
    public void prePrint(){
        prePrint(root);
    }

    // 二分搜索树的前序遍历，递归算法
    private void prePrint(Node node){
        if (node == null){
            return;
        }

        System.out.print(node.data + " ");
        prePrint(node.left);
        prePrint(node.right);
    }

    // 二分搜索树的前序遍历，非递归
    public void prePrintNR(){
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node node = stack.pop();
            System.out.print(node.data + " ");

            // 因为是前序遍历，所以先把右子树压栈
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }
    }

    // 二分搜索树的层序遍历（广度优先遍历）
    public void levelPrint(){
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node node = queue.remove();
            System.out.print(node.data + " ");

            // 因为是层序遍历，所以先把左子树入队
            if (node.left != null){
                queue.add(node.left);
            }
            if (node.right != null){
                queue.add(node.right);
            }
        }
    }

    // 查找二分搜索树的最小值
    public T min(){
        if (size == 0){
            throw new IllegalArgumentException("BinarySearchTree is empty!");
        }
        return min(root).data;
    }

    // 查找二分搜索树中最小的节点，递归算法
    private Node min(Node node){
        if (node.left == null){
            return node;
        }
        return min(node.left);
    }

    // 查找二分搜索树的最大值
    public T max(){
        if (size == 0){
            throw new IllegalArgumentException("BinarySearchTree is empty!");
        }
        return max(root).data;
    }

    // 查找二分搜索树中最大的节点，递归算法
    private Node max(Node node){
        if (node.right == null){
            return node;
        }
        return max(node.right);
    }

    // 查找二分搜索树中最小的值，非递归
    public T minNR(){
        if (size == 0){
            throw new IllegalArgumentException("BinarySearchTree is empty!");
        }
        Node node = root;
        while (node.left != null){
            node = node.left;
        }
        return node.data;
    }

    // 查找二分搜索树中最大的值，非递归
    public T maxNR(){
        if (size == 0){
            throw new IllegalArgumentException("BinarySearchTree is empty!");
        }
        Node node = root;
        while (node.right != null){
            node = node.right;
        }
        return node.data;
    }

    // 删除二分搜索树中的最小值的节点，返回最小值
    public T removeMin(){
        Node node = min(root);
        root = removeMin(root);
        return node.data;
    }

    // 删除二分搜索树中最小值的节点，返回删除后的二分搜索树的根，递归算法
    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    // 删除二分搜索树中的最大值的节点，返回最大值
    public T removeMax(){
        Node node = max(root);
        root = removeMax(root);
        return node.data;
    }

    // 删除二分搜索树中最大值的节点，返回删除后的二分搜索树的根，递归算法
    private Node removeMax(Node node){
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    // 删除二分搜索树中相应的节点
    public void remove(T data){
        root = remove(root, data);
    }

    // 删除二分搜索树中相应的节点，返回删除后二分搜索树的根，递归算法
    private Node remove(Node node, T data){
        if (node == null){
            return null;
        }

        if (data.compareTo(node.data) < 0){
            node.left = remove(node.left, data);
            return node;
        }else if (data.compareTo(node.data) > 0){
            node.right = remove(node.right, data);
            return node;
        }else {  // data.compareTo(node.data) == 0，证明该节点就是要删除的节点
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }else if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }else {  // 左右子树都不为空，采用 Hibbard Deletion 方法删除

                // Hibbard Deletion：即用右子树的最小值替换该节点，然后删除右子树的最小值
                Node successor = min(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;

                node.left = node.right = null;
                return successor;
            }
        }


    }
}
