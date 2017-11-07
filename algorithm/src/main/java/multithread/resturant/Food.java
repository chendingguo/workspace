package multithread.resturant;
public class Food {
    private int num;    //食物的数量，这个为公共资源，多个线程竞争
    private String name;   

    public Food(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public synchronized boolean add(){
        if (!max()){
            num++;
            return true;
        }else {
            return false;
        }
    }

    public synchronized boolean sub(){
        if (!min()){
            num--;
            return true;
        }else {
            return false;
        }
    }

    public boolean max(){
        if (num >= 10){
            return true;
        }
        return false;
    }

    public boolean min(){
        if (num <= 0){
            return true;
        }
        return false;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}