public class CellList {
    // INNER CLASS
    class CellNode implements Cloneable {
    // ATTRIBUTES
        CellPhone phone; // What the node contains.
        CellNode next; // Pointer to the next node.

    // CONSTRUCTORS
        public CellNode () { // Create an empty node.
            phone = null;
            next = null;
        }

        public CellNode (CellPhone phone, CellNode node){
            this.phone = phone;
            this.next = node;
        }

        public CellNode (CellNode another){ // Create a copy of a node.
            this.phone = another.phone;
            this.next = another.next;
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
            return next;
        }
        public void setPhone(CellPhone phone) {
            this.phone = phone;
        }
        public void setNode(CellNode node) {
            this.next = node;
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
    /**
     * Method adds a node to the first position of the linked list.
     * @param phone is the CellPhone object for the node to hold.
     */
    public void addToStart(CellPhone phone) {
        this.head = new CellNode(phone, this.head);
    }

    /**
     * Method deletes the first node in the linked list if it exits.
     * If the list is empty, then the method lets the user know.
     */
    public void deleteFromStart(){
        if(this.head == null) // Scenario #1 (List in empty)
            System.out.println("ERROR: No node to delete, list is empty!");
        else { // Scenario #2 (List has at least one node)
            CellNode t = this.head;
            this.head = t.next;
            t.next = null;
            t = null;
        }
    }
    public void insertAtIndex(CellPhone phone, int index){
        if(this.size == 0) // Scenario #1: List is empty.
            addToStart(phone);
        else if(index < 0 && index > this.size - 1){ // Scenario #2: The list has nodes, but the index doesn't exist!
            System.out.println("ERROR: Index " + index + " does not exist in this list!");
        }
        else { // Scenario #3: The index exists in the list!
            CellNode t = this.head;
            for(int i = 0; i < index - 1; ++i){
                t = t.next;
            }
             t.next = new CellNode(phone, t.next);
            t = null;
        }
    }






}
