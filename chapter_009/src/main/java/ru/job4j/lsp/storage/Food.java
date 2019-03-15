package ru.job4j.lsp.storage;

        /*Часть 1.
        1. Создать класс Food с полями. Name, expireDate, createDate, price, discount.
        На основе класса Food создать другие продукты.
        2. Создать классы хранилище продуктов Warehouse, Shop, Trash.
        Создать класс обработчик перераспределения продуктов в место использования. ControlQuality.
        Класс должен перераспределять еду по хранилищам в зависимости от условиый.
        3.1. Если срок годности израсходован меньше чем на 25% направить в Warehouse.
        3.2 Если срок годности от 25% до 75% направить в Shop
        3.3. Если срок годности больше 75% то выставить скидку на продукт и отправить в Shop
        3.4. Если срок годности вышел. Отправить продукт в мусорку.
        В данной задаче надо использовать шаблон проектирование - https://www.tutorialspoint.com/design_pattern/strategy_pattern.htm
        Нельзя использовать instanceOf или if ("Shop".equals(storage.getName())*/

import java.time.LocalDate;

public class Food {
    private String name;
    private LocalDate expireDate;
    private LocalDate createDate;
    private int price;
    private int discount;

    public Food(String name, LocalDate expireDate, LocalDate createDate, int price) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = 0;
    }

    public int getExpirePercent() {
        return (100 * (getExpireDate().compareTo(LocalDate.now())))
                / (getExpireDate().compareTo(getCreateDate()));
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }


    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Food)) {
            return false;
        }

        Food food = (Food) o;

        if (price != food.price) {
            return false;
        }
        if (discount != food.discount) {
            return false;
        }
        if (!name.equals(food.name)) {
            return false;
        }
        if (!expireDate.equals(food.expireDate)) {
            return false;
        }
        return createDate.equals(food.createDate);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + expireDate.hashCode();
        result = 31 * result + createDate.hashCode();
        result = 31 * result + price;
        result = 31 * result + discount;
        return result;
    }
}
