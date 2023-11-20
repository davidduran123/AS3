import java.util.NoSuchElementException;

public class CellList {
    // INNER CLASS
    class CellNode implements Cloneable {
    // ATTRIBUTES
        private CellPhone phone; // What the node contains.
        private CellNode next; // Pointer to the next node.

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
        this.size = 0;
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
        this.setSize(this.findListSize()); // Updating the 'size' attributes of the linked list.
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

            this.setSize(this.findListSize()); // Updating the 'size' attributes of the linked list.
        }
    }

    /**
     * Method inserts a node at a specified index in the linked-list.
     * It handles three different linked-list structure scenarios (1) Empty list (2) index doesn't exist (3) Index exists
     * @param phone CellPhone object for the node to hold.
     * @param index Index where the method must insert the node in the list.
     */
    public void insertAtIndex(CellPhone phone, int index) throws NoSuchElementException {
        if(index < 0 || index > this.size - 1){ // Scenario #1: The list has nodes, but the index doesn't exist!
            throw new NoSuchElementException("ERROR: Index " + index + " does not exist in this list!");
        }
        else if(this.size == 0) { // Scenario #2: List is empty.
            addToStart(phone);
        }
        else { // Scenario #3: The index exists in the list!
            CellNode t = this.head;
            for(int i = 0; i < index - 1; ++i){
                t = t.next;
            }
             t.next = new CellNode(phone, t.next);
            t = null;

            this.setSize(this.findListSize()); // Updating the 'size' attributes of the linked list.
        }
    }

    /**
     * Method removes a node in a specified index of the list (if it exists) and adjusts size of the list accordingly.
     * @param index Index where the method must delete the node in the list.
     */
    public void deleteFromIndex(int index) {
        if(index < 0 || index > this.size - 1 || this.size == 0){ // Scenario #1: The index doesn't exist OR the linked list is empty.
            throw new NoSuchElementException("ERROR: Index " + index + " does not exist in this list!");
        }
        else if(this.size == 1) { // Scenario #2: List contains one node.
            this.head = null;
            this.setSize(this.findListSize());
        }
        else { // Scenario #3: List contains one node.
            CellNode t = this.head;
            for(int i = 0; i < index - 1; ++i){
                t = t.next;
            }
            t.next = t.next.next; // Removing the reference to the node object we want to remove.
            t = null;
            this.setSize(this.findListSize()); // Updating the 'size' attributes of the linked list
        }
    }

    public void replaceAtIndex(CellPhone phone, int index){
        if(index < 0 || index > this.size - 1 || this.size == 0){ // Scenario #1: The index doesn't exist OR the linked list is empty.
            throw new NoSuchElementException("ERROR: Index " + index + " does not exist in this list!");
        }
        else if(index == 0) { // Scenario #2: We want to replace the first node.
            this. head = new CellNode(phone,this.head.next);
        }
        else{

        }
    }

    /**
     * Method determines the size of the linked-list that called it.
     * @return The size of the linked-list that called the method.
     */
    public int findListSize(){
        if(this.head == null)
            return 0;

        CellNode t = this.head;
        int sizeCtr = 1;

        while(t.next != null){
            t = t.next;
            ++sizeCtr;
        }
        t = null;
        return sizeCtr;
    }






}
