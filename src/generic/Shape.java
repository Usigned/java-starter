package generic;

public class Shape {
    static class Node<T> extends Shape { }

    public static void main(String[] args) {
        Node<Circle> nc = new Node<>();
        Node<? super Circle> ns = nc;
    }

}

class Circle extends Shape { }

class Rectangle extends Shape { }