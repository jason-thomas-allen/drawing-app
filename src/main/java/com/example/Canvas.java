package com.example;

public class Canvas implements Drawable {

    private int width;
    private int height;
    private char[][] pixels;

    public Canvas() {
        initialize(0, 0);
    }

    public void initialize(int i, int j) {
        this.width = i;
        this.height = j;
        this.pixels = new char[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[y][x] = ' ';
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String display() {
        StringBuilder sb = new StringBuilder();
        // Top border
        sb.append("-");
        for (int i = 0; i < width; i++) {
            sb.append("-");
        }
        sb.append("-\n");
        // Canvas rows
        for (int j = 0; j < height; j++) {
            sb.append("|");
            for (int i = 0; i < width; i++) {
                sb.append(pixels[j][i]);
            }
            sb.append("|\n");
        }
        // Bottom border
        sb.append("-");
        for (int i = 0; i < width; i++) {
            sb.append("-");
        }
        sb.append("-");
        return sb.toString();
    }

    public void drawLine(int x1, int y1, int x2, int y2, char c) {
        // Convert to 0-based indices
        x1--; y1--; x2--; y2--;
        if (y1 == y2) { // horizontal line
            for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                if (x >= 0 && x < width && y1 >= 0 && y1 < height) {
                    pixels[y1][x] = c;
                }
            }
        } else if (x1 == x2) { // vertical line
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                if (x1 >= 0 && x1 < width && y >= 0 && y < height) {
                    pixels[y][x1] = c;
                }
            }
        }
    }

    public void drawRectangle(int x1, int y1, int x2, int y2, char c) {
        drawLine(x1, y1, x2, y1, c); // Top
        drawLine(x1, y2, x2, y2, c); // Bottom
        drawLine(x1, y1, x1, y2, c); // Left
        drawLine(x2, y1, x2, y2, c); // Right
    }

    public void clear() {
        if (pixels == null) {
            return;
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[y][x] = ' ';
            }
        }
    }

    public void fill(int i, int j, char c) {
        // Convert to 0-based indices
        int x = i - 1;
        int y = j - 1;
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return;
        }
        char target = pixels[y][x];
        if (target == c || target == '-' || target == '|') {
            return;
        }
        floodFill(x, y, target, c);
    }

    private void floodFill(int x, int y, char target, char replacement) {
        if (x < 0 || x >= width || y < 0 || y >= height) return;
        if (pixels[y][x] != target) return;
        pixels[y][x] = replacement;
        floodFill(x + 1, y, target, replacement);
        floodFill(x - 1, y, target, replacement);
        floodFill(x, y + 1, target, replacement);
        floodFill(x, y - 1, target, replacement);
    }
}
