import java.util.NoSuchElementException;

public class CellList extends c {
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
    private String listName;

// CONSTRUCTORS

    public CellList(){
        this.head = null;
        this.size = 0;
    }
    public CellList(CellList listToCopy){ // To be modified to what we saw in lecture!
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
    public String getListName() {
        return listName;
    }
    public void setHead(CellNode head) {
        this.head = head;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public void setListName(String listName) {
        this.listName = listName;
    }

// ACTION METHODS

    /**
     * Method adds a node to the first position of the linked list.
     * @param phone is the CellPhone object for the node to hold.
     */
    public void addToStart(CellPhone phone) {
        this.head = new CellNode(phone, this.head);
        this.setSize(this.findListSize()); // Updating the 'size' attributes of the linked list.
        System.out.println(c("p") + "Added node at the start of list " + c("rs") + this);
    } // TESTED : WORKS

    /**
     * Method deletes the first node in the linked list if it exits.
     * If the list is empty, then the method lets the user know.
     */
    public void deleteFromStart() throws NoSuchElementException{
        if(this.head == null) // Scenario #1 (List in empty)
            throw new NoSuchElementException(c("r") + "ERROR: Cannot delete node at start as it doesn't exist in list " + c("rs") + this);
        else { // Scenario #2 (List has at least one node)
            CellNode t = this.head;
            this.head = t.next;
            t.next = null;
            t = null;
            this.setSize(this.findListSize()); // Updating the 'size' attributes of the linked list.
        }
        System.out.println(c("c") + "Deleted node at the starting position in list " + c("rs") + this);
    } // TESTED : WORKS

    /**
     * Method inserts a node at a specified index in the linked-list.
     * It handles three different linked-list structure scenarios (1) Empty list (2) index doesn't exist (3) Index exists
     * @param phone CellPhone object for the node to hold.
     * @param index Index where the method must insert the node in the list.
     */
    public void insertAtIndex(CellPhone phone, int index) throws NoSuchElementException {
        if(index < 0 || index > this.size - 1){ // Scenario #1: The list has nodes, but the index doesn't exist!
            throw new NoSuchElementException(c("r") + "ERROR: Cannot insert node at index "+ c("rs") + index + c("r")+ " as it doesn't exist in list " + c("rs") + this);
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
        System.out.println(c("b") + "Inserted node at index " + c("rs") + index + c("b") + " in list " + c("rs") + this + ".");
    } // TESTED : WORKS

    /**
     * Method removes a node in a specified index of the list (if it exists) and adjusts size of the list accordingly.
     * @param index Index where the method must delete the node in the list.
     */
    public void deleteFromIndex(int index) {
        if(index < 0 || index > this.size - 1 || this.size == 0){ // Scenario #1: The index doesn't exist OR the linked list is empty.
            throw new NoSuchElementException(c("r") + "ERROR: Node at index "+ c("rs") + index + c("r")+ " can't be deleted, it doesn't exist in list " + c("rs") + this);
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
        System.out.println(c("y") + "Deleted node at index " + c("rs") + index + c("y") + " in list " + c("rs") + this + ".");
    } // TESTED : WORKS

    /**
     * Method that replaces a node at a certain index in the linked-list (if it exists).
     * @param phone CellPhone object to be held in the CellNode object.
     * @param index Index that the input node must replace.
     * @throws NoSuchElementException Thrown if input index doesn't exist.
     */
    public void replaceAtIndex(CellPhone phone, int index) throws NoSuchElementException {
        if(index < 0 || this.size == 0 || index > this.size - 1){ // Scenario #1: The index doesn't exist OR the linked list is empty.
            throw new NoSuchElementException(c("r") + "ERROR: Cannot replace node at index " + c("rs") + index + c("r") + " as it doesn't exist in list: " + c("rs") + this);
        }
        else if(index == 0) { // Scenario #2: We want to replace the first node.
            this.head = new CellNode(phone,this.head.next);
        }
        else { // Scenario #3: We want to replace a node in the middle or the final node.
            CellNode t = this.head;
            for(int i = 0; i < index - 1; ++i) { // Move our pointer "t" to the node BEFORE the target index.
                t = t.next;
            }
            t.next = new CellNode(phone,t.next.next);
            t = null; // Get rid of the backdoor to our linked list.
        }
        System.out.println(c("g") + "Replaced node at index " + c("rs") + index + c("g") + " in list " + c("rs") + this + ".");
    } // TESTED : WORKS

    /**
     * Method that scans through a linked-list with the goal of finding the CellPhone object containing the matching serial-number.
     * @param serialNumber Long value to search for at each node containing a CellPhone object.
     * @return The address of the node containing the CellPhone object with the matching serial number in memory.
     */
    public CellNode find(long serialNumber){
        int numIter = 0;
        CellNode t = this.head;
        while(t != null && t.phone.getSerialNum() != serialNumber){
           t  = t.next;
           ++numIter;
        }
        if(t == null)
            System.out.println(c("r") + "ERROR: No node containing a CellPhone object with serial-number " + c("rs") + serialNumber + c("r")+ " found in list " + c("rs") + this
                    + c("p") + "\t # of iterations: " + c("rs") + numIter);
        else if(t.phone.getSerialNum() == serialNumber)
            System.out.println(c("g") + "CellPhone object with serial-number " + c("rs") + serialNumber + c("g") + " found in list " + c("rs") + this
                    + c("p") + "\t # of iterations: " + c("rs") + numIter);
        return t; // Return the address of the node OR null.
    } // TESTED : WORKS

    public boolean contains(long serialNumber){
        CellNode t = this.head;
        while(t != null && t.phone.getSerialNum() != serialNumber){
            t = t.next;
        }
        if(t == null){
            System.out.println(c("r") + "ERROR: Serial-number " + c("rs") + serialNumber + c("r") + " was NOT found in list: " + c("rs") + this);
            return false;
        }
        else{
            System.out.println(c("g") + "Serial-number " + c("rs") + serialNumber + c("g") + " was found in list: " + c("rs") + this);
            return true;
        }
    }


// USER METHODS

    /**
     * Method determines the size of the linked-list that called it.
     * @return The size of the linked-list that called the method.
     */
    public int findListSize(){

        CellNode t = this.head;
        int sizeCtr = 0;

        while(t != null){
            t = t.next;
            ++sizeCtr;
        }
        t = null;
        return sizeCtr;
    }
    @Override
    public String toString(){
        return listName;
    }


// TO-DO
    // FIX COPY CONSTRUCTOR FOR CellList



}
