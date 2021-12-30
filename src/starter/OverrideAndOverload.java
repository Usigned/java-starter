package starter;


class SuperClass {
    public SuperClass get(SuperClass s) {
        return null;
    }
}

class SubClass extends SuperClass {

    // Override
    public SubClass get(SuperClass s) {
        return null;
    }

    // OverLoad
    public SuperClass get(SubClass subClass) {
        return null;
    }
}

public class OverrideAndOverload {
}
