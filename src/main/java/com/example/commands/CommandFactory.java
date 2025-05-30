package com.example.commands;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CommandFactory {
    private static final Map<String, Function<String, Command>> commandMap = new HashMap<>();

    static {
        commandMap.put("C", InitializeCanvasCommand::new);
        commandMap.put("L", DrawLineCommand::new);
        commandMap.put("R", DrawRectangleCommand::new);
        commandMap.put("B", FillCommand::new);
    }

    public static Command getCommand(String commandString) {
        String trimmed = commandString.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Command cannot be empty");
        }
        String commandName = trimmed.substring(0, 1).toUpperCase();
        String commandArgs = trimmed.substring(1).trim();
        Function<String, Command> supplier = commandMap.get(commandName);
        if (supplier == null) {
            throw new IllegalArgumentException("Unknown command: " + commandString);
        }
        return supplier.apply(commandArgs);
    }
}