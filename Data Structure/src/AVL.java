public class AVL extends BST {

    public AVL (){
        super();
    }

    @Override
    public void insert(int number){
        super.insert(number);
        treeNode t = search(number);
        if (t!=null) {
            updateBalanceFactorAllTheWayUp(t);
            fleepIfNeeded(t);
        }
    }

    @Override
    public treeNode delete(int value) {
        treeNode replacment = super.delete(value);
        if (replacment!=null) {
            updateBalanceFactorAllTheWayUp(replacment);
            fleepIfNeeded(replacment);
        }
        return replacment;
    }

    private void updateBalanceFactorAllTheWayUp(treeNode t) {
        t.updateBalanceFactor();
        if (t.getParent() != null)
            updateBalanceFactorAllTheWayUp(t.getParent());
    }

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
        updateBalanceFactorAllTheWayUp(y);
        updateBalanceFactorAllTheWayUp(x);
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
        updateBalanceFactorAllTheWayUp(y);
        updateBalanceFactorAllTheWayUp(x);
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
        updateBalanceFactorAllTheWayUp(z);
        updateBalanceFactorAllTheWayUp(x);
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
        updateBalanceFactorAllTheWayUp(z);
        updateBalanceFactorAllTheWayUp(x);
    }


}
