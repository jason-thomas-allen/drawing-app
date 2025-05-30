package com.example.commands;

import com.example.Drawable;

public class FillCommand implements Command {
    private final int x;
    private final int y;
    private final char color;

    public FillCommand(String commandArgs) {
        String[] args = commandArgs.split(" ");
        if (args.length != 3) {
            throw new IllegalArgumentException("Invalid fill command");
        }
        x = Integer.parseInt(args[0]);
        y = Integer.parseInt(args[1]);
        color = args[2].charAt(0);
        if (x < 1 || y < 1) {
            throw new IllegalArgumentException("Coordinates must be positive integers");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getColor() {
        return color;
    }

    @Override
    public void execute(Drawable canvas) {
        if (canvas == null) {
            throw new IllegalArgumentException("Canvas cannot be null");
        }
        canvas.fill(x, y, color);
    }

}
