package homework.grade1;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class UserRegistration {

    public static final double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0;
    public static final double VIP_DISCOUNT_UNDER_18 = 20.0;
    public static final double VIP_BASE_FEE = 100.0;

    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private double feeToCharge;
    private int cvv;
    private String userType;
    private boolean emailValid;
    private boolean minorAndBirthday;
    private boolean minor;
    private boolean ageValid;
    private boolean cardNumberValid;
    private boolean cardStillValid;
    private boolean validCVV;

    public void registration() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("欢迎来到 ERyder 注册系统");
        System.out.println("你有两个注册选项：");
        System.out.println("1. 注册为普通用户");
        System.out.println("2. 注册为 VIP 用户");
        System.out.print("请输入你的选择（1 或 2）：");

        int choice = scanner.nextInt();
        userType = (choice == 1) ? "普通用户" : "VIP 用户";

        scanner.nextLine();

        System.out.print("请输入您的全名：");
        fullName = scanner.nextLine();

        System.out.print("请输入您的邮箱地址：");
        emailAddress = scanner.nextLine();
        emailValid = analyseEmail(emailAddress);

        System.out.print("请输入您的出生日期（YYYY-MM-DD 格式）：");
        dateOfBirth = scanner.nextLine();
        LocalDate dob = LocalDate.parse(dateOfBirth);
        ageValid = analyseAge(dob);

        System.out.print("请输入您的卡号（仅支持 VISA、万事达、美国运通）：");
        cardNumber = scanner.nextLong();
        cardNumberValid = analyseCardNumber(cardNumber);

        System.out.print("请输入卡片有效期（MM/YY 格式）：");
        cardExpiryDate = scanner.next();
        cardStillValid = analyseCardExpiryDate(cardExpiryDate);

        System.out.print("请输入卡片安全码：");
        cvv = scanner.nextInt();
        validCVV = analyseCVV(cvv);

        finalCheckpoint();

        scanner.close();
    }

    private boolean analyseEmail(String email) {
        if (email.contains("@") && email.contains(".")) {
            System.out.println("邮箱有效");
            return true;
        } else {
            System.out.println("邮箱地址无效，将返回注册流程开头");
            registration();
            return false;
        }
    }

    private boolean analyseAge(LocalDate dob) {
        LocalDate today = LocalDate.now();
        int age = Period.between(dob, today).getYears();

        boolean isBirthday = (dob.getMonthValue() == today.getMonthValue() &&
                             dob.getDayOfMonth() == today.getDayOfMonth());

        if ("VIP 用户".equals(userType)) {
            if (isBirthday && age < 18 && age > 12) {
                System.out.println("生日快乐！");
                System.out.println("因你未满 18 岁且今日生日，VIP 订阅费享 25% 折扣！");
                minorAndBirthday = true;
            } else if (age < 18 && age > 12) {
                System.out.println("因你未满 18 岁，VIP 订阅费享 20% 折扣！");
                minor = true;
            }
        }

        if (age <= 12 || age > 120) {
            System.out.println("你年龄过小或已超出合理范围，抱歉无法注册。祝你愉快");
            System.exit(0);
        }

        return true;
    }

    private boolean analyseCardNumber(long cardNumber) {
        String cardNumStr = String.valueOf(cardNumber);
        int length = cardNumStr.length();

        int firstTwoDigits = Integer.parseInt(cardNumStr.substring(0, 2));
        int firstFourDigits = Integer.parseInt(cardNumStr.substring(0, 4));

        if ((length == 13 || length == 16) && cardNumStr.startsWith("4")) {
            cardProvider = "VISA";
        } else if (length == 16 && (firstTwoDigits >= 51 && firstTwoDigits <= 55 ||
                       firstFourDigits >= 2221 && firstFourDigits <= 2720)) {
            cardProvider = "万事达";
        } else if (length == 15 && (cardNumStr.startsWith("34") || cardNumStr.startsWith("37"))) {
            cardProvider = "美国运通";
        } else {
            System.out.println("抱歉，仅支持 VISA、万事达、美国运通卡片，请更换有效卡片重试");
            registration();
            return false;
        }

        return true;
    }

    private boolean analyseCardExpiryDate(String expiryDate) {
        int month = Integer.parseInt(expiryDate.substring(0, 2));
        int year = Integer.parseInt(expiryDate.substring(3, 5)) + 2000;

        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int currentMonth = today.getMonthValue();

        if (year > currentYear || (year == currentYear && month >= currentMonth)) {
            System.out.println("卡片有效");
            return true;
        } else {
            System.out.println("抱歉，你的卡片已过期，请更换卡片");
            registration();
            return false;
        }
    }

    private boolean analyseCVV(int cvv) {
        String cvvStr = String.valueOf(cvv);

        if ("美国运通".equals(cardProvider) && cvvStr.length() == 4) {
            System.out.println("卡片安全码有效");
            return true;
        } else if (("VISA".equals(cardProvider) || "万事达".equals(cardProvider)) && cvvStr.length() == 3) {
            System.out.println("卡片安全码有效");
            return true;
        } else {
            System.out.println("该卡片安全码无效");
            registration();
            return false;
        }
    }

    private void finalCheckpoint() {
        if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV) {
            chargeFees();
        } else {
            System.out.println("抱歉，注册失败，原因如下：");
            if (!emailValid) {
                System.out.println("邮箱地址无效");
            }
            if (!ageValid) {
                System.out.println("年龄无效");
            }
            if (!cardNumberValid) {
                System.out.println("卡号无效");
            }
            if (!cardStillValid) {
                System.out.println("卡片已过期");
            }
            if (!validCVV) {
                System.out.println("安全码无效");
            }
            System.out.println("将返回注册流程开头");
            registration();
        }
    }

    private void chargeFees() {
        if (minorAndBirthday) {
            feeToCharge = VIP_BASE_FEE * (100 - VIP_DISCOUNT_UNDER_18_BIRTHDAY) / 100;
        } else if (minor) {
            feeToCharge = VIP_BASE_FEE * (100 - VIP_DISCOUNT_UNDER_18) / 100;
        } else {
            feeToCharge = VIP_BASE_FEE;
        }

        String cardNumStr = String.valueOf(cardNumber);
        String lastFourDigits = cardNumStr.substring(cardNumStr.length() - 4);

        System.out.println("感谢支付");
        System.out.println("已向你尾号****的卡片扣除费用" + feeToCharge);
    }

    @Override
    public String toString() {
        String cardNumberStr = String.valueOf(cardNumber);
        String censoredPart = "*".repeat(cardNumberStr.length() - 4);
        String lastFourDigits = cardNumberStr.substring(cardNumberStr.length() - 4);
        String censoredNumber = censoredPart + lastFourDigits;

        return "注册成功！你的信息如下：\n" +
               "用户类型：" + userType + "\n" +
               "全名：" + fullName + "\n" +
               "邮箱地址：" + emailAddress + "\n" +
               "出生日期：" + dateOfBirth + "\n" +
               "卡号：" + censoredNumber + "\n" +
               "发卡机构：" + cardProvider + "\n" +
               "卡片有效期：" + cardExpiryDate;
    }

    public static void main(String[] args) {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.registration();
        System.out.println(userRegistration);
    }
}
