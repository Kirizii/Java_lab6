import java.util.Objects;

/**
 * Абстрактний базовий клас для всіх типів кави.
 */
public abstract class Coffee {

    protected String name;
    protected double weight;
    protected double price;
    protected int quality;

    public Coffee(String name, double weight, double price, int quality) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.quality = quality;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public int getQuality() {
        return quality;
    }

    /**
     * Співвідношення ціни до ваги.
     */
    public double getPriceToWeightRatio() {
        return price / weight;
    }

    @Override
    public String toString() {
        return name +
                " | вага=" + weight +
                " кг | ціна=" + price +
                " грн | якість=" + quality;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Coffee)) {
            return false;
        }

        Coffee other = (Coffee) obj;

        return Double.compare(weight, other.weight) == 0
                && Double.compare(price, other.price) == 0
                && quality == other.quality
                && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, weight, price, quality);
    }
}


