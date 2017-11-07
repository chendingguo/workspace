package multithread.resturant;

/**
 * 厨师类
 */
public class Cook {
	private String name;
	private Food food;

	public Cook(String name, Food food) {
		this.name = name;
		this.food = food;
	}

	/**
	 * 厨师生产食物
	 */
	public void produce() {
		while (true) {
			try {
				synchronized (food) {
					boolean isAdd = food.add();
					if (!isAdd) {
						System.out.println(food.getName() + "已经到达最大值了，不能再生成了！数量为：" + food.getNum());
						food.wait();
					} else {
						System.out.println(food.getName() + "已经做好了，数量为：" + food.getNum());
						Thread.sleep(1000);
						food.notifyAll();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}
}