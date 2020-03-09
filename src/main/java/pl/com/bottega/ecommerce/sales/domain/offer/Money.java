package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class Money
{
    private BigDecimal denomination;
    private String currency;

    public Money(BigDecimal denomination)
    {
        this.currency = "EUR";
        this.denomination = denomination;
    }

    public Money(BigDecimal denomination, String currency)
    {
        this.denomination = denomination;
        this.currency = currency;
    }

    public String getCurrency()
    {
        return currency;
    }

    public Money multiply(Money multiplier)
    {
        return new Money(this.denomination.multiply(multiplier.denomination), this.currency);
    }

    public Money subtract(Money other)
    {
        return new Money(this.denomination.subtract(other.denomination), this.currency);
    }

    public Money add(Money other)
    {
        return new Money(this.denomination.add(other.denomination), this.currency);
    }

    public int compareTo(Money other)
    {
        return this.denomination.compareTo(other.denomination);
    }


}
