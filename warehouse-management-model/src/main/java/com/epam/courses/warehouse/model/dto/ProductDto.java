package com.epam.courses.warehouse.model.dto;

/**
 * POJO Product with summary count product in warehouse.
 */
public final class ProductDto {

    /**
     * Product id.
     */
    private Integer productId;

    /**
     * Product name.
     */
    private String productName;

    /**
     * Summary product count.
     */
    private Integer productSumCount;

    /**
     * Constructor without arguments.
     */
    public ProductDto() {
    }

    /**
     * Constructor with product name.
     * @param prodName product name.
     */
    public ProductDto(final String prodName) {
        this.productName = prodName;
    }

    /**
     * Return <code>Integer</code> representation of this product id.
     * @return productId product id.
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Set the Product identifier.
     * @param prodId product id.
     * @return this dto product.
     */
    public ProductDto setProductId(final Integer prodId) {
        this.productId = prodId;
        return this;
    }

    /**
     * Return <code>String</code> representation of this product name.
     * @return productName product name.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Set the product name.
     * @param prodName product name.
     * @return this dto product.
     */
    public ProductDto setProductName(final String prodName) {
        this.productName = prodName;
        return this;
    }

    /**
     * Return <code>Integer</code> representation of summary count product.
     * @return productSumCount product summary count.
     */
    public Integer getProductSumCount() {
        return productSumCount;
    }

    /**
     * Set the Product quantity.
     * @param quantity product quantity.
     * @return this dto product.
     */
    public ProductDto setProductSumCount(final Integer quantity) {
        this.productSumCount = quantity;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ProductDto{"
                + "productId=" + productId
                + ", productName='" + productName + '\''
                + ", productSumCount=" + productSumCount
                + '}';
    }
}
