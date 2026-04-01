package homework.grade1;

import java.util.Scanner;

public class AdminPanel {
    
    private Scanner scanner = new Scanner(System.in);
    private UserService userService = new UserService();
    private BikeRental bikeRental = new BikeRental();
    
    public void userManagementOptions() {
        while (true) {
            System.out.println("\n欢迎进入 ERyder 电子骑行系统管理员面板");
            System.out.println("请选择操作：");
            System.out.println("1. 新增用户");
            System.out.println("2. 查看已注册用户");
            System.out.println("3. 删除已注册用户");
            System.out.println("4. 修改已注册用户信息");
            System.out.println("5. 演示自行车租赁系统");
            System.out.println("6. 退出系统");
            System.out.print("请输入选项（1-6）：");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    userService.addNewUsers();
                    break;
                case 2:
                    userService.viewRegisteredUsers();
                    break;
                case 3:
                    userService.removeRegisteredUsers();
                    break;
                case 4:
                    userService.updateRegisteredUsers();
                    break;
                case 5:
                    bikeRental.simulateApplicationInput();
                    break;
                case 6:
                    System.out.println("感谢使用 ERyder 管理系统，再见！");
                    scanner.close();
                    return;
                default:
                    System.out.println("选择无效，请重新尝试");
            }
        }
    }
}
