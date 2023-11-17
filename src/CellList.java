public class CellList {
    // INNER CLASS
    class CellNode implements Cloneable {
    // ATTRIBUTES
        CellPhone phone; // What the node contains.
        CellNode node; // Pointer to the next node.

    // CONSTRUCTORS
        public CellNode () {
            phone = null;
            node = null;
        }

        public CellNode (CellPhone phone, CellNode node){
            this.phone = phone;
            this.node = node;
        }

        public CellNode (CellNode another){
            this.phone = another.phone;
            this.node = another.node;
        }
    // CUSTOM
        public CellNode clone() throws CloneNotSupportedException {
            return (CellNode)super.clone();
        }
    // SET & GET
        public CellPhone getPhone() {
            return phone;
        }
        public CellNode getNode() {
            return node;
        }
        public void setPhone(CellPhone phone) {
            this.phone = phone;
        }
        public void setNode(CellNode node) {
            this.node = node;
        }
    }

    // ATTRIBUTES
    private CellNode head;
    private int size;

    // CONSTRUCTORS
    public CellList(){
        this.head = null;
    }
    public CellList(CellList listToCopy){
        this.head = listToCopy.getHead();
        this.size = listToCopy.getSize();
    }

    // SET & GET
    public CellNode getHead() {
        return head;
    }
    public int getSize() {
        return size;
    }
    public void setHead(CellNode head) {
        this.head = head;
    }
    public void setSize(int size) {
        this.size = size;
    }

    // ACTION METHODS
    public void addToStart(CellPhone phone) {
        this.head = new CellNode(phone, this.head);
    }


}
