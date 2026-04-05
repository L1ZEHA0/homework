package homework.grade1;

public class RegisteredUsers {
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardExpiryDate;
    private String cardProvider;
    private int cvv;
    private String userType;
    private String[] lastThreeTrips;

    public RegisteredUsers() {
        this.fullName = "";
        this.emailAddress = "";
        this.dateOfBirth = "";
        this.cardNumber = 0;
        this.cardExpiryDate = "";
        this.cardProvider = "";
        this.cvv = 0;
        this.userType = "";
        this.lastThreeTrips = new String[3];
    }

    public RegisteredUsers(String fullName, String emailAddress, String dateOfBirth,
                           long cardNumber, String cardExpiryDate, String cardProvider,
                           int cvv, String userType, String[] lastThreeTrips) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.cardProvider = cardProvider;
        this.cvv = cvv;
        this.userType = userType;
        this.lastThreeTrips = lastThreeTrips;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getCardProvider() {
        return cardProvider;
    }

    public void setCardProvider(String cardProvider) {
        this.cardProvider = cardProvider;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String[] getLastThreeTrips() {
        return lastThreeTrips;
    }

    public void setLastThreeTrips(String[] lastThreeTrips) {
        this.lastThreeTrips = lastThreeTrips;
    }

    public double calculateFare(double baseFare) {
        return baseFare;
    }

    public void displayUserType() {
        System.out.println("普通用户");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== 用户信息 ===\n");
        sb.append("用户类型：").append(userType).append("\n");
        sb.append("姓名：").append(fullName).append("\n");
        sb.append("邮箱：").append(emailAddress).append("\n");
        sb.append("出生日期：").append(dateOfBirth).append("\n");
        sb.append("卡号：").append(maskCardNumber(cardNumber)).append("\n");
        sb.append("发卡方：").append(cardProvider).append("\n");
        sb.append("卡片有效期：").append(cardExpiryDate).append("\n");
        sb.append("\n最近三次骑行记录：\n");

        if (lastThreeTrips != null) {
            for (int i = 0; i < lastThreeTrips.length; i++) {
                if (lastThreeTrips[i] != null && !lastThreeTrips[i].isEmpty()) {
                    sb.append("第").append(i + 1).append("次骑行：").append(lastThreeTrips[i]).append("\n");
                }
            }
        }

        return sb.toString();
    }

    private String maskCardNumber(long cardNumber) {
        String cardNumStr = String.valueOf(cardNumber);
        if (cardNumStr.length() <= 4) {
            return cardNumStr;
        }
        String censoredPart = "*".repeat(cardNumStr.length() - 4);
        String lastFourDigits = cardNumStr.substring(cardNumStr.length() - 4);
        return censoredPart + lastFourDigits;
    }
}