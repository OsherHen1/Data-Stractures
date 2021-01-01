public class treeNode {
    private treeNode left;
    private treeNode right;
    private treeNode parent;
    private int data;
    private int height;
    private int balanceFactor;

    public treeNode(){
        this.left = null;
        this.right = null;
        this.height = 0;
    }

    public treeNode (int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public treeNode getLeft() {
        return left;
    }

    public treeNode getRight() {
        return right;
    }

    public int getData() {
        return data;
    }
    public void setData(int data){
        this.data = data;
    }

    public void setLeft(treeNode left) {
        this.left = left;
    }

    public void setRight(treeNode right) {
        this.right = right;
    }

    public void setParent(treeNode parent) {
        this.parent = parent;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public treeNode getParent() {
        return parent;
    }

    public int getHeight() {
        return height;
    }

    public int getBalanceFactor() {
        return balanceFactor;
    }

    public void setBalanceFactor(int balanceFactor) {
        this.balanceFactor = balanceFactor;
    }

    public void updateBalanceFactor(){
        if (getRight()==null && getLeft()==null)
            balanceFactor =0;
        if (getRight()!=null && getLeft()!=null)
            balanceFactor = Math.abs(getRight().height - getLeft().height);

        if (getRight()==null && getLeft()!=null)
            balanceFactor = Math.abs(getLeft().height)+1;

        if (getRight()!=null && getLeft() ==null)
            balanceFactor = Math.abs(getRight().height)+1;
    }

    public treeNode getChildWithBiggerHeight(){
        if (getRight()!=null && getLeft()!=null){
            if (getRight().getHeight()> getLeft().getHeight())
                return getRight();
            else
                return getLeft();
        }
        if (getRight()==null && getLeft()!=null)
            return getLeft();

        if (getRight()!=null && getLeft() ==null)
            return getRight();

        return null;
    }

    public treeNode getsuccessor(){
        if (this.getRight()==null)
            return this;
        else{
            return getsuccessorRec(this.getRight());
        }
    }

    public treeNode getPredecessor(){
        if (this.getLeft()==null)
            return this;
        else{
            return getPredecessorRec(this.getLeft());
        }
    }

    public treeNode getsuccessorRec(treeNode pointer) {
        if (pointer.left!=null)
            return getsuccessorRec(pointer.left);
        else{
            return pointer;
        }
    }

    public treeNode getPredecessorRec(treeNode pointer) {
        if (pointer.right!=null)
            return getPredecessorRec(pointer.right);
        else{
            return pointer;
        }
    }

    public boolean isLeaf(){
        return (this.getRight()==null && this.getRight()==null);
    }

    public boolean hasOneChild(){
        return ((this.getRight()!=null && this.getRight() ==null)||
                (this.getRight()==null && this.getRight() !=null));
    }

    public boolean hasTwoChildren(){
        return (this.getRight()!=null && this.getRight()!=null);
    }
}
