package testresp;

/**
 * @description: son
 * @author: frank.wu
 * @create: 2021-11-17
 */
public class Son extends Parent{
    @Override
    void hello(){
        System.out.println("son");
    }

    public static void main(String[] args) {
        Parent parent = new Son();
        parent.hello();
    }
}
