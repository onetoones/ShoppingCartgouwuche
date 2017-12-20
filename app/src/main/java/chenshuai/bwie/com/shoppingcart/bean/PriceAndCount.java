package chenshuai.bwie.com.shoppingcart.bean;

/**
 * 姓名：陈帅
 * 类作用：
 * 日期：
 */

public class PriceAndCount {
    private double price;
    private int count;

    public PriceAndCount(double price, int count) {
        this.price = price;
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
