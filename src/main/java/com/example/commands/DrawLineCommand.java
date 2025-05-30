package com.example.commands;

import com.example.Drawable;

public class DrawLineCommand implements Command {

    private int x1, y1, x2, y2;
    private char color;

    public DrawLineCommand(String commandArgs) {
    
        String[] args = commandArgs.split(" ");
        if (args.length != 5) {
            throw new IllegalArgumentException("Invalid draw line command");
        }
        x1 = Integer.parseInt(args[0]);
        y1 = Integer.parseInt(args[1]);
        x2 = Integer.parseInt(args[2]);
        y2 = Integer.parseInt(args[3]);
        color = args[4].charAt(0);
        if (x1 < 1 || y1 < 1 || x2 < 1 || y2 < 1) {
            throw new IllegalArgumentException("Coordinates must be positive integers");
        }
        if (x1 != x2 && y1 != y2) {
            throw new IllegalArgumentException("Line must be horizontal or vertical");
        }
    }

    @Override
    public void execute(Drawable canvas) {
        if (canvas == null) {
            throw new IllegalArgumentException("Canvas cannot be null");
        }
        canvas.drawLine(x1, y1, x2, y2, color);
    }

    public int getStartX() {
        return x1;
    }

    public int getStartY() {
        return y1;
    }

    public int getEndX() {
        return x2;
    }

    public int getEndY() {
        return y2;
    }

}
