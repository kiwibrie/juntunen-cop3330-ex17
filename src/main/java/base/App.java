package base;

/*
 *  UCF COP3330 Summer 2021 Assignment 17 Solution
 *  Copyright 2021 Brianne Juntunen
 */
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        App myApp = new App();

        double genderRate = myApp.promptGender();
        double weight = myApp.promptDouble("your weight in pounds");
        double oz = myApp.promptDouble("the ounces of consumed alcohol");
        double hours = myApp.promptDouble("the number of hours since your last drink");

        double bac = myApp.calculateBAC(genderRate, weight, oz, hours);
        String msg = myApp.compareBAC(bac);

        myApp.printOutput(bac, msg);
    }

    public void printOutput(double bac, String msg){
        System.out.printf("Your BAC is %.2f.\n", bac);
        System.out.println(msg);
    }

    public double promptDouble(String prompt){
        System.out.printf("What is %s? ", prompt);
        while(!in.hasNextDouble()){
            System.out.print("Please enter a number. ");
            in.nextLine();
        }
        return Double.parseDouble(in.nextLine());
    }

    public double promptGender(){
        System.out.print("What is your gender? ");
        String input = in.nextLine();
        while(input != null){
            if(input.equalsIgnoreCase("m") ||
                    input.equalsIgnoreCase("male") ||
                    input.equalsIgnoreCase("man")){
                return 0.73;
            } else if (input.equalsIgnoreCase("f") ||
                    input.equalsIgnoreCase("female") ||
                    input.equalsIgnoreCase("woman")){
                return 0.66;
            } else {
                System.out.print("Please enter M or F. ");
                input = in.nextLine();
            }
        }
        return 999.999;
    }

    public double calculateBAC(double genderRate, double weight, double oz, double hours){
        //BAC = (A × 5.14 / W × r) − .015 × H
        return ((oz * 5.14) / (weight * genderRate)) - (0.015 * hours);
    }

    public String compareBAC(double bac){
        if(bac >= 0.08){
            return "It is not legal for you to drive.";
        }
        return "It is legal for you to drive.";
    }
}