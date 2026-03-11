package homework.grade1;

public class ERyder {

    public static final String COMPANY_NAME = "ERyder";
    public static final double BASE_FARE = 1.0;
    public static final double PER_MINUTE_FARE = 0.5;

    private final String LINKED_ACCOUNT;
    private final String LINKED_PHONE_NUMBER;

    private String bikeID;
    private int batteryLevel;
    private boolean isAvailable;
    private double kmDriven;

    private int totalUsageInMinutes;
    private double totalFare;


    public ERyder() {
        this.bikeID = "";
        this.batteryLevel = 0;
        this.isAvailable = false;
        this.kmDriven = 0.0;
        this.LINKED_ACCOUNT = "";
        this.LINKED_PHONE_NUMBER = "";
        this.totalUsageInMinutes = 0;
        this.totalFare = 0.0;
    }


    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, double kmDriven) {
        this.bikeID = bikeID;
        this.batteryLevel = batteryLevel;
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        this.LINKED_ACCOUNT = "default_user";
        this.LINKED_PHONE_NUMBER = "00000000000";
        this.totalUsageInMinutes = 0;
        this.totalFare = 0.0;
    }


    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, double kmDriven, 
                  String linkedAccount, String linkedPhoneNumber) {
        this.bikeID = bikeID;
        this.batteryLevel = batteryLevel;
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        this.LINKED_ACCOUNT = linkedAccount;
        this.LINKED_PHONE_NUMBER = linkedPhoneNumber;
        this.totalUsageInMinutes = 0;
        this.totalFare = 0.0;
    }


    public void ride() {
        if (batteryLevel > 0 && isAvailable) {
            System.out.println("自行车 " + bikeID + " 可使用");
        } else {
            System.out.println("自行车 " + bikeID + " 不可使用");
        }
    }


    public void printBikeDetails() {
        System.out.println("=== 自行车信息 ===");
        System.out.println("自行车编号：" + bikeID);
        System.out.println("电池电量：" + batteryLevel + "%");
        System.out.println("使用状态：" + (isAvailable ? "可用" : "不可用"));
        System.out.println("行驶里程：" + kmDriven + " 千米");
    }


    public void printRideDetails(int usageInMinutes) {
        this.totalUsageInMinutes = usageInMinutes;
        this.totalFare = calculateFare(usageInMinutes);
        
        System.out.println("=== 骑行详情 ===");
        System.out.println("公司名称：" + COMPANY_NAME);
        System.out.println("关联账户：" + LINKED_ACCOUNT);
        System.out.println("关联手机号：" + LINKED_PHONE_NUMBER);
        System.out.println("车辆编号：" + bikeID);
        System.out.println("使用时长：" + usageInMinutes + " 分钟");
        System.out.println("总费用：" + totalFare + " 元");
    }


    private double calculateFare(int usageInMinutes) {
        return BASE_FARE + (PER_MINUTE_FARE * usageInMinutes);
    }


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
