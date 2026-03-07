package homework.grade1;

public class ERyder {
    
    // 成员变量
    private String bikeID;
    private int batteryLevel;
    private boolean isAvailable;
    private double kmDriven;

    // 无参构造方法
    public ERyder() {
    }

    // 全参构造方法
    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, double kmDriven) {
        this.bikeID = bikeID;
        this.batteryLevel = batteryLevel;
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
    }

    // ride() 方法
    public void ride() {
        if (batteryLevel > 0 && isAvailable) {
            System.out.println("自行车 " + bikeID + " 可使用");
        } else {
            System.out.println("自行车 " + bikeID + " 不可使用");
        }
    }

    // printBikeDetails() 方法
    public void printBikeDetails() {
        System.out.println("=== 自行车信息 ===");
        System.out.println("自行车编号：" + bikeID);
        System.out.println("电池电量：" + batteryLevel + "%");
        System.out.println("使用状态：" + (isAvailable ? "可用" : "不可用"));
        System.out.println("行驶里程：" + kmDriven + " 千米");
    }

    // Getters 和 Setters

    public String getBikeID() {
        return bikeID;
    }

    public void setBikeID(String bikeID) {
        this.bikeID = bikeID;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        // 校验电量值，仅允许设置 0 到 100 之间的数值
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        } else {
            System.out.println("错误：电池电量必须在 0 到 100 之间！");
        }
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public double getKmDriven() {
        return kmDriven;
    }

    public void setKmDriven(double kmDriven) {
        this.kmDriven = kmDriven;
    }
}
