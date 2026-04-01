package homework.grade1;

import java.util.Scanner;

public class BikeRental {
    
    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private String bikeID;
    
    private UserRegistration userRegistration = new UserRegistration();
    private BikeService bikeService = new BikeService();
    private RentalService rentalService = new RentalService();
    
    public void simulateApplicationInput() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("这是电动自行车租赁流程的模拟演示");
        
        System.out.print("请输入您的邮箱地址：");
        emailAddress = scanner.nextLine();
        
        System.out.print("您是否为已注册用户？（true/false）：");
        isRegisteredUser = Boolean.parseBoolean(scanner.nextLine());
        
        System.out.print("请输入您尝试租赁车辆的地点：");
        location = scanner.nextLine();
        
        System.out.println("正在模拟租赁请求的分析过程");
        bikeID = analyseRequest(isRegisteredUser, emailAddress, location);
        
        if (!bikeService.isLocationValid()) {
            scanner.close();
            return;
        }
        
        System.out.println("正在模拟电动自行车预约流程");
        bikeService.reserveBike(bikeID, emailAddress, rentalService);
        
        System.out.println("展示当前有效租赁信息");
        rentalService.viewActiveRentals();
        
        System.out.println("正在模拟行程结束流程");
        bikeService.returnBike(bikeID);
        rentalService.removeRental(bikeID);
        
        System.out.println("展示行程结束后的有效租赁信息");
        rentalService.viewActiveRentals();
        
        scanner.close();
    }
    
    private String analyseRequest(boolean isRegistered, String email, String loc) {
        if (isRegistered) {
            System.out.println("欢迎回来，" + email + "！");
        } else {
            System.out.println("你并非本站注册用户，建议完成注册");
            userRegistration.registration();
        }
        
        return bikeService.findAvailableBike(loc);
    }
}
