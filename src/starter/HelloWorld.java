package starter;

public class HelloWorld {
    public static void main(String[] args) {
        Obj obj = null;
        if (Math.random() > 0.5 ) {
            obj = ObjFactory.createObj();
        }
        System.out.println(obj.toString());
    }
}

class Obj { }

class ObjFactory {
    static Obj createObj() {
        return new Obj();
    }
}