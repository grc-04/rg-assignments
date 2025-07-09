package Core_Java;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 120) { // Assuming age is within a valid range
            this.age = age;
        } else {
            System.out.println("Invalid age! Please provide a valid age.");
        }
    }
}


public class EncapsulatedMain {
    public static void main(String[] args) {
        // Create a new Person object
        Person person = new Person("Alice", 30);

        // Access and modify attributes using public methods
        System.out.println("Name: " + person.getName());  // Output: Name: Alice
        System.out.println("Age: " + person.getAge());    // Output: Age: 30

        // Modify attributes using setter methods
        person.setName("Bob");
        person.setAge(25);

        // Access modified attributes
        System.out.println("Updated Name: " + person.getName());  // Output: Updated Name: Bob
        System.out.println("Updated Age: " + person.getAge());    // Output: Updated Age: 25
    }
}
