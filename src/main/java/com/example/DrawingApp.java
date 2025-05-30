package com.example;

import com.example.commands.*;

import java.util.Scanner;

public class DrawingApp {
    private final Drawable canvas;
    private final CommandFactory commandFactory;

    public DrawingApp() {
        this(new Canvas(), new CommandFactory());
    }

    public DrawingApp(Drawable canvas, CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
        this.canvas = canvas;
    }

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
                if (input.isEmpty()) {
                    System.out.println("No command entered. Please try again.");
                    continue;
                }
                if (input.equalsIgnoreCase("Q")) {
                    running = false;
                    System.out.println("Exiting the Drawing App. Goodbye!");
                } else {
                    try {
                        Command command = commandFactory.getCommand(input);
                        commandFactory.addCommand(command);
                        displayCanvas();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            }
        }
    }

    public void displayCanvas() {
        canvas.clear();
        for (Command command : commandFactory.getCommandHistory()) {
            command.execute(canvas);
        }
        System.out.println(canvas.display());
    }
}