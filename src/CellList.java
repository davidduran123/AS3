import java.util.NoSuchElementException;

public class CellList extends colour {

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

        public CellNode (CellPhone phone, CellNode node){ // Create a custom node.
            this.phone = phone;
            this.next = node;
        }

        public CellNode (CellNode another){ // Create HARD COPY of a node.
            try{
                this.phone = another.phone.clone(); // This creates a HARD COPY of a CellPhone object
            }
            catch(CloneNotSupportedException e) {
                System.out.println(e.getMessage());
            }
            this.next = another.next;
        }
    // CUSTOM
        @Override
        public CellNode clone() throws CloneNotSupportedException { // Create a new CellNode object & copies all attributes
            CellNode cloneOfNode = (CellNode)super.clone(); // HARD COPY of node made but SHALLOW COPY made of CellPhone object inside.
            CellPhone hrdCpyOfPhone = this.phone.clone(); // Create a HARD COPY of the CellPhone object in the OG node
            cloneOfNode.setPhone(hrdCpyOfPhone); // Make the NEW node point to the NEW CellPhone object.
            return cloneOfNode;
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

    public CellList() {
        this.head = null;
        this.size = 0;
        this.listName = "Empty List";
    }

    public CellList(CellList list){ // Fix to DEEP copy!
        this.setListName(list.getListName() + " - copy");
        this.setSize(list.getSize());

        CellNode t1, t2, t3;
        t1 = list.head; // Scans through the OG linked list
        t2 = null; // Keeps track of our cloned nodes
        t3 = null; // Keeps track of the node created on the previous iteration
        while(t1 != null) {
            try {
                t2 = t1.clone();
//                System.out.println("Pointer 't2' is at new node in NEW list.");
//                System.out.println("Created a node clone!");
            }
            catch(CloneNotSupportedException e) {
                System.out.println(e.getMessage());
            }
            if(t1 == list.head){ // If "t1" is at head-node (this will only happen once)
//                System.out.println("Pointer 't1' is at first node in OG list.");
                this.head = t2;
//                System.out.println("Pointer 't2' is at first node in NEW list.");
                t2.next = null; // Make the cloned node point to null
                t3 = this.head;
//                System.out.println("Pointer 't3' is at first node in NEW list.");
            }
            else {
                t2.next = null; // Make the new node point to null.
                t3.next = t2; // Make the previous created node point to the new node.
                t3 = t3.next; // Move t3 to the new node.
//                System.out.println("Pointer 't3' has moved up a node in NEW list.");
            }
            t1 = t1.next;
//            System.out.println("Pointer 't1' has moved up a node in OG list.");
        }
        System.out.println("DEEP COPY of '" + list + "' has been completed.");
        t1 = null;
        t2 = null;
        t3 = null;
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
        System.out.println(c("p") + "\nAdded node at the start of list " + c("rs") + this);
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
        System.out.println(c("c") + "\nDeleted node at the starting position in list " + c("rs") + this);
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
        System.out.println(c("b") + "\nInserted node at index " + c("rs") + index + c("b") + " in list " + c("rs") + this + ".");
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
        System.out.println("\n" + c("y") + "\nDeleted node at index " + c("rs") + index + c("y") + " in list " + c("rs") + this + ".");
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
        System.out.println(c("g") + "\nReplaced node at index " + c("rs") + index + c("g") + " in list " + c("rs") + this + ".");
    } // TESTED : WORKS

    /**
     * Method that scans through a linked-list with the goal of finding the CellPhone object containing the matching serial-number.
     * @param serialNumber Long value to search for at each node containing a CellPhone object.
     * @return The address of the node containing the CellPhone object with the matching serial number in memory.
     */

    /*
    !!!! POTENTIAL PRIVACY LEAK !!!!
    Returning an address to an object in memory is rarely a good idea as anyone who gets a hold on it can compromise it.
    A safer alternative would be to return the index of the node holding the CellPhone object with the target serial-number.
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

    /**
     * Method scans the linked-list to see if a target serial-number exists or not.
     * @param serialNumber The target serial-number our method aims to find in our linked list.
     * @return True if the SN is within our linked-list and False if not.
     */
    public boolean contains(long serialNumber){
        CellNode t = this.head;
        while(t != null && t.phone.getSerialNum() != serialNumber){
            t = t.next;
        }
        if(t == null){
            System.out.println(c("r") + "ERROR: CellPhone object with serial-number " + c("rs") + serialNumber + c("r") + " was NOT found in list: " + c("rs") + this);
            return false;
        }
        else{
            System.out.println(c("g") + "CellPhone object with serial-number " + c("rs") + serialNumber + c("g") + " was found in list: " + c("rs") + this);
            return true;
        }
    }

    /**
     * Method prints out the sequence and contents of each node in a linked-list in a clean format.
     */
    public void showContents(){
        String header = c("it") + "\n\nThe current size of the list '" + c("BO") + c("g") + this + c("rs") + "' is " + c("it") + c("g") + this.size + c("rs") + c("it")
                + ". Here are the contents of the list" + c("rs");
        System.out.println(header);
        for(int i = 0; i < header.length() - 30; ++i) { System.out.print("="); }
        System.out.println();

        if(this.head == null)
            System.out.println(c("r") + "head/ ---> X" + c("rs"));
        else{
            int printCntr = 0;
            CellNode t = head;
            while(t!= null){
                System.out.print(t.phone + " ---> ");
                t = t.next;
                ++printCntr;
                if(printCntr % 3 == 0 && t != null)
                    System.out.println();
            }
            System.out.println(c("r") + "X" + c("rs") + "\n");
        }
    }

    /**
     * Method that compares the contents of one linked list to another.
     * @param list Linked-list that we'll compare the one that called the method to.
     * @return True if both linked lists contain the same CellPhone objects in their nodes (regardless of the order).
     *         False otherwise.
     */
    public boolean equals(CellList list) {
        // Creating travel-pointers
        CellNode t1, t2;
        t1 = this.head; // Pointer to travel across the linked-list calling the method.

        if(t1 == null) {  // If the list calling the method is empty
            System.out.println(c("r") + "Comparison list '" + c("rs") + this + c("r") + "' is empty!" + c("rs"));
            return false;
        }

        int indexCtr1 = 0, indexCtr2; // To keep track at what index our pointers are at.
        while(t1 != null){
            t2 = list.head; // Pointer to travel across the linked-list that is passed as a parameter.
            indexCtr2 = 0;
            while(t2 != null && !t1.phone.equals(t2.phone)) {
                t2 = t2.next;
                ++indexCtr2;
            }
            if(t2 == null){
                if(indexCtr2 == 0){
                    System.out.println(c("r") + "Comparison list '" + c("rs") + list + c("r") + "' is empty!" + c("rs"));
                }
                else {
                    System.out.println(c("r") + "Lists '" + c("rs") + this + c("r") + "' and '" + c("rs") + list + c("r") + "' do NOT hold similar objects!" + c("rs")
                            + "\nNo match found for node at index #" + indexCtr1 + " in list '" + this + "'");
                }
                return false;
            }
            else {
                if(t1.next == null){
                    System.out.println("Node at index #" + indexCtr1 + " in list '" + this + "' found a match in list '" + list + "'" +
                            "\nComparison process will terminate as final node in list '" + this + "' has been verified");
                    System.out.println(c("g") + "Lists '" + c("rs") + this + c("g") + "' and '" + c("rs") + list + c("g") + "' DO hold similar objects!" + c("rs"));
                }
                else{
                    System.out.println("Node at index #" + indexCtr1 + " in list '" + this + "' found a match in list '" + list + "'" +
                            "\nProceeding to node at index #" + (indexCtr1 + 1) + " in list '" + this + "' for comparison");
                    ++indexCtr1;
                }
                t1 = t1.next;
            }
        }
        return true;
    }

    /**
     * Method finds CellPhone objects with duplicate SNs in a linked-list, then removes all duplicates from it.
     */
    public void fixSNDuplicates(){ // W I P
        System.out.println(c("r") + c("it") + "\n*** GETTING RID OF DUPLICATE SERIAL NUMBER ENTRIES ***" + c("rs"));

        CellNode t1 = this.head, t2;
        for(int i = 0; t1 != null; ++i){ // Set "t1" at a node (starting from head)
            t2 = this.head;
            for(int j = 0; t2 != null; ++j){ // Scan the whole linked list (till null) for duplicates
                if(t2 == t1){ // Skip the index that "t1" is currently at
                    t2 = t2.next;
                    continue;
                }
                if(t1.phone.getSerialNum() == t2.phone.getSerialNum()){ // There's a duplicate .:. delete it
                    this.deleteFromIndex(j);
                    break;
                }
                t2 = t2.next;
            }
            t1 = t1.next;
        }
        System.out.println(c("r") + c("it") + "\n******************************************************" + c("rs"));
    }

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
}
