package com.example;

public interface Drawable {
    public void clear();
    String display();
    void initialize(int i, int j);
    void drawLine(int x1, int y1, int x2, int y2, char c);
    void drawRectangle(int x1, int y1, int x2, int y2, char c);
    void fill(int x, int y, char c);
}
