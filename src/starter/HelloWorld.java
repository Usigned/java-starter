package starter;

public class HelloWorld {
    public static void main(String[] args) {
//        Obj obj = null;
//        if (Math.random() > 0.5 ) {
//            obj = ObjFactory.createObj();
//        }
//        System.out.println(obj.toString());

        A a = new B();
        a.m();
    }
}

class Obj { }

class ObjFactory {
    static Obj createObj() {
        return new Obj();
    }
}


class A {
    int i = 1;
    void m() {
        System.out.println(this.i);
        n();
    }

    void n() {
        System.out.println("method in A");
    }
}

class B extends A {
    int i = 2;
    void n() {
        System.out.println(this.i);
        System.out.println("method in B");
    }
}