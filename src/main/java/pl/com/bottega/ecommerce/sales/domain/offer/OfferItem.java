/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class OfferItem {

    // product
    private Product product;

    private int quantity;

    private Money totalCost;

    // discount
    private String discountCause;

    private Money discount;

    public OfferItem(String productId, Money productPrice, String productName, Date productSnapshotDate, String productType,
            int quantity) {
        this(productId, productPrice, productName, productSnapshotDate, productType, quantity, null, null);
    }

    public OfferItem(String productId, Money productPrice, String productName, Date productSnapshotDate, String productType,
            int quantity, Money discount, String discountCause) {
        this.product = new Product(productId, productPrice, productName, productSnapshotDate, productType);

        this.quantity = quantity;
        this.discount = discount;
        this.discountCause = discountCause;

        Money discountValue = new Money(new BigDecimal(0));
        if (discount != null) {
            discountValue = discountValue.add(discount);
        }

        this.totalCost = productPrice.multiply(new Money(new BigDecimal(quantity)))
                                     .subtract(discountValue);
    }

    public String getProductId() {
        return product.getProductId();
    }

    public Money getProductPrice() {
        return product.getProductPrice();
    }

    public String getProductName() {
        return product.getProductName();
    }

    public Date getProductSnapshotDate() {
        return product.getProductSnapshotDate();
    }

    public String getProductType() {
        return product.getProductType();
    }

    public Money getTotalCost() {
        return totalCost;
    }

    public Money getDiscount() {
        return discount;
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(discount, discountCause, product, quantity, totalCost);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OfferItem other = (OfferItem) obj;
        return Objects.equals(discount, other.discount)
               && Objects.equals(discountCause, other.discountCause)
               && Objects.equals(product, other.product)
               && quantity == other.quantity
               && Objects.equals(totalCost, other.totalCost);
    }

    /**
     *
     * @param other
     * @param delta
     *            acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {

        if(!product.sameAs(other.product))
            return false;

        if (quantity != other.quantity) {
            return false;
        }

        Money max;
        Money min;
        if (totalCost.compareTo(other.totalCost) > 0) {
            max = totalCost;
            min = other.totalCost;
        } else {
            max = other.totalCost;
            min = totalCost;
        }

        Money difference = max.subtract(min);
        Money acceptableDelta = max.multiply(new Money(BigDecimal.valueOf(delta / 100)));

        return acceptableDelta.compareTo(difference) > 0;
    }

}
