class A {
    void method() {
        System.out.println("A's method");
    }
}

class B extends A {
    void method() {
        System.out.println("B's method");
        super.method(); // Calls A's method
    }
}

class C extends B {
    @Override
    void method() {
        System.out.println("C's method");
        super.method(); // Calls B's method first
    }
}

public class Main {
    public static void main(String[] args) {
        C obj = new C();
        obj.method();
    }
}
