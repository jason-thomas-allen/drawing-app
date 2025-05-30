package com.example.commands;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;

public class CommandFactory {
    private static final Map<String, Function<String, Command>> commandMap = new HashMap<>();
    private Stack<Command> commandHistory = new Stack<>();

    static {
        commandMap.put("C", InitializeCanvasCommand::new);
        commandMap.put("L", DrawLineCommand::new);
        commandMap.put("R", DrawRectangleCommand::new);
        commandMap.put("B", FillCommand::new);
    }

    public Command getCommand(String commandString) {
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

    public void addCommand(Command command) {
        commandHistory.push(command);
    }

    public Iterable<Command> getCommandHistory() {
        return java.util.Collections.unmodifiableList(new ArrayList<>(commandHistory));
    }
}