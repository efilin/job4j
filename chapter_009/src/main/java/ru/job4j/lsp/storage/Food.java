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
        Нельзя использовать instanceOf или if ("Shop".equals(storage.getName())

        Часть 2.

        1. От хозяина предприятие пришло новое условие требование. На складе Warehouse не хватает места для хранения
        и поэтому нужно добавить новый склад. Ваше решение?
        2. Еще одно новое условие. Появились продукты, которые можно переработать после исхода срока годности.
        Нужно расширить программу. Что бы продукты в флагом canReproduct отправлялись на переработку.
        3. Еще одно требование. Овощи пришедшие на обработку и попадающие на склад.
        Должны храниться в специальном складе с низкой температурой. Ваши решения.
        В данном задание надо сделать расширение кода за счет использование шаблона проектирование - декоратор - https://www.tutorialspoint.com/design_pattern/decorator_pattern.htm*/

import java.time.LocalDate;
import java.time.Period;

public class Food {
    private String name;
    private LocalDate createDate;
    private LocalDate expireDate;
    private int price;
    private int discount;
    private boolean canReproduct;
    private boolean isVegetable;

    public Food(String name, LocalDate createDate, LocalDate expireDate, int price, boolean canReproduct, boolean isVegetable) {
        this.name = name;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.price = price;
        this.discount = 0;
        this.canReproduct = canReproduct;
        this.isVegetable = isVegetable;
    }

    public int getExpirePercent() {
        return 100 * Period.between(LocalDate.now(), createDate).getDays()
                / Period.between(expireDate, createDate).getDays();

    }


    public boolean isCanReproduct() {
        return canReproduct;
    }

    public boolean isVegetable() {
        return isVegetable;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setVegetable(boolean vegetable) {
        isVegetable = vegetable;
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
        if (canReproduct != food.canReproduct) {
            return false;
        }
        if (isVegetable != food.isVegetable) {
            return false;
        }
        if (name != null ? !name.equals(food.name) : food.name != null) {
            return false;
        }
        if (expireDate != null ? !expireDate.equals(food.expireDate) : food.expireDate != null) {
            return false;
        }
        return createDate != null ? createDate.equals(food.createDate) : food.createDate == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (expireDate != null ? expireDate.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + discount;
        result = 31 * result + (canReproduct ? 1 : 0);
        result = 31 * result + (isVegetable ? 1 : 0);
        return result;
    }
}
