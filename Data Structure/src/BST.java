public class BST {

    protected treeNode root;
    protected int size;


    public BST (){
        this.root = new treeNode();
        this.size = 0;
    }
    /**
     * The function manges BST insert
     * @param number that will be added to the tree
     */
    public void insert(int number){
        treeNode t = new treeNode(number);
        if (isEmpty()) {
            this.root = t;
            t.setHeight(0);
            size++;
            updateHeight(t);
            return;
        } else {
            recursiveInsert(root, t);
            updateHeight(t);
            size++;
        }
    }

    /**
     * The function manges BST deletion
     * @param value is the value we would like to delete from the tree
     * @return
     */
    public treeNode delete(int value) {
        treeNode pointer = search(value);
        if (pointer!=null) {
            if (pointer.getRight() == null && pointer.getLeft() == null)
                return deleteLeaf(pointer);
            else {
                if (pointer.hasOneChild())
                    return deleteOneChild(pointer);
                else
                    return deleteTwoChildren(pointer, pointer.getPredecessor());
            }
        }else{
            return null;
        }
    }

    /**
     * private function that manages the deletion of node with two children
     * @param pointer is the node we would like to delete
     * @param predecessor is the node that replace the node we would like to delete
     * @return
     */
    private treeNode deleteTwoChildren(treeNode pointer, treeNode predecessor) {
        delete(predecessor.getData());
        pointer.setData(predecessor.getData());
        size--;
        return pointer;
    }

    private treeNode deleteOneChild(treeNode pointer) {
        treeNode replacment = null;
        if (pointer.getParent()==null){
            if (pointer.getRight()!=null){
                pointer.getRight().setParent(null);
                root= pointer.getRight();
            }
            else{
                pointer.getLeft().setParent(null);
                root= pointer.getLeft();
            }
        } else {
            if (pointer.getParent().getLeft() != null && pointer.getParent().getLeft().getData() == pointer.getData()) {
                if (pointer.getLeft() != null) {
                    replacment = pointer.getLeft();
                    pointer.getParent().setLeft(replacment);
                    replacment.setParent(pointer.getParent());
                } else {
                    replacment = pointer.getRight();
                    pointer.getParent().setLeft(replacment);
                    replacment.setParent(pointer.getParent());
                }
            } else {
                if (pointer.getLeft() != null) {
                    replacment = pointer.getLeft();
                    pointer.getParent().setRight(replacment);
                    replacment.setParent(pointer.getParent());
                } else {
                    replacment = pointer.getRight();
                    pointer.getParent().setRight(replacment);
                    replacment.setParent(pointer.getParent());
                }
            }
            updateHeight(replacment);
            //updateBalanceFactor(replacment);
            // fleepIfNeeded(replacment);
        }
        size--;
        return replacment;
    }

    protected void updateHeight(treeNode t) { // update height after insertion recursively
        if (t.getRight() == null && t.getLeft() == null)
            t.setHeight(0);
        if (t.getRight() != null && t.getLeft() != null)
            t.setHeight(Math.max(t.getRight().getHeight(), t.getLeft().getHeight()) + 1);
        if (t.getRight() != null && t.getLeft() == null)
            t.setHeight(t.getRight().getHeight() + 1);
        if (t.getRight() == null && t.getLeft() != null)
            t.setHeight(t.getLeft().getHeight() + 1);
        if (t.getParent() != null) {
            updateHeight(t.getParent()); // recursively call
        }
    }

    private treeNode deleteLeaf(treeNode pointer) {
        if (pointer.getParent().getLeft() != null && pointer.getParent().getLeft().getData() == pointer.getData()) {
            pointer.getParent().setLeft(null);
        } else {
            pointer.getParent().setRight(null);
        }
        updateHeight(pointer.getParent());
      //  updateBalanceFactor(pointer.getParent());
      //  fleepIfNeeded(pointer.getParent());
        size--;
        return pointer.getParent();
    }

    public treeNode search(int data) {
        return recursiveSearch(data, root);
    }

    public treeNode recursiveSearch(int data, treeNode pointer) {
        if (data == pointer.getData())
            return pointer;
        if (pointer.getData() > data && pointer.getLeft() != null)
            return recursiveSearch(data, pointer.getLeft());
        if (pointer.getData() < data && pointer.getRight() != null)
            return recursiveSearch(data, pointer.getRight());
        return null;
    }

    public treeNode minumum(treeNode pointer){
        if (pointer.getLeft()!= null)
            return minumum(pointer.getLeft());
        return pointer;
    }

    public treeNode maximum(treeNode pointer){
        if (pointer.getRight()!= null)
            return minumum(pointer.getRight());
        return pointer;
    }

    public boolean isEmpty(){
        return (size==0);
    }

    public void recursiveInsert(treeNode pointer, treeNode forInsert) { // insert like BST recursively
        if (pointer.getData() > forInsert.getData() && pointer.getLeft() != null) {
            recursiveInsert(pointer.getLeft(), forInsert);
        }
        if (pointer.getData() < forInsert.getData() && pointer.getRight() != null) {
            recursiveInsert(pointer.getRight(), forInsert);
        }
        if (pointer.getData() > forInsert.getData() && pointer.getLeft() == null) {
            pointer.setLeft(forInsert);
            forInsert.setParent(pointer);
        }
        if (pointer.getData() < forInsert.getData() && pointer.getRight() == null) {
            pointer.setRight(forInsert);
            forInsert.setParent(pointer);
        }
    }


    public void printTreeInOrder() {
        printingInOrder(this.root);

    }

    public void printingInOrder(treeNode n) {
        if (n.getLeft() != null)
            printingInOrder(n.getLeft());
        System.out.println("data: " + n.getData() + " height: " + n.getHeight() + " balanceFactor: " + n.getBalanceFactor());
        if (n.getRight() != null)
            printingInOrder(n.getRight());
    }

    public void printingPreOrder(treeNode n) {
        System.out.println("data: " + n.getData() + " height: " + n.getHeight() + " balanceFactor: " + n.getBalanceFactor());
        if (n.getLeft() != null)
            printingInOrder(n.getLeft());
        if (n.getRight() != null)
            printingInOrder(n.getRight());
    }

    public void printingPostOrder(treeNode n) {
        if (n.getLeft() != null)
            printingInOrder(n.getLeft());
        if (n.getRight() != null)
            printingInOrder(n.getRight());
        System.out.println("data: " + n.getData() + " height: " + n.getHeight() + " balanceFactor: " + n.getBalanceFactor());
    }

    public treeNode getRoot() {
        return this.root;
    }
}
