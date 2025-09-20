package cce105a;

import java.util.ArrayDeque;
import java.util.Scanner;

class Container {
    private String id;
    private String description;
    private int weight;

    public Container(String id, String description, int weight) {
        this.id = id;
        this.description = description;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return id + " | " + description + " | " + weight + "kg";
    }
}

class Ship {
    private String name;
    private String captain;

    public Ship(String name, String captain) {
        this.name = name;
        this.captain = captain;
    }

    @Override
    public String toString() {
        return name + " | Capt. " + captain;
    }
}

public class PortContainerManagementSystem {
    private static ArrayDeque<Container> containerStack = new ArrayDeque<>();
    private static ArrayDeque<Ship> shipQueue = new ArrayDeque<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Port Container Management System ===");
            System.out.println("[1] Store container (push)");
            System.out.println("[2] View port containers");
            System.out.println("[3] Register arriving ship (enqueue)");
            System.out.println("[4] View waiting ships");
            System.out.println("[5] Load next ship (pop container + poll ship)");
            System.out.println("[0] Exit");
            System.out.print("Choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> storeContainer();
                case 2 -> viewContainers();
                case 3 -> registerShip();
                case 4 -> viewShips();
                case 5 -> loadNextShip();
                case 0 -> System.out.println("Exiting system");
                default -> System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0);
    }

    private static void storeContainer() {
        System.out.print("Enter container ID: ");
        String id = sc.nextLine();
        System.out.print("Enter description: ");
        String description = sc.nextLine();
        System.out.print("Enter weight (kg): ");	
        int weight = Integer.parseInt(sc.nextLine());

        Container contain = new Container(id, description, weight);
        containerStack.push(contain);
        System.out.println("Stored: " + contain);
    }

    private static void viewContainers() {
        if (containerStack.isEmpty()) {
            System.out.println("No containers at the port");
            return;
        }
        System.out.println("\nTOP →");
        for (Container contain : containerStack) {
            System.out.println(contain);
        }
        System.out.println("← BOTTOM");
    }

    private static void registerShip() {
        System.out.print("Enter ship name: ");
        String name = sc.nextLine();
        System.out.print("Enter captain's name: ");
        String captain = sc.nextLine();

        Ship ship = new Ship(name, captain);
        shipQueue.add(ship);
        System.out.println("Registered: " + ship);
    }

    private static void viewShips() {
        if (shipQueue.isEmpty()) {
            System.out.println("No ships waiting at the dock.");
            return;
        }
        System.out.println("\nFRONT →");
        for (Ship ship : shipQueue) {
            System.out.println(ship);
        }
        System.out.println("← REAR");
    }

    private static void loadNextShip() {
        if (containerStack.isEmpty()) {
            System.out.println("No containers available to load.");
            return;
        }
        if (shipQueue.isEmpty()) {
            System.out.println("No ships waiting to be loaded.");
            return;
        }

        Container contain = containerStack.pop();  
        Ship ship = shipQueue.poll();          
        System.out.println("Loaded: " + contain + " → " + ship);
        System.out.println("Remaining containers: " + containerStack.size());
        System.out.println("Remaining ships waiting: " + shipQueue.size());
    }
}
