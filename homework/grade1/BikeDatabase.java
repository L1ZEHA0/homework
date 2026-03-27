package homework.grade1;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BikeDatabase {
    public static ArrayList<Bike> bikes = new ArrayList<>();

    static {
        bikes.add(new Bike("BJ001", true, 85, LocalDateTime.now().minusDays(2), "北京朝阳公园"));
        bikes.add(new Bike("BJ002", true, 92, LocalDateTime.now().minusDays(1), "北京中关村"));
        bikes.add(new Bike("BJ003", false, 45, LocalDateTime.now().minusHours(3), "北京西直门"));
        bikes.add(new Bike("BJ004", true, 78, LocalDateTime.now().minusDays(5), "北京国贸"));
        bikes.add(new Bike("BJ005", true, 60, LocalDateTime.now().minusDays(3), "北京朝阳公园"));

        bikes.add(new Bike("SH001", true, 88, LocalDateTime.now().minusDays(1), "上海人民广场"));
        bikes.add(new Bike("SH002", false, 30, LocalDateTime.now().minusHours(5), "上海陆家嘴"));
        bikes.add(new Bike("SH003", true, 95, LocalDateTime.now().minusDays(4), "上海南京路"));

        bikes.add(new Bike("GZ001", true, 70, LocalDateTime.now().minusDays(2), "广州天河城"));
        bikes.add(new Bike("GZ002", true, 82, LocalDateTime.now().minusDays(1), "广州珠江新城"));
        bikes.add(new Bike("GZ003", false, 25, LocalDateTime.now().minusHours(8), "广州北京路"));
    }
}
