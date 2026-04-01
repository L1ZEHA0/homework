package homework.grade1;

import java.time.LocalDateTime;

public class BikeService {
    
    private boolean locationValid;
    
    public String findAvailableBike(String location) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equals(location) && bike.isAvailable()) {
                System.out.println("你所请求的地点有可租赁车辆");
                locationValid = true;
                return bike.getBikeID();
            }
        }
        
        System.out.println("抱歉，你所请求的地点暂无可用车辆，请稍后再试");
        return null;
    }
    
    public boolean isLocationValid() {
        return locationValid;
    }
    
    public void reserveBike(String bikeID, String userEmail, RentalService rentalService) {
        if (bikeID != null) {
            for (Bike bike : BikeDatabase.bikes) {
                if (bike.getBikeID().equals(bikeID)) {
                    LocalDateTime tripStartTime = LocalDateTime.now();
                    bike.setIsAvailable(false);
                    bike.setLastUsedTime(tripStartTime);
                    
                    System.out.println("正在预约编号为" + bikeID + "的车辆，请按照屏幕提示找到车辆，开启愉快的骑行之旅");
                    
                    ActiveRental activeRental = new ActiveRental(bikeID, userEmail, tripStartTime);
                    rentalService.addRental(activeRental);
                    break;
                }
            }
        } else {
            System.out.println("抱歉，目前无法完成车辆预约，请稍后再试");
        }
    }
    
    public void returnBike(String bikeID) {
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