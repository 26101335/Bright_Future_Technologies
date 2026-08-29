/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package brightfutureapplication;

/**
 *
 * @author Chabalala Ngalava Naom 26101335
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Products {
      //making these static so i can use them in the static methods without creating an object inside of every method
    private static Scanner inputScanner = new Scanner(System.in);//used to get user input
    private static ArrayList<ReportData> products = new ArrayList<>();//stores our products
    
    
    public void startProgram() {
        System.out.println("BRIGHT FUTURE TECHNOLOGIES APPLICATION");
        System.out.println("***************************************");
        boolean run=true;
        while (run) {
            System.out.println("");
            System.out.print("Enter (1) to launch menu or any other key to exit ");
            String launchChoice = inputScanner.nextLine();
            System.out.println("");
            if ("1".equals(launchChoice.trim())) {
                int menuChoice=DisplayMenu();//dislay menu is called and whatever option user chooses is stored in our variable 
                switch (menuChoice) {
                    case 1:
                        CaptureProduct();
                        break;
                    case 2:
                        SearchProduct();
                        break;
                    case 3:
                        UpdateProduct();
                        break;
                    case 4:
                        DeleteProduct();
                        break;
                    case 5:
                        PrintProductReport();
                        break;
                    case 6:
                        run=ExitApplication();
                        break;
                }
            } else {
                run=ExitApplication();//false is assigned,terminating the loop
            }
        }
    }
   
    public static void CaptureProduct() {
        System.out.println("CAPTURE A NEW PRODUCT");
        System.out.println("**************************");

        String productCode;//declaring the variable outside of the loop so that it can be used later in the method and not be destroyed
        while (true) {
            System.out.print("Enter the product code: ");
            productCode = inputScanner.nextLine().trim();//allows the user to enter a value
            if (!productCode.isEmpty()) {//! negates the condition, so if the product code is not empty, it will break out of the loop
                break;//exits the loop if the product code is not empty
            }
            System.out.println("Product code cannot be empty");//if the product code is empty, this will be printed 
            //the loop we go back to the start and ask the user to enter a product code again
        }

        //this regular for loop checks if the product code already exists in the system, if it does, it will return and not allow the user to add a new product with the same code
        for (int i = 0; i < products.size(); i++) {
            ReportData product = products.get(i);
            if (product.getProductCode().equalsIgnoreCase(productCode)) {
                System.out.println("Product " + product.getProductName() + " is already in the system");
                return;//reason for this is simple,if the product code already exists, then most likely the thing they wanted to store is already stored
            }
        }

        String productName;
        while (true) {
            System.out.print("Enter the product name: ");
            productName = inputScanner.nextLine().trim();
            if (!productName.isEmpty()) {
                break;
            }
            System.out.println("Product name cannot be empty");
        }

        System.out.println("");
        System.out.println("Select the product category:");
        System.out.println("Desktop Computer - 1");
        System.out.println("Laptop - 2");
        System.out.println("Tablet - 3");
        System.out.println("Printer - 4");
        System.out.println("Gaming Console - 5");
        System.out.println("");
        int category;
        while (true) {
            System.out.print("Product category >> ");
            category = inputScanner.nextInt();
            inputScanner.nextLine();
            if (category>=1 && category<=5) {
                break;
            }
            System.out.println("Category has to be between 1 and 5");
        }

        System.out.println("");
        System.out.print("Indicate the product warranty. Enter (1) for 6 months or any other key for 2 years. ");
        String warrantyChoice = inputScanner.nextLine().trim();// need to use string coz we are trying to accomodate all other keys

        double warrantyInMonths;//opted to store warranty in months instead of years because it will be easier to calculate the average warranty in months, and then convert it to years when printing the report
        if("1".equals(warrantyChoice)){
            warrantyInMonths = 6.0;//6 months is 0.5 years
        }
        else{
            warrantyInMonths = 24.0;//24 months is 2 years
        }

        System.out.println("");
        double productPrice;
        while (true) {
            System.out.print("Enter the price for " + productName + " >> ");
            productPrice = inputScanner.nextDouble();
            inputScanner.nextLine();
            if (productPrice >= 0) {
                break;
            }
            System.out.println("Price cannot be NEGATIVE!!!!");
        }

        int stockLevel;
        while (true) {
            System.out.print("Enter Stock level for " + productName + " >> ");
            stockLevel = inputScanner.nextInt();
            inputScanner.nextLine();
            if (stockLevel >= 0) {
                break;
            }
            System.out.println("Stock cannot be negative");
        }

        String supplier;
        while (true) {
            System.out.print("Enter the supplier for " + productName + " >> ");
            supplier = inputScanner.nextLine().trim();
            if (!supplier.isEmpty()) {
                break;//exits the loop if the supplier is not empty
            }
            System.out.println("Supplier cannot be empty");//will be executed if supplier is empty
        }

        ReportData product = new ReportData(productCode, productName, warrantyInMonths, category, productPrice, stockLevel, supplier);
        SaveProduct(product);//saves the product to the products arraylist
        //since all statements have been executed, the method will end and the program will return to the prompt we got at the start
    }
    public static void SaveProduct(ReportData product) {
        products.add(product);//adds the product to the products arraylist
        System.out.println("Product details has been saved successfully!!!");
    }
     public static int DisplayMenu() {
        System.out.println("Please select one of the following menu items:");
        System.out.println("(1) Capture a new product.");
        System.out.println("(2) Search for a product.");
        System.out.println("(3) Update a product.");
        System.out.println("(4) Delete a product.");
        System.out.println("(5) Print report.");
        System.out.println("(6) Exit Application.");

        int menuChoice = 0;
        while (true) {
            System.out.print("choice >> ");
            menuChoice = inputScanner.nextInt();//when the user gives an input
            //the input is stored but there will be a left over nextLine character in the input buffer, 
            // which will cause the next nextLine() to be skipped, so we need to clear it
            inputScanner.nextLine();
            if (menuChoice >= 1 && menuChoice <= 6) {
                System.out.println("");
                return menuChoice;
            }
            System.out.print("Choice must be between 1 and 6");
        }
    }

    public static void SearchProduct() {
        String productCode;
        while(true){
            System.out.print("Please enter the product code to search: ");
            productCode = inputScanner.nextLine().trim();
            if (!productCode.isEmpty()) {
                break;
            }
            System.out.println("Product code cannot be empty");
        }

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductCode().equalsIgnoreCase(productCode)) {
                ReportData product = products.get(i);
                System.out.println("*************************************************");
                System.out.println("PRODUCT SEARCH RESULTS");
                System.out.println("*************************************************");
                System.out.println("PRODUCT CODE: " + product.getProductCode());
                System.out.println("PRODUCT NAME: " + product.getProductName());
                System.out.println("PRODUCT WARRANTY: " + (product.getWarranty() / 12) + " years");
                System.out.println("PRODUCT CATEGORY: " + FindCategoryValue(product.getCategory()));
                System.out.println("PRODUCT PRICE: " + product.getPrice());
                System.out.println("PRODUCT STOCK LEVELS: " + product.getStockLevels());
                System.out.println("PRODUCT SUPPLIER: " + product.getSupplier());
                return;//exits the method
            }
        }
        System.out.println("The product cannot be located. Invalid Product");//if product is not found, this will be show then the method will be exited
    }

    public static void DeleteProduct(){

        String productCode;
        while (true) {
            System.out.print("Please enter the product code to delete: ");
            productCode = inputScanner.nextLine().trim();
            if (!productCode.isEmpty()) {
                break;
            }
            System.out.println("Product code cannot be empty");
        }
        boolean found = false;//its for tracking if the product was found or not
        //if after the for loop it's still false then product was not found and we'll tell the

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductCode().equalsIgnoreCase(productCode)) {
                found = true;
                System.out.println("Are you sure that you want to delete? (y) for yes, any other key to cancel");
                String confirmation = inputScanner.nextLine();

                if("y".equalsIgnoreCase(confirmation.trim())){
                    products.remove(i);
                    System.out.println("Product deleted successfully");
                }
                else{
                    System.out.println("Cancellation successful");
                }

                break;
            }
        }

        if(!found){
            System.out.println("The product was not found in the system");
        }

    }
   
    public static void UpdateProduct() {
        String productCode;
        while (true) {
            System.out.print("Please enter the product code to update: ");
            productCode = inputScanner.nextLine().trim();
            if (!productCode.isEmpty()) {
                break;
            }
            System.out.println("Product code cannot be empty");
        }

        boolean found = false;

        for (int i = 0; i < products.size(); i++) {
            ReportData product = products.get(i);
            if (product.getProductCode().equalsIgnoreCase(productCode)) {
                found = true;

                String warrantyUpdateChoice;
                while (true) {
                    System.out.print("Update the warranty? (y) Yes, (n) No ");
                    warrantyUpdateChoice = inputScanner.nextLine().trim().toLowerCase();

                    if (warrantyUpdateChoice.isEmpty()) {
                        System.out.print("Please enter 'y' or 'n': ");
                        continue;
                    }

                    char warrantyUpdateAnswer = warrantyUpdateChoice.charAt(0);

                    if (warrantyUpdateAnswer == 'y') {
                        System.out.print("Indicate the new product warranty. Enter (1) for 6 months or any other key for 2 years. ");
                        String warrantyChoice = inputScanner.nextLine().trim();
                        if ("1".equals(warrantyChoice)) {
                            product.setWarranty(6.0);
                        } else {
                            product.setWarranty(24.0);
                        }
                        break;
                    } else if (warrantyUpdateAnswer == 'n') {
                        System.out.println("It won't be changed then");
                        break;
                    } else {
                        System.out.print("Invalid character entered! Try again (y/n): ");
                    }
                }

                String priceUpdateChoice;
                while (true) {
                    System.out.print("Update the price? (y) Yes, (n) No ");
                    priceUpdateChoice = inputScanner.nextLine().trim().toLowerCase();

                    if (priceUpdateChoice.isEmpty()) {
                        System.out.print("Please enter 'y' or 'n': ");
                        continue;
                    }

                    char priceUpdateAnswer = priceUpdateChoice.charAt(0);

                    if (priceUpdateAnswer == 'y') {
                        while (true) {
                            System.out.print("Enter the new price for " + product.getProductName() + " >> ");
                            double updatedPrice = inputScanner.nextDouble();
                            inputScanner.nextLine();
                            product.setPrice(updatedPrice);
                            break;
                        }
                        break;
                    } else if (priceUpdateAnswer == 'n') {
                        System.out.println("Okay, Moving on");
                        break;
                    } else {
                        System.out.print("Invalid character entered! Try again (y/n): ");
                    }
                }

                String stockUpdateChoice;
                while (true) {
                    System.out.print("Update the stock level? (y) Yes, (n) No ");
                    stockUpdateChoice = inputScanner.nextLine().trim().toLowerCase();

                    if (stockUpdateChoice.isEmpty()) {
                        System.out.print("Please enter 'y' or 'n': ");
                        continue;
                    }

                    char stockUpdateAnswer = stockUpdateChoice.charAt(0);

                    if (stockUpdateAnswer == 'y') {
                        while (true) {
                            System.out.print("Enter the new stock level for " + product.getProductName() + " >> ");
                            int updatedStockLevel = inputScanner.nextInt();
                            inputScanner.nextLine();

                            if (updatedStockLevel >= 0) {
                                product.setStockLevels(updatedStockLevel);
                                break;
                            }
                            System.out.println("Stock cannot be negative.");
                        }
                        break;
                    } else if (stockUpdateAnswer == 'n') {
                        System.out.println("Stock level was not changed.");
                        break;
                    } else {
                        System.out.print("Invalid character entered! Enter (y) for Yes or (n) for No: ");
                    }
                }

                System.out.println("Product details updated successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("Product not found");
        }
    }

    public static void PrintProductReport() {
        System.out.println("PRODUCT REPORT");
        System.out.println("=====================================================================================");

        if (products.isEmpty()) {// isEmpty is also an arraylist method used to check if its empty or not
            System.out.println("No products available.");
            System.out.println("");
            return;
        }

        double total = 0.0;
        for (int i = 0; i < products.size(); i++) {
            ReportData product = products.get(i);//reference to current object
            double productValue = product.getPrice() * product.getStockLevels();//total value of the product is calculated by multiplying the price by the stock levels
            total += productValue;//add the total value of the product to the total value of all products
            System.out.println("PRODUCT " + (i + 1));
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("PRODUCT CODE >> " + product.getProductCode());
            System.out.println("PRODUCT NAME >> " + product.getProductName());
            System.out.println("PRODUCT WARRANTY >> " + (product.getWarranty() / 12) + " years");
            System.out.println("PRODUCT CATEGORY >> " + FindCategoryValue(product.getCategory()));
            System.out.println("PRODUCT PRICE >> " + product.getPrice());
            System.out.println("PRODUCT STOCK LEVELS >> " + product.getStockLevels());
            System.out.println("PRODUCT SUPPLIER >> " + product.getSupplier());
            System.out.println("-------------------------------------------------------------------------------------");
        }

        
        System.out.println("==================================================================================");
        System.out.println("TOTAL PRODUCT COUNT: " + products.size());
        System.out.println("TOTAL PRODUCT VALUE: R " + total);
        System.out.println("AVERAGE PRODUCT VALUE: R " + (total / products.size()));
        System.out.println("==================================================================================");
    }

    public static boolean ExitApplication() {
        System.out.println("Goodbye!");
        return false;
    }
    public static String FindCategoryValue(int category){
        switch(category){
            case 1:
                return "Desktop Computer";
            case 2:
                return "Laptop";
            case 3:
                return "Tablet";
            case 4:
                return "Printer";
            case 5:
                return "Gaming Console";
            default:
                return "";
        }
    }
}

