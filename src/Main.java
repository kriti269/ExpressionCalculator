package src;


import src.exceptions.InvalidExpressionException;

public class Main {
    public static void main(String[] args) {
        try{
            System.out.println(new Calculator(args[0]).calculate());
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Input not provided!");
        } catch (InvalidExpressionException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception occurred while running the program!");
            ex.printStackTrace();
        }

    }
}
