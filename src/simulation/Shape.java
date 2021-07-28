package simulation;

public interface Shape {
    void draw();
    void setVisitor(Visitor visitor);
}

interface Visitor {
    void visit(Rectangle rectangle);
    void visit(Circle circle);
}

class Rectangle implements Shape {
    int height;
    int width;

    public Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public void draw() {
        System.out.format("%nRectangle: %d x %d", height, width);
    }

    @Override
    public void setVisitor(Visitor visitor) {
        visitor.visit(this);
    }
}

class Circle implements Shape {
    int r;

    public Circle(int r) {
        this.r = r;
    }

    @Override
    public void draw() {
        System.out.format("%nCircle: %d", r);
    }

    @Override
    public void setVisitor(Visitor visitor) {
        visitor.visit(this);
    }
}