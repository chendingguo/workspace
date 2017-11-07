package multithread.resturant;

import java.util.List;

/**
 * @Description: 服务员
 */
public class Waiter {
    private List<Customer> customers;
    private Cook cook;
    private Food food;

    public Waiter(List<Customer> customers, Cook cook, Food food) {
        this.customers = customers;
        this.cook = cook;
        this.food = food;
    }

    public void server() {
        for (Customer customer : customers){
            synchronized (food){
                System.out.println("欢迎光临!");
                System.out.println(customer.getId()+"号客人开始点餐");
                customer.order();
                if (customer.getFood().min()){
                    food.notifyAll();
                    try {
                        food.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(customer.getId()+"号客人的"+food.getName()+"好了！");
                customer.getFood().sub();
                customer.eat();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Cook getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }
}