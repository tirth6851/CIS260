public class Product {

    private String name;
    private String code;
    private double price;
    private int count;

    public Product(String name, String code, double price, int count) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.count = count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void addInventory(int amt) {
        count = count + amt;
    }

    public void sellInventory(int amt) {
        count = count - amt;
    }
}

