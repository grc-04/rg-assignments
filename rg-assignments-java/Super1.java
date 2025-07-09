package Core_Java;

public class Super1
{
    static void show()
    {
        System.out.println("super class show method");
    }
    static class StaticMethods
    {
        void show()
        {
            System.out.println("sub class show method");
        }
    }
    public static void main(String[]args)
    {
        Super1.show();
        new Super1.StaticMethods().show();
    }
}
