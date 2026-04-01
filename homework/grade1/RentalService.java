package homework.grade1;

import java.util.Iterator;
import java.util.LinkedList;

public class RentalService {
    
    private LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();
    
    public void addRental(ActiveRental rental) {
        activeRentalsList.add(rental);
    }
    
    public void removeRental(String bikeID) {
        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while (iterator.hasNext()) {
            ActiveRental rental = iterator.next();
            if (rental.getBikeID().equals(bikeID)) {
                iterator.remove();
                break;
            }
        }
    }
    
    public void viewActiveRentals() {
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
    
    public LinkedList<ActiveRental> getActiveRentalsList() {
        return activeRentalsList;
    }
}
