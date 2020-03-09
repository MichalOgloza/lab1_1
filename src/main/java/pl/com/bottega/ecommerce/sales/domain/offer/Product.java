package pl.com.bottega.ecommerce.sales.domain.offer;

import java.util.Date;
import java.util.Objects;

public class Product
{
    private String productId;

    private Money productPrice;

    private String productName;

    private Date productSnapshotDate;

    private String productType;

    public Product(String productId, Money productPrice, String productName, Date productSnapshotDate, String productType)
    {
        this.productId = productId;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productSnapshotDate = productSnapshotDate;
        this.productType = productType;
    }

    public String getProductId() {
        return productId;
    }

    public Money getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public Date getProductSnapshotDate() {
        return productSnapshotDate;
    }

    public String getProductType() {
        return productType;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) &&
                Objects.equals(productPrice, product.productPrice) &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(productSnapshotDate, product.productSnapshotDate) &&
                Objects.equals(productType, product.productType);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(productId, productPrice, productName, productSnapshotDate, productType);
    }

    public boolean sameAs(Product other) {
        if (productPrice == null) {
            if (other.productPrice != null) {
                return false;
            }
        } else if (!productPrice.equals(other.productPrice)) {
            return false;
        }
        if (productName == null) {
            if (other.productName != null) {
                return false;
            }
        } else if (!productName.equals(other.productName)) {
            return false;
        }

        if (productId == null) {
            if (other.productId != null) {
                return false;
            }
        } else if (!productId.equals(other.productId)) {
            return false;
        }
        if (productType == null) {
            if (other.productType != null) {
                return false;
            }
        } else if (!productType.equals(other.productType)) {
            return false;
        }
        return true;
    }
}
