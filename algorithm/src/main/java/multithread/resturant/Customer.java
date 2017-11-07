package multithread.resturant;

/**
 * @Description: 顾客类
 * 
 */
public class Customer {

	private int id; // 顾客编号
	private Food food; // 顾客点的食物

	public Customer(int id, Food food) {
		this.id = id;
		this.food = food;
	}

	public void order() {
		System.out.println(id + "号顾客点了" + food.getName());
	}

	public void eat() {
		System.out.println(id + "号顾客正在吃" + food.getName());
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}