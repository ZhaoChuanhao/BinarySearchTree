public class Main {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int i = 0; i < nums.length; i++){
            bst.add(nums[i]);
        }

        // 树的结构如下图所示
        //       5
        //     /  \
        //    3    6
        //  /  \    \
        // 2    4    8

        System.out.print("前序遍历递归：");
        bst.prePrint();
        System.out.println();
        System.out.print("前序遍历非递归：");
        bst.prePrintNR();
        System.out.println();
        System.out.print("层序遍历：");
        bst.levelPrint();
        System.out.println();

        /*bst.removeMin();
        bst.prePrint();
        System.out.println();

        bst.removeMin();
        bst.prePrint();
        System.out.println();

        bst.removeMin();
        bst.prePrint();
        System.out.println();

        bst.removeMin();
        bst.prePrint();
        System.out.println();*/

        bst.remove(3);
        bst.prePrint();
        System.out.println();

        bst.remove(5);
        bst.prePrint();
        System.out.println();

    }
}
