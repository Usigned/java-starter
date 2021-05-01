package practice;

public class HelloWorldAnonymousClasses {
    /*
    Anonymous classes are often used in GUI
     */

    abstract class HelloWorld implements IHelloWorld {
        String name;
        HelloWorld(String name) {
            this.name = name;
        }
        @Override
        public void greet() {
            greetSomeone(name);
        }

        abstract public void greetSomeone(String someone);
    }

    public void sayHello() {
//        this is a local class
        class EnglishGreeting implements IHelloWorld {
            String name = "world";
            @Override
            public void greet() {
                greetSomeone("world");
            }

            @Override
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hello " + name);
            }
        }

        IHelloWorld englishGreeting = new EnglishGreeting();

//        this is a anonymous class implementing a interface
        IHelloWorld chineseGreeting = new IHelloWorld() {
            String name = "世界";
            @Override
            public void greet() {
                greetSomeone("世界");
            }

            @Override
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("你好，" + name);
            }
        };

//        this is an anonymous class implementing a abstract class

        IHelloWorld no_ideaGreeting = new HelloWorld("!@#@#$$") {

            @Override
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("F%**K " + name);;
            }
        };

        englishGreeting.greet();
        chineseGreeting.greet();
        no_ideaGreeting.greet();

    }

    public static void main(String[] args) {
        HelloWorldAnonymousClasses app = new HelloWorldAnonymousClasses();
        app.sayHello();
    }
}
