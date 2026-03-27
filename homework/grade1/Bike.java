package homework.grade1;

import java.time.LocalDateTime;

public class Bike {
    private String bikeID;
    private boolean isAvailable;
    private int batteryLevel;
    private LocalDateTime lastUsedTime;
    private String location;

    public Bike() {
    }

    public Bike(String bikeID, boolean isAvailable, int batteryLevel, LocalDateTime lastUsedTime, String location) {
        this.bikeID = bikeID;
        this.isAvailable = isAvailable;
        this.batteryLevel = batteryLevel;
        this.lastUsedTime = lastUsedTime;
        this.location = location;
    }

    public String getBikeID() {
        return bikeID;
    }

    public void setBikeID(String bikeID) {
        this.bikeID = bikeID;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        } else {
            System.out.println("错误：电池电量必须在 0 到 100 之间！");
        }
    }

    public LocalDateTime getLastUsedTime() {
        return lastUsedTime;
    }

    public void setLastUsedTime(LocalDateTime lastUsedTime) {
        this.lastUsedTime = lastUsedTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "自行车{" +
                "编号='" + bikeID + '\'' +
                ", 可租赁=" + isAvailable +
                ", 电量=" + batteryLevel +
                ", 最后使用时间=" + lastUsedTime +
                ", 位置='" + location + '\'' +
                '}';
    }
}