import java.util.ArrayList;
import java.util.List;

enum DiscountType {
    PERCENTAGE,
    FIXED_AMOUNT
}

enum PaymentMethod {
    CREDIT_CARD,
    PAYPAL,
    BANK_TRANSFER,
    CASH
}

interface ICloneable<T> {
    T clone();
}

class Product implements ICloneable<Product> {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public Product clone() {
        return new Product(name, price, quantity);
    }
}

class Discount implements ICloneable<Discount> {
    private String description;
    private double amount;
    private DiscountType type;

    public Discount(String description, double amount, DiscountType type) {
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public DiscountType getType() {
        return type;
    }

    @Override
    public Discount clone() {
        return new Discount(description, amount, type);
    }
}



class Order implements ICloneable<Order> {
    private List<Product> products = new ArrayList<>();
    private double shippingCost;
    private List<Discount> discounts = new ArrayList<>();
    private PaymentMethod paymentMethod; // Добавлено поле для метода оплаты

    public void addProduct(Product product) {
        products.add(product);
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) { // Метод для установки метода оплаты
        this.paymentMethod = paymentMethod;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public PaymentMethod getPaymentMethod() { // Метод для получения метода оплаты
        return paymentMethod;
    }

    @Override
    public Order clone() {
        Order clonedOrder = new Order();
        for (Product product : products) {
            clonedOrder.addProduct(product.clone());
        }
        clonedOrder.setShippingCost(shippingCost);
        for (Discount discount : discounts) {
            clonedOrder.addDiscount(discount.clone());
        }
        clonedOrder.setPaymentMethod(paymentMethod); // Клонирование метода оплаты
        return clonedOrder;
    }

    public void displayOrder() {
        System.out.println("Order Summary:");
        for (Product product : products) {
            System.out.printf("Product: %s, Price: %.2f, Quantity: %d%n",
                    product.getName(), product.getPrice(), product.getQuantity());
        }
        System.out.printf("Shipping Cost: %.2f%n", shippingCost);
        System.out.printf("Payment Method: %s%n", paymentMethod); // Отображение метода оплаты
        for (Discount discount : discounts) {
            System.out.printf("Discount: %s, Amount: %.2f, Type: %s%n",
                    discount.getDescription(), discount.getAmount(), discount.getType());
        }
    }
}

public class Main_3 {
    public static void main(String[] args) {
        Order originalOrder = new Order();
        originalOrder.addProduct(new Product("Laptop", 999.99, 1));
        originalOrder.addProduct(new Product("Mouse", 49.99, 2));
        originalOrder.setShippingCost(25.00);
        originalOrder.setPaymentMethod(PaymentMethod.CREDIT_CARD); // Установка метода оплаты
        originalOrder.addDiscount(new Discount("Black Friday", 20.0, DiscountType.PERCENTAGE));
        Order clonedOrder = originalOrder.clone();
        clonedOrder.addProduct(new Product("Keyboard", 89.99, 1));
        clonedOrder.setShippingCost(15.00);
        clonedOrder.setPaymentMethod(PaymentMethod.PAYPAL); // Изменение метода оплаты
        clonedOrder.addDiscount(new Discount("Holiday Sale", 10.0, DiscountType.FIXED_AMOUNT));
        System.out.println("Original Order:");
        originalOrder.displayOrder();
        System.out.println("\nCloned Order:");
        clonedOrder.displayOrder();
    }
}


