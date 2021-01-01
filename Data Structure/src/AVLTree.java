public class AVLTree {
    private treeNode root;
    private int size;


    public AVLTree() {
        this.root = new treeNode();
        size = 0;
    }

    /**
     * the method insert into AVLtree
     * @param number - the value we want to insert
     */
    public void insert(int number) {
        treeNode t = new treeNode(number);
        if (isEmpty()) {
            this.root = t;
            t.setHeight(0);
            size++;
            return;
        } else {
            recursiveInsert(root, t);
            updateHeight(t);
            updateBalanceFactor(t);
            fleepIfNeeded(t);
        }

    }

    /**
     * checks if any fleeps needed
     * @param t - the node where we start to check
     */
    private void fleepIfNeeded(treeNode t) {
        if (t.getBalanceFactor() > 1) {
            treeNode z = t;
            treeNode y = t.getChildWithBiggerHeight();
            treeNode x = y.getChildWithBiggerHeight();
            if (z.getLeft() != null && y.getLeft() != null && z.getLeft().getData() == y.getData() && y.getLeft().getData() == x.getData()) {
                fleepR(x, y, z);
            }

            if (z.getRight() != null && y.getRight() != null && z.getRight().getData() == y.getData() && y.getRight().getData() == x.getData()) {
                fleepL(x, y, z);
            }
//
            if (z.getLeft() != null && y.getRight() != null && z.getLeft().getData() == y.getData() && y.getRight().getData() == x.getData()) {
                fleepLR(x, y, z);
            }
//
            if (z.getRight() != null && z.getRight().getData() == y.getData() && y.getLeft().getData() == x.getData()) {
                fleepRL(x, y, z);
            }

        } else {
            if (t.getParent() != null)
                fleepIfNeeded(t.getParent());
        }
    }

    private void fleepRL(treeNode x, treeNode y, treeNode z) {
        z.setRight(x);
        x.setParent(z);
        y.setLeft(x.getRight());
        x.setRight(y);
        y.setParent(x);
        updateHeight(y);
        updateHeight(x);
        updateBalanceFactor(y);
        updateBalanceFactor(x);
        fleepIfNeeded(y);
    }

    private void fleepLR(treeNode x, treeNode y, treeNode z) {
        z.setLeft(x);
        x.setParent(z);
        y.setRight(x.getLeft());
        x.setLeft(y);
        y.setParent(x);
        updateHeight(y);
        updateHeight(x);
        updateBalanceFactor(y);
        updateBalanceFactor(x);
        fleepIfNeeded(y);
    }

    private void fleepR(treeNode x, treeNode y, treeNode z) {
        if (z.getParent() != null) {
            if (z.getParent().getRight() != null && z.getParent().getRight().getData() == z.getData()) {
                z.getParent().setRight(y);
            } else {
                z.getParent().setLeft(y);
            }
            y.setParent(z.getParent());
        } else {
            y.setParent(null);
            root = y;
        }

        z.setLeft(y.getRight());
        if (y.getRight() != null)
            y.getRight().setParent(z);
        y.setRight(z);
        z.setParent(y);
        updateHeight(z);
        updateHeight(x);
        updateBalanceFactor(z);
        updateBalanceFactor(x);
    }

    private void fleepL(treeNode x, treeNode y, treeNode z) {
        if (z.getParent() != null) {
            if (z.getParent().getLeft() != null && z.getParent().getLeft().getData() == z.getData()) {
                z.getParent().setLeft(y);
            } else {
                z.getParent().setRight(y);
            }
            y.setParent(z.getParent());
        } else {
            y.setParent(null);
            root = y;
        }
        z.setRight(y.getLeft());
        if (y.getLeft() != null)
            y.getLeft().setParent(z);
        y.setLeft(z);
        z.setParent(y);
        updateHeight(z);
        updateHeight(x);
        updateBalanceFactor(z);
        updateBalanceFactor(x);
    }

    private void updateBalanceFactor(treeNode t) {
        t.updateBalanceFactor();
        if (t.getParent() != null)
            updateBalanceFactor(t.getParent());
    }

    private void updateHeight(treeNode t) { // update height after insertion recursively
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

    public treeNode getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return (size == 0);
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

    public void delete(int value) {
        treeNode pointer = search(value);
        if (pointer.getRight() == null && pointer.getLeft() == null)
            deleteLeaf(pointer);
        else{
            if (pointer.hasOneChild())
                deleteOneChild(pointer);
            else
                deleteTwoChildren(pointer,pointer.getsuccessor());
        }
    }

    private void deleteTwoChildren(treeNode pointer, treeNode getsuccessor) {
        delete(getsuccessor.getData());
        pointer.setData(getsuccessor.getData());
    }

    private void deleteOneChild(treeNode pointer) {
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
            updateBalanceFactor(replacment);
            fleepIfNeeded(replacment);
        }
    }

    private void deleteLeaf(treeNode pointer) {
        if (pointer.getParent().getLeft() != null && pointer.getParent().getLeft().getData() == pointer.getData()) {
            pointer.getParent().setLeft(null);
        } else {
            pointer.getParent().setRight(null);
        }
        updateHeight(pointer.getParent());
        updateBalanceFactor(pointer.getParent());
        fleepIfNeeded(pointer.getParent());
    }
}
