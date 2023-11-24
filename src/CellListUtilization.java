import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class CellListUtilization extends colour {
    public static void main(String[] args) {

        CellList CL1 = new CellList();  // Creating two empty CellLists
        CL1.setListName("Peggy");

        CellList CL2 = new CellList();
        CL2.setListName("Hank");

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
        CL1.fixSNDplcts();
        CL1.showContents();

        Scanner kbr = new Scanner(System.in);
        boolean exit = false;
        boolean valSN = false;
        long inSN = -2;


        while(!exit) {
            System.out.print("\nEnter a serial-number to look-up or enter '-1' to exit: ");
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
        }
        System.out.println(c("r") + "\nExit!" + c("rs"));




    }
}
