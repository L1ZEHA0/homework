package homework.grade1;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class BikeRental {

    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private LocalDateTime tripStartTime;
    private String bikeID;
    private boolean locationValid;

    private UserRegistration userRegistration = new UserRegistration();
    private ActiveRental activeRental;
    private LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();

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

        if (!locationValid) {
            return;
        }

        System.out.println("正在模拟电动自行车预约流程");
        reserveBike(bikeID);

        System.out.println("展示当前有效租赁信息");
        viewActiveRentals();

        System.out.println("正在模拟行程结束流程");
        removeTrip(bikeID);

        System.out.println("展示行程结束后的有效租赁信息");
        viewActiveRentals();

        scanner.close();
    }

    private String analyseRequest(boolean isRegistered, String email, String loc) {
        if (isRegistered) {
            System.out.println("欢迎回来，" + email + "！");
        } else {
            System.out.println("你并非本站注册用户，建议完成注册");
            userRegistration.registration();
        }

        return validateLocation(loc);
    }

    private String validateLocation(String loc) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equals(loc) && bike.isAvailable()) {
                System.out.println("你所请求的地点有可租赁车辆");
                locationValid = true;
                return bike.getBikeID();
            }
        }

        System.out.println("抱歉，你所请求的地点暂无可用车辆，请稍后再试");
        return null;
    }

    private void reserveBike(String bikeID) {
        if (bikeID != null) {
            for (Bike bike : BikeDatabase.bikes) {
                if (bike.getBikeID().equals(bikeID)) {
                    tripStartTime = LocalDateTime.now();
                    bike.setIsAvailable(false);
                    bike.setLastUsedTime(tripStartTime);

                    System.out.println("正在预约编号为" + bikeID + "的车辆，请按照屏幕提示找到车辆，开启愉快的骑行之旅");

                    activeRental = new ActiveRental(bikeID, emailAddress, tripStartTime);
                    activeRentalsList.add(activeRental);
                    break;
                }
            }
        } else {
            System.out.println("抱歉，目前无法完成车辆预约，请稍后再试");
        }
    }

    private void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("当前无有效租赁订单");
        } else {
            System.out.println("\n=== 有效租赁列表 ===");
            for (ActiveRental rental : activeRentalsList) {
                System.out.println(rental);
            }
            System.out.println("==================\n");
        }
    }

    private void removeTrip(String bikeID) {
        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while (iterator.hasNext()) {
            ActiveRental rental = iterator.next();
            if (rental.getBikeID().equals(bikeID)) {
                iterator.remove();
                break;
            }
        }

        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setIsAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                System.out.println("你的行程已结束，感谢选择我们的骑行服务");
                break;
            }
        }
    }
}