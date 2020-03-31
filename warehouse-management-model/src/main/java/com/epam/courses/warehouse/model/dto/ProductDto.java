package com.epam.courses.warehouse.model.dto;

/**
 * POJO Product with summary count product in warehouse.
 */
public class ProductDto {

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
     * @param productName product name.
     */
    public ProductDto(String productName) {
        this.productName = productName;
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
     * @param productId product id.
     * @return this dto product.
     */
    public ProductDto setProductId(Integer productId) {
        this.productId = productId;
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
     * @param productName product name.
     * @return this dto product.
     */
    public ProductDto setProductName(String productName) {
        this.productName = productName;
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
     * @param sumCount product quantity.
     * @return this dto product.
     */
    public ProductDto setProductSumCount(Integer sumCount){
        this.productSumCount = sumCount;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ProductDto{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productSumCount=" + productSumCount +
                '}';
    }
}
