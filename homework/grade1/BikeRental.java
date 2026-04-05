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
    private UserService userService = new UserService();
    
    public void simulateApplicationInput() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("这是电动自行车租赁流程的模拟演示");
        
        System.out.print("请输入您的邮箱地址：");
        emailAddress = scanner.nextLine();
        
        RegisteredUsers currentUser = findUserByEmail(emailAddress);
        
        if (currentUser == null) {
            System.out.println("未找到该邮箱对应的注册用户");
            System.out.print("您是否为已注册用户？（true/false）：");
            isRegisteredUser = Boolean.parseBoolean(scanner.nextLine());
            
            if (!isRegisteredUser) {
                System.out.println("你并非本站注册用户，建议完成注册");
                userRegistration.registration();
                scanner.close();
                return;
            }
        } else {
            isRegisteredUser = true;
            System.out.println("欢迎回来，" + currentUser.getFullName() + "！");
            currentUser.displayUserType();
        }
        
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
        
        if (currentUser != null) {
            rentalService.removeRental(bikeID, currentUser);
        } else {
            rentalService.removeRental(bikeID);
        }
        
        System.out.println("展示行程结束后的有效租赁信息");
        rentalService.viewActiveRentals();
        
        scanner.close();
    }
    
    private RegisteredUsers findUserByEmail(String email) {
        for (RegisteredUsers user : userService.getRegisteredUsersList()) {
            if (user.getEmailAddress().equals(email)) {
                return user;
            }
        }
        return null;
    }
    
    private String analyseRequest(boolean isRegistered, String email, String loc) {
        if (!isRegistered) {
            System.out.println("你并非本站注册用户，建议完成注册");
            userRegistration.registration();
        }
        
        return bikeService.findAvailableBike(loc);
    }
}
