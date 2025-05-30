package com.example;

import com.example.commands.*;

import java.util.Scanner;
import java.util.Stack;

public class DrawingApp {
    private final Canvas canvas = new Canvas();
    private final Stack<Command> commandStack = new Stack<>();

    public static void main(String[] args) {
        DrawingApp app = new DrawingApp();
        app.run();
    }

    private void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the Drawing App!");
            boolean running = true;
            while (running) {
                System.out.print("Enter command: ");
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("Q")) {
                    running = false;
                    System.out.println("Exiting the Drawing App. Goodbye!");
                } else {
                    try {
                        Command command = CommandFactory.getCommand(input);
                        addCommand(command);
                        displayCanvas();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            }
        }
    }

    public void addCommand(Command command) {
        commandStack.push(command);
    }

    public void displayCanvas() {
        canvas.clear();
        for (Command command : commandStack) {
            command.execute(canvas);
        }
        System.out.println(canvas.display());
    }
}