package ru.job4j.ood.srp.model;

public class Magnit extends Shop {
    private static final float DELIVERY = 300f;
    private final float costOrder;
    private float radius;
    private int timeOrder;

    public Magnit(float costOrder) {
        super(costOrder);
        this.costOrder = costOrder;
    }

    public Magnit(float costOrder, float radius) {
        super(costOrder, radius);
        this.costOrder = costOrder;
        this.radius = radius;
    }

    public Magnit(float costOrder, int timeOrder) {
        super(costOrder, timeOrder);
        this.costOrder = costOrder;
        this.timeOrder = timeOrder;
    }

    @Override
    public void order() {
        if (costOrder < 1500f) {
            throw new IllegalArgumentException(
                    "Стоимость заказа должна быть выше 1500 руб");
        }
        System.out.println("Заказ магазином Магнит принят");
    }

    @Override
    public float total() {
        return radius > 5f ? costOrder + DELIVERY : costOrder;
    }

    @Override
    public void setTimeOrder(int time) {
        timeOrder = time;
    }

    public static class Logic {
        public static void main(String[] args) {
            Shop shop = new Magnit(1200f);
            shop.order();
            Shop shop2 = new Magnit(1700f, 10f);
            System.out.println(shop2.total());
            Shop shop3 = new Magnit(1800f, 12);
            shop3.setTimeOrder(23);
        }
    }
}
