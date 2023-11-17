import java.util.InputMismatchException;
import java.util.Scanner;
public class CellPhone implements Cloneable {

    // Attributes
    private static long serialNumCtr = 0;
    private long serialNum;
    private String brand;
    private int year;
    private double price;

    // Constructors
    public CellPhone() {
        this.serialNum = 0;
        this.brand = "N/A";
        this.year = 0;
        this.price = 0.00;
    }
    public CellPhone(long serialNum, String brand, int year, double price) {
        this.serialNum = serialNum;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }
    public CellPhone(CellPhone cellPhone, long uniqueSerialNum){
        this.serialNum = uniqueSerialNum;
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
    public CellPhone clone() throws CloneNotSupportedException {
        boolean validSN = false;
        long newSN = -1;
        long nextAvailSN = CellPhone.serialNumCtr + 1;
        Scanner kbd = new Scanner(System.in);
        while(!validSN) { // Prompt the user until a valid SN is input.
            try {
                System.out.println("Please enter the next available serial-number (#" + nextAvailSN + ") to assign to the CellPhone copy: ");
                newSN = kbd.nextLong();
                if(newSN != nextAvailSN)
                    throw new IncorrectSNException(nextAvailSN);
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
        return copy;
    }
    public static void main(String[] args) {
        System.out.println("he");
    }
}
