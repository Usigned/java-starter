package Interface;

//Overriding vs Hiding
public abstract class Animal {
    public static void classMethod() {
        System.out.println("static Animal");
    }

    public void instanceMethod() {
        System.out.println("instance animal");
    }
}

class Cat extends Animal {

    //hiding
    public static void classMethod() {
        System.out.println("static Cat");
    }

    @Override
    public void instanceMethod() {
        System.out.println("instance Cat");
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        Animal animal = cat;
//        Animal.classMethod();
//        animal.instanceMethod();
        System.out.println(animal.getClass().getSimpleName());
    }
}