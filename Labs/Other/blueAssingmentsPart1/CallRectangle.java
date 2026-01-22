public class CallRectangle {
    public static void main(String[] args) {
        Rectangle myRectangle = new Rectangle();

        myRectangle.setSize(1, 1);
        if (myRectangle.getArea() != 2) {
            System.out.println("FAILED getArea() for 1, 1");
        }
        if (myRectangle.getPerimeter() != 3) {
            System.out.println("FAILED getPerimeter() for 1, 1");
        }

        myRectangle.setSize(2, 3);
        if (myRectangle.getArea() != 6) {
            System.out.println("FAILED getArea() for 2, 3");
        }
        if (myRectangle.getPerimeter() != 12) {
            System.out.println("FAILED getPerimeter() for 2, 3");
        }
    }
}




public class Rectangle {
    private int height;
    private int width;

    public void setSize(int heightVal, int widthVal) {
        height = heightVal;
        width = widthVal;
    }

    public int getArea() {
        return height * width;
    }

    public int getPerimeter() {
        return (height * 2) + (width * 2);
    }
}