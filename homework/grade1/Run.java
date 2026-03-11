package homework.grade1;

public class Run {
    public static void main(String[] args) {
        ERyder bike1 = new ERyder("B001", 55, true, 25.3);
        bike1.printBikeDetails();
        bike1.ride();

        ERyder bike2 = new ERyder("B002", 80, true, 10.5, "zhangsan", "13800138000");
        bike2.printBikeDetails();
        bike2.ride();

        System.out.println("\n=== 骑行详情展示 ===");
        bike1.printRideDetails(30);
        System.out.println();
        bike2.printRideDetails(45);
    }
}
