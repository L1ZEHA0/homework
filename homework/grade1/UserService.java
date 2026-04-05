package homework.grade1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UserService {
    
    private List<RegisteredUsers> registeredUsersList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    
    public void addNewUsers() {
        System.out.print("请输入需要新增的用户数量：");
        int numberOfUsers = scanner.nextInt();
        scanner.nextLine();
        
        for (int i = 0; i < numberOfUsers; i++) {
            System.out.println("\n=== 录入第 " + (i + 1) + " 个用户信息 ===");
            
            System.out.print("请输入姓名：");
            String fullName = scanner.nextLine();
            
            System.out.print("请输入电子邮箱：");
            String emailAddress = scanner.nextLine();
            
            System.out.print("请输入出生日期（YYYY-MM-DD 格式）：");
            String dateOfBirth = scanner.nextLine();
            
            System.out.print("请输入卡号：");
            long cardNumber = scanner.nextLong();
            
            System.out.print("请输入发卡方：");
            String cardProvider = scanner.next();
            scanner.nextLine();
            
            System.out.print("请输入卡片有效期（MM/YY 格式）：");
            String cardExpiryDate = scanner.nextLine();
            
            System.out.print("请输入卡片安全码：");
            int cvv = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("请输入用户类型：");
            String userType = scanner.nextLine();
            
            String[] lastThreeTrips = new String[3];
            System.out.println("\n请录入该用户的最近三次骑行信息：");
            
            for (int j = 0; j < 3; j++) {
                System.out.println("\n--- 第 " + (j + 1) + " 次骑行 ---");
                
                System.out.print("骑行日期（YYYY-MM-DD 格式）：");
                String rideDate = scanner.nextLine();
                
                System.out.print("骑行起点：");
                String startPoint = scanner.nextLine();
                
                System.out.print("骑行终点：");
                String endPoint = scanner.nextLine();
                
                System.out.print("本次骑行消费金额（欧元）：");
                double fare = scanner.nextDouble();
                scanner.nextLine();
                
                System.out.print("骑行反馈（可为空，直接回车跳过）：");
                String feedback = scanner.nextLine();
                if (feedback.isEmpty()) {
                    feedback = "NULL";
                }
                
                StringBuilder tripInfo = new StringBuilder();
                tripInfo.append("日期：").append(rideDate);
                tripInfo.append("，起点：").append(startPoint);
                tripInfo.append("，终点：").append(endPoint);
                tripInfo.append("，消费（欧元）：").append(fare);
                tripInfo.append("，反馈：").append(feedback);
                
                lastThreeTrips[j] = tripInfo.toString();
            }
            
            RegisteredUsers newUser;
            if (userType.equalsIgnoreCase("VIP")) {
                newUser = new VIPUser(
                        fullName, emailAddress, dateOfBirth,
                        cardNumber, cardExpiryDate, cardProvider,
                        cvv, userType, lastThreeTrips
                );
            } else {
                newUser = new RegularUser(
                        fullName, emailAddress, dateOfBirth,
                        cardNumber, cardExpiryDate, cardProvider,
                        cvv, userType, lastThreeTrips
                );
            }
            
            registeredUsersList.add(newUser);
            System.out.println("\n用户 " + fullName + " 添加成功！");
        }
        
        System.out.println("\n成功添加 " + numberOfUsers + " 个用户！");
    }
    
    public void viewRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("暂无已注册用户可展示");
            return;
        }
        
        System.out.println("\n=== 已注册用户列表 ===");
        for (RegisteredUsers user : registeredUsersList) {
            System.out.println(user);
            System.out.println("------------------------");
        }
    }
    
    public void removeRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("暂无已注册用户可删除");
            return;
        }
        
        System.out.print("请输入要删除的用户电子邮箱：");
        String emailToRemove = scanner.nextLine();
        
        boolean found = false;
        Iterator<RegisteredUsers> iterator = registeredUsersList.iterator();
        
        while (iterator.hasNext()) {
            RegisteredUsers user = iterator.next();
            if (user.getEmailAddress().equals(emailToRemove)) {
                iterator.remove();
                System.out.println("用户 " + emailToRemove + " 已删除！");
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("未找到该电子邮箱对应的用户");
        }
    }
    
    public void updateRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("暂无已注册用户可修改");
            return;
        }
        
        System.out.print("请输入要修改信息的用户电子邮箱：");
        String emailToUpdate = scanner.nextLine();
        
        RegisteredUsers userToUpdate = null;
        for (RegisteredUsers user : registeredUsersList) {
            if (user.getEmailAddress().equals(emailToUpdate)) {
                userToUpdate = user;
                break;
            }
        }
        
        if (userToUpdate == null) {
            System.out.println("未找到该电子邮箱对应的用户");
            return;
        }
        
        System.out.println("\n=== 修改用户信息 ===");
        System.out.println("（直接按回车键可保留原信息，输入 0 可保留数值型原信息）\n");
        
        System.out.print("请输入新的姓名（按回车键不修改）：");
        String fullName = scanner.nextLine();
        if (!fullName.isEmpty()) {
            userToUpdate.setFullName(fullName);
        }
        
        System.out.print("请输入新的电子邮箱（按回车键不修改）：");
        String emailAddress = scanner.nextLine();
        if (!emailAddress.isEmpty()) {
            userToUpdate.setEmailAddress(emailAddress);
        }
        
        System.out.print("请输入新的出生日期（按回车键不修改）：");
        String dateOfBirth = scanner.nextLine();
        if (!dateOfBirth.isEmpty()) {
            userToUpdate.setDateOfBirth(dateOfBirth);
        }
        
        System.out.print("请输入新的卡号（输入 0 不修改）：");
        long cardNumber = scanner.nextLong();
        if (cardNumber != 0) {
            userToUpdate.setCardNumber(cardNumber);
        }
        
        System.out.print("请输入新的发卡方（按回车键不修改）：");
        String cardProvider = scanner.next();
        scanner.nextLine();
        if (!cardProvider.isEmpty()) {
            userToUpdate.setCardProvider(cardProvider);
        }
        
        System.out.print("请输入新的卡片有效期（按回车键不修改）：");
        String cardExpiryDate = scanner.nextLine();
        if (!cardExpiryDate.isEmpty()) {
            userToUpdate.setCardExpiryDate(cardExpiryDate);
        }
        
        System.out.print("请输入新的卡片安全码（输入 0 不修改）：");
        int cvv = scanner.nextInt();
        scanner.nextLine();
        if (cvv != 0) {
            userToUpdate.setCvv(cvv);
        }
        
        System.out.print("请输入新的用户类型（按回车键不修改）：");
        String userType = scanner.nextLine();
        if (!userType.isEmpty()) {
            userToUpdate.setUserType(userType);
        }
        
        System.out.println("\n用户信息更新成功！");
    }
    
    public List<RegisteredUsers> getRegisteredUsersList() {
        return registeredUsersList;
    }
}
