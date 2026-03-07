package homework.grade1;

public class Run {
    public static void main(String[] args) {
        ERyder bike1 = new ERyder("B001", 55, true, 25.3);
        bike1.printBikeDetails();
        bike1.ride();
        ERyder bike2= new ERyder();
        bike2.printBikeDetails();
        bike2.ride();
    }
}
