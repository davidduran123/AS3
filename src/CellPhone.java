import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class CellPhone extends c implements Cloneable {

    // Attributes
    private static long nextSN = 0;
    private long serialNum;
    private String brand;
    private int year;
    private double price;

    // Constructors
    public CellPhone() {
        this.serialNum = CellPhone.nextSN++;
        this.brand = "N/A";
        this.year = 0;
        this.price = 0.00;
    }
    public CellPhone(long serialNum, String brand, int year, double price) {
        if(serialNum == nextSN)
            this.serialNum = serialNum;
        else
            this.serialNum = nextSN++;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }

    public CellPhone(CellPhone cellPhone, long serialNum){
        if(serialNum == nextSN)
            this.serialNum = serialNum;
        else
            this.serialNum = nextSN++;
        this.brand = cellPhone.getBrand();
        this.year = cellPhone.getYear();
        this.price = cellPhone.getPrice();
    }

    // Mutators
    public void setSerialNum(long serialNum) {
        this.serialNum = serialNum;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    // Accessors
    public long getSerialNum() {
        return serialNum;
    }
    public String getBrand() {
        return brand;
    }
    public int getYear() {
        return year;
    }
    public double getPrice() {
        return price;
    }

    // Utility Methods
    @Override
    public CellPhone clone() throws CloneNotSupportedException {
        boolean validSN = false;
        long newSN = -1;
        Scanner kbd = new Scanner(System.in);
        while(!validSN) { // Prompt the user until a valid SN is input.
            try {
                System.out.print("Please enter the next available serial-number (" + CellPhone.nextSN + ") to assign to the CellPhone copy: ");
                newSN = kbd.nextLong();
                if(newSN != CellPhone.nextSN)
                    throw new IncorrectSNException(CellPhone.nextSN);
                kbd.nextLine();
                validSN = true; // Valid SN has been input if we get to this point .:. break out of prompt loop.
            } catch(InputMismatchException e) {
                System.out.println("Invalid serial-number value! Please try again.");
                kbd.nextLine();
            } catch(IncorrectSNException e) {
                System.out.println(e.getMessage());
            }
        }
        CellPhone copy = (CellPhone)super.clone(); // Creating the copy w/bad serial-number.
        copy.setSerialNum(newSN);
        CellPhone.nextSN++;
        return copy;
    }
    @Override
    public String toString() {
        return "CellPhone{" +
                "serialNum=" + serialNum +
                ", brand='" + brand + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || o.getClass() != this.getClass())
            return false;
        CellPhone cell = (CellPhone) o;
        return this.getBrand().equals(cell.getBrand()) && this.getPrice() == cell.getPrice() && this.getYear() == cell.getYear();
    }
    public static void main(String[] args) {
     /*   // TESTING (It works)
        System.out.println("Next SN: " + CellPhone.nextSN);
        CellPhone iPhone = new CellPhone();
        System.out.println(iPhone);
        System.out.println("Next SN: " + CellPhone.nextSN);

        CellPhone Pixel = null;
        try{
            Pixel = iPhone.clone();
        }
        catch(CloneNotSupportedException e){
            System.out.println(e.getMessage());
        }

        System.out.println(Pixel);
        System.out.println("Next SN: " + CellPhone.nextSN);

        if(iPhone.equals(Pixel))
            System.out.println("Phones are the same!");
        else
            System.out.println("Phones are not the same");

        iPhone.setBrand("Apple");

        if(iPhone.equals(Pixel))
            System.out.println("Phones are the same!");
        else
            System.out.println("Phones are not the same!");

        // TESTING CellList Methods
        CellList CL1 = new CellList();

        // T1: Adding two phones at the start, back to back.
        CL1.addToStart(iPhone);
        System.out.println("This list has " + CL1.getSize() + " node(s).");
        CL1.addToStart(Pixel);
        System.out.println("This list has " + CL1.getSize() + " node(s)."); // WORKS

        CL1.insertAtIndex(new CellPhone(), 1);
        System.out.println("This list has " + CL1.getSize() + " node(s)."); // WORKS

        System.out.println(CellPhone.nextSN);

        // T2: Deleting the Pixel from the start index.
        CL1.deleteFromStart();
        System.out.println("This list has " + CL1.getSize() + " node(s)."); // WORKS

        //T3: Inserting at the first index.
        try{
            CL1.insertAtIndex(Pixel, 0);
        }
        catch(NoSuchElementException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        System.out.println("This list has " + CL1.getSize() + " node(s)."); // WORKS

        CL1.deleteFromStart();
        System.out.println("This list has " + CL1.getSize() + " node(s)."); // WORKS

        CL1.deleteFromStart();
        System.out.println("This list has " + CL1.getSize() + " node(s)."); // WORKS

        CL1.deleteFromStart();
        System.out.println("This list has " + CL1.getSize() + " node(s)."); // WORKS

        //T4: deleteFromIndex() tests

        // Empty List
        try {
            CL1.deleteFromIndex(0);
        }
        catch(NoSuchElementException e){
            System.out.println(e.getMessage());
        }

        for(int i = 0; i < 5; ++i){
            CL1.addToStart(new CellPhone());
        }
        System.out.println("This list has " + CL1.getSize() + " node(s)."); // WORKS
        try {
            CL1.deleteFromIndex(0);
        }
        catch(NoSuchElementException e){
            System.out.println(e.getMessage());
        }

        System.out.println("This list has " + CL1.getSize() + " node(s)."); // WORKS
      */ // TEST #1

        CellList L2 = new CellList();
        L2.setListName("Schindler");

        CellPhone iPhone = new CellPhone(69,"Apple",2001,69.99);
        CellPhone NothingPhone = new CellPhone(69,"Nothing",2022,105.99);
        CellPhone Moto = new CellPhone(69, "Motorola", 2016,120.99);

        CellPhone[] cellArr = {iPhone, NothingPhone, Moto};

        for(CellPhone phone : cellArr) {
            L2.addToStart(phone);
            System.out.println(phone);
        }

        System.out.println("\nCurrent list size: " + L2.findListSize());


        CellPhone Pixel = new CellPhone(59, "Google", 2015, 323.99);
        try {
            L2.replaceAtIndex(Pixel, 7);
        }
        catch(NoSuchElementException e){
            System.out.println(e.getMessage());
        }
        System.out.println("\nCurrent list size: " + L2.findListSize());

        System.out.println(Pixel.getSerialNum());
        L2.find(1);
        L2.find(2);

        L2.contains(0);
        L2.contains(69);

    }
}
