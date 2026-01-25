public class Main {
    public static void main(String[] args) {
        Product p = new Product("Apple", "SKU234", 0.40, 3);

        // Test 1: initial values
        System.out.println("Name: " + p.getName());
        System.out.println("Code: " + p.getCode());
        System.out.printf("Price: %.2f%n", p.getPrice());
        System.out.println("Count: " + p.getCount());
        System.out.println();

        // Test 2: inventory changes
        p.addInventory(10);
        p.sellInventory(5);

        System.out.println("Name: " + p.getName());
        System.out.println("Code: " + p.getCode());
        System.out.printf("Price: %.2f%n", p.getPrice());
        System.out.println("Count: " + p.getCount());
        System.out.println();

        // Test 3: setters
        p.setName("Golden Delicious");
        p.setCode("SKU555");
        p.setPrice(0.55);
        p.setCount(4);

        System.out.println("Name: " + p.getName());
        System.out.println("Code: " + p.getCode());
        System.out.printf("Price: %.2f%n", p.getPrice());
        System.out.println("Count: " + p.getCount());
    }
}
