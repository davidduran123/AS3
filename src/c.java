/**
 * Class that houses the c() that allows you to colour your text however you want.
 */
public class c {
    /**
     * Used to color a string in red, green, yellow, blue or purple.
     * (e.g.:) Driver1.colorMyText("GREEN") + "hello" + Driver1.colorMyText("RESET") + "world";
     * In the string above you're coloring the word "hello" in green and then resetting the color formatting to white for the word "world".
     * @param colorName A String that matches the color name you want your text to be in.
     * @return A String containing the color-code for the selected color.
     * */
    public static String c(String colorName) {
        String colorSelected;
        switch(colorName.toUpperCase()) {
            case "R" -> colorSelected = "\u001B[31m";     // Red text color
            case "G" ->  colorSelected = "\u001B[32m";  // Green text color
            case "Y" -> colorSelected = "\u001B[33m";  // Yellow text color
            case "B" -> colorSelected = "\u001B[34m";    // Blue text color
            case "P" -> colorSelected = "\u001B[35m";  // Purple text color
            case "C" -> colorSelected = "\u001B[36m"; // Cyan text color
            case "BO" -> colorSelected = "\u001B[1m"; // Bold text
            case "IT" -> colorSelected = "\u001B[3m"; // Italic text
            case "UN" -> colorSelected = "\u001B[4m"; // Underline text
            case "RS" -> colorSelected = "\u001B[0m";    // White text color
            default -> colorSelected = "\u001B[0m";
        }
        return colorSelected;
    }
}
