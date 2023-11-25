import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CellListUtilization extends colour {
    public static void main(String[] args) {

        CellList CL1 = new CellList();  // Creating two empty CellLists
        CL1.setListName("India");

        CellList CL2 = new CellList();
        CL2.setListName("Mexico");

        long SN;
        String brand;
        double price;
        int year;

        Scanner file = null;

        try {   // Opening the text file
            file = new Scanner(new FileInputStream("Cell_Info.txt"));
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        while(file.hasNextLine()){ // Read the whole text file until the end
            SN = file.nextLong();
            brand = file.next();
            price = file.nextDouble();
            year = file.nextInt();

            CL1.addToStart(new CellPhone(SN,brand,year,price)); // Create a new node
        }
        file.close();

        CL1.showContents();
        CL1.fixSNDuplicates();
        CL1.showContents();

        Scanner kbr = new Scanner(System.in);
        boolean exit = false;
        boolean valSN = false;
        long inSN = -2;

        while(!exit) {
            System.out.print("\nEnter a serial-number to look-up in list " + CL1 + " or enter '-1' to exit: ");
            valSN = false;
            while(!valSN) {
                try {
                    inSN = kbr.nextLong();
                    valSN = true;
                    kbr.nextLine();
                }
                catch(InputMismatchException e) {
                    kbr.nextLine();
                    System.out.print("\nInvalid input, please try again: ");
                }
            }
            if(inSN == -1)
                exit = true;
            else {
                CL1.find(inSN);
            }
        } // Prompting user to look up SN in our linked list.
        System.out.println(c("r") + "\nExiting User Prompt Madness!\n" + c("rs"));

        // METHOD TESTING

        CellPhone iPhoneX = new CellPhone(1203941,"Apple",2018,200);
        CellPhone Pixel10 = new CellPhone(1239243, "Google", 2019, 300);
        CellPhone GalaxyS14 = new CellPhone(1234543, "Samsung", 2020, 400);
        CellPhone NothingPhone = new CellPhone(1123099, "Nothing", 2023, 800);
        CellPhone iPhone12 = new CellPhone(12091293,"Apple",2020,700);
        CellPhone MotoX = new CellPhone(1203102132, "Motorola",2016,300);
        CellPhone TeslaPhone = new CellPhone(109283012, "Tesla", 2026, 1000);

// CellPhone Class
        // clone()
        System.out.println("Testing the clone() from the CellPhone class...");
        CellPhone iPhone13 = null;
        try{
            iPhone13 = iPhone12.clone();
            System.out.println("Cloning the iPhone 12.....tada! iPhone 13!");
        }
        catch(CloneNotSupportedException e){
            System.out.println(e.getMessage());
        }
        // equals()
        if(iPhone12.equals(iPhone13))
            System.out.println(iPhone12 + " and "+ iPhone13 + " are the same!");
        else System.out.println(iPhone12 + " and "+ iPhone13 + " are NOT the same!");

        if(Pixel10.equals(iPhone13))
            System.out.println(Pixel10 + " and "+ iPhone13 + " are the same!");
        else System.out.println(Pixel10 + " and "+ iPhone13 + " are NOT the same!");

// CellList Class

        // addToStart()
        CL2.addToStart(iPhoneX);
        CL2.showContents();

        // insertAtIndex()
        try {
            CL2.insertAtIndex(Pixel10, 0);
            CL2.insertAtIndex(Pixel10, 7000); // Special Case! (Index doesn't exist)
            CL2.insertAtIndex(Pixel10, -7000); // Special Case! (Index doesn't exist)
        }
        catch(NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        CL2.showContents();

        // deleteFromStart()
        try {
            CL2.deleteFromStart();
        }
        catch(NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        CL2.showContents();

        // deleteFromIndex() & deleteFromStart()
        try {
            CL2.deleteFromIndex(0);
            CL2.deleteFromIndex(8); // Special Case! (Index doesn't exist)
        }
        catch(NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        CL2.showContents();
        try {
            CL2.deleteFromStart(); // Special case! (Empty List)
        }
        catch(NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        CL2.showContents();

        // replaceAtIndex()
        CL2.addToStart(GalaxyS14);
        CL2.addToStart(iPhone13);
        CL2.addToStart(iPhone12);
        CL2.addToStart(TeslaPhone);

        CL2.showContents();

        try {
            CL2.replaceAtIndex(NothingPhone,3);
            CL2.replaceAtIndex(iPhoneX,7); // Special Case! (Index doesn't exist)
        }
        catch(NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        CL2.showContents();

        // find()
        CL2.find(4204200); // Special Case! (SN isn't in list)
        CL2.find(1123099);

        // contains()
        CL2.contains(4204200); // Special Case! (SN isn't in list)
        CL2.contains(1123099);

        // CellList Copy Constructor
        CellList CL2copy = new CellList(CL2);

        CL2.showContents();
        CL2copy.showContents();

        // equals()
        CL2.equals(CL2copy);
        CL2.equals(CL1);

        // Testing all methods on an empty list
        CellList EL = new CellList();
        try{
            EL.deleteFromStart();
        }
        catch(NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        try{
            EL.find(0);
        }
        catch(NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        try{
            EL.contains(0);
        }
        catch(NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        try{
            EL.replaceAtIndex(Pixel10,0);
        }
        catch(NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        try{
            EL.deleteFromIndex(0);
        }
        catch(NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        EL.equals(CL1);
        EL.showContents();
    }
}
