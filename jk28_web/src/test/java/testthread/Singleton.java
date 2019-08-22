package testthread;

/**
 * Created by 54934 on 2019/8/22.
 */
public class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            System.out.println(instance);
            instance = new Singleton();
        }
        return instance;
    }
}
