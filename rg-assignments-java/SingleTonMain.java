package Core_Java;

class Singleton {
    private static Singleton instance;

    private Singleton() {
        if (instance != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello, I am a singleton instance!");
    }
}

public class SingleTonMain {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();

        singleton.showMessage();
    }
}
