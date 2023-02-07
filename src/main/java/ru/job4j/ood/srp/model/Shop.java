package ru.job4j.ood.srp.model;

public class Shop {
    private static final WorkingHours WORKING_HOURS = new WorkingHours(10, 22);
    private static final float DELIVERY = 200f;
    private final float costOrder;
    private float radius;
    private int timeOrder;

    public Shop(float costOrder) {
        this.costOrder = costOrder;
    }

    public Shop(float costOrder, float radius) {
        this.costOrder = costOrder;
        this.radius = radius;
    }

    public Shop(float costOrder, int timeOrder) {
        this.costOrder = costOrder;
        this.timeOrder = timeOrder;
        validate();
    }

    protected void validate() {
        if (!(timeOrder > WORKING_HOURS.open()
                && timeOrder < WORKING_HOURS.close())) {
            throw new IllegalArgumentException("Магазин закрыт!");
        }
    }

    public void setTimeOrder(int time) {
        timeOrder = time;
        validate();
    }

    public float total() {
        return radius > 15f ? costOrder + DELIVERY : costOrder;
    }

    public void order() {
        if (costOrder < 1000f) {
            throw new IllegalArgumentException(
                    "Стоимость заказа должна быть выше 1000 руб");
        }
        System.out.println("Заказ принят");
    }
}
