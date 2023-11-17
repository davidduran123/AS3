import java.util.InputMismatchException;
import java.util.Scanner;
public class CellPhone implements Cloneable {

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
                System.out.print("Please enter the next available serial-number (#" + CellPhone.nextSN + ") to assign to the CellPhone copy: ");
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
        // TESTING (It works)
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

    }
}
