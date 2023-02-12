package main;

import main.commands.Command;
import main.commands.SaveDataCommand;
import main.commands.coffee.AddCoffeeCommand;
import main.commands.coffee.ChangeCoffeeCommand;
import main.commands.coffee.DeleteCoffeeCommand;
import main.commands.coffee.PrintCoffeesCommand;
import main.commands.coffee.search.*;
import main.commands.sort.SortCoffeeByCorrelationCommand;
import main.commands.sort.SortCoffeeByQualityCommand;
import main.commands.van.*;
import main.helpers.AppLogger;

import java.util.Scanner;

public class Application {
    private final Manager manager;

    public Application() {
        new AppLogger();
        manager = new Manager();
    }

    public void initUI() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        System.out.println("Choose command, then print it number. Like this: '10'");
        printInstructions();
        while (flag) {
            System.out.print("Command: ");
            String inputCommand = scanner.next();
            // Save data
            if ("save".equals(inputCommand)) {
                executeCommand(new SaveDataCommand(manager));

                // Add van
            } else if ("1".equals(inputCommand)) {
                System.out.print("Enter van's capacity: ");
                String capacity = scanner.next();
                executeCommand(new AddVanCommand(capacity, manager));

                // Change van
            } else if ("2".equals(inputCommand)) {
                System.out.print("Enter van's id: ");
                String id = scanner.next();
                System.out.print("Enter van's new capacity: ");
                String capacity = scanner.next();
                executeCommand(new ChangeVanCommand(id, capacity, manager));

                // Delete van
            } else if ("3".equals(inputCommand)) {
                System.out.print("Enter van's id: ");
                String input = scanner.next();
                executeCommand(new DeleteVanCommand(input, manager));

                // Load van
            } else if ("4".equals(inputCommand)) {
                System.out.print("Enter van's id: ");
                String id = scanner.next();
                executeCommand(new LoadVanCommand(id, manager));

                // Print vans
            } else if ("5".equals(inputCommand)) {
                executeCommand(new PrintVansCommand(manager));

                // Add coffee
            } else if ("6".equals(inputCommand)) {
                System.out.print("Enter name: ");
                String name = scanner.next();

                System.out.println("Enter aggregate state: \n\tCan be: 'cereal', 'ground', 'solubleInCans', 'solubleInSachets';");
                String state = scanner.next();

                System.out.print("Enter volume (cm^3): ");
                String volume = scanner.next();

                System.out.print("Enter netto weight (g): ");
                String weight = scanner.next();

                System.out.print("Enter price (grn/unit): ");
                String price = scanner.next();

                System.out.print("Enter quality mark or 'none' to count it automatically (min value - 0, max value - 100): ");
                String qualityMark = scanner.next();

                System.out.println("Enter coffee status: \nCan be 'ordinary', 'higher', 'specialty'");
                String coffeeStatus = scanner.next();

                String cultivationPlace;
                System.out.println("""
                        Enter cultivation place like:\s
                        \nCountryName
                        stateName
                        farmName
                                                    
                        Also you can write 'none' for each data;""");
                String cultivationCountry = scanner.next();
                String cultivationState = scanner.next();
                String cultivationFarm = scanner.next();
                cultivationPlace = cultivationCountry + " " + cultivationState + " " + cultivationFarm;

                System.out.print("Enter transportation company or 'none': ");
                String transportation = scanner.next();

                System.out.print("Enter capping mark or '0' if you haven't information: ");
                String cappingMark = scanner.next();

                executeCommand(new AddCoffeeCommand(name, state, volume, weight, price, qualityMark,
                        coffeeStatus, cultivationPlace, transportation, cappingMark, manager));

                // Change coffee
            } else if ("7".equals(inputCommand)) {
                System.out.print("Enter id: ");
                String id = scanner.next();

                System.out.print("Enter name: ");
                String name = scanner.next();

                System.out.println("Enter aggregate state: \n\tCan be: 'cereal', 'ground', 'solubleInCans', 'solubleInSachets';");
                String state = scanner.next();

                System.out.print("Enter volume (cm^3): ");
                String volume = scanner.next();

                System.out.print("Enter netto weight (g): ");
                String weight = scanner.next();

                System.out.print("Enter price (grn/unit): ");
                String price = scanner.next();

                System.out.print("Enter quality mark or 'none' to count it automatically (min value - 0, max value - 100): ");
                String qualityMark = scanner.next();

                System.out.println("Enter coffee status: \nCan be 'ordinary', 'higher', 'specialty'");
                String coffeeStatus = scanner.next();

                String cultivationPlace;
                System.out.println("""
                        Enter cultivation place like:\s
                        \tCountryName
                        \tstateName
                        \tfarmName
                        Also you can write 'none' for each data;""");
                String cultivationCountry = scanner.next();
                String cultivationState = scanner.next();
                String cultivationFarm = scanner.next();
                cultivationPlace = cultivationCountry + " " + cultivationState + " " + cultivationFarm;

                System.out.print("Enter transportation company or 'none': ");
                String transportation = scanner.next();

                System.out.print("Enter capping mark or '0' if you haven't information: ");
                String cappingMark = scanner.next();

                executeCommand(new ChangeCoffeeCommand(id, name, state, volume, weight, price, qualityMark,
                        coffeeStatus, cultivationPlace, transportation, cappingMark, manager));

                // Delete coffee
            } else if ("8".equals(inputCommand)) {
                System.out.println("Enter coffee's id:");
                String id = scanner.next();

                executeCommand(new DeleteCoffeeCommand(id, manager));

                // Print coffee
            } else if ("9".equals(inputCommand)) {
                executeCommand(new PrintCoffeesCommand(manager));

                // Sort by quality mark
            } else if ("10".equals(inputCommand)) {
                executeCommand(new SortCoffeeByQualityCommand(manager));

                // Sort by correlation
            } else if ("11".equals(inputCommand)) {
                executeCommand(new SortCoffeeByCorrelationCommand(manager));

                // Search coffee by name
            } else if ("12".equals(inputCommand)) {
                System.out.println("Enter coffee's name:");
                String name = scanner.next();

                executeCommand(new SearchCoffeeByNameCommand(name, manager));

                // Search coffee by name & aggregate state
            } else if ("13".equals(inputCommand)) {
                System.out.println("Enter coffee's name:");
                String name = scanner.next();
                System.out.println("Enter aggregate state: \n\tCan be: cereal, ground, solubleInCans, solubleInSachets;");
                String state = scanner.next();

                executeCommand(new SearchCoffeeByAggregateStateCommand(name, state, manager));

                // Search coffee by quality mark
            } else if ("14".equals(inputCommand)) {
                System.out.println("Enter quality mark or 'none' to count it automatically");
                String qualityMark = scanner.next();

                executeCommand(new SearchCoffeeByQualityMarkCommand(qualityMark, manager));


                // Search coffee by quality mark in range(minValue, maxValue)
            } else if ("15".equals(inputCommand)) {
                System.out.println("Enter quality min value");
                String minValue = scanner.next();
                System.out.println("Enter quality max value");
                String maxValue = scanner.next();

                executeCommand(new SearchCoffeeByQualityRangeCommand(minValue, maxValue, manager));

                // Search coffee by correlation coefficient in range(minValue, maxValue)
            } else if ("16".equals(inputCommand)) {
                System.out.println("Enter correlation min value");
                String minValue = scanner.next();
                System.out.println("Enter correlation max value");
                String maxValue = scanner.next();

                executeCommand(new SearchCoffeeByCorrelationCommand(minValue, maxValue, manager));

                // Print found coffee
            } else if ("17".equals(inputCommand)) {
                executeCommand(new PrintFoundCoffeeCommand(manager));

                //Print van coffee goods
            } else if ("18".equals(inputCommand)) {
                System.out.println("Enter van's id:");
                String id = scanner.next();
                executeCommand(new PrintCoffeeGoodsCommand(manager, id));

            } else if ("help".equals(inputCommand)) {// HELP
                printInstructions();

                // EXIT
            } else if ("exit".equals(inputCommand)) {
                flag = false;
                executeCommand(new SaveDataCommand(manager));
            } else {
                System.out.println("Incorrect data!");
            }
        }
    }

    private void printInstructions() {
        System.out.println(
                """
                        'save'. Save data;
                        1. Add van;
                        2. Change van;
                        3. Delete van;
                        4. Load van;
                        5. Print vans;
                        6. Add coffee;
                        7. Change coffee;
                        8. Delete coffee;
                        9. Print coffees;
                        10. Sort coffees by quality mark;
                        11. Sort coffees by correlation;
                        12. Search coffee by name;
                        13. Search coffee by name & aggregate state;
                        14. Search coffee by quality mark;
                        15. Search coffee by quality mark in range(minValue, maxValue);
                        16. Search coffee by correlation coefficient in range(minValue, maxValue);
                        17. Print found coffee
                        18. Print van coffee goods;
                        'help'. Show instructions
                        'exit'. Save data and stop program"""
        );
    }

    private void executeCommand(Command command) {
        command.execute();
    }
}
