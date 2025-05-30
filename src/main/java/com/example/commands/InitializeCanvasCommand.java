package com.example.commands;

import com.example.Drawable;

public class InitializeCanvasCommand implements Command {

    private Integer width;
    private Integer height;

    public InitializeCanvasCommand(String commandArgs) {
        String[] args = commandArgs.split(" ");
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid initialize canvas command");
        }
        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        if (width < 2 || height < 2) {
            throw new IllegalArgumentException("Canvas width and height must be at least 2");
        }
        this.width = width;
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    @Override
    public void execute(Drawable canvas) {
        if (canvas == null) {
            throw new IllegalArgumentException("Canvas cannot be null");
        }
        canvas.initialize(width, height);
    }

}
