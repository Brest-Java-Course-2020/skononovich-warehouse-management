package com.epam.courses.warehouse.model.dto;

import com.epam.courses.warehouse.model.DealTypes;

import java.sql.Date;

/**
 * ProductRecord with String field productName.
 */
public final class ProductRecordDTO {

        /**
         * Record id.
         */
        private Integer recordId;

        /**
         * Product name.
         */
        private String productName;

        /**
         * Quantity of product.
         */
        private Integer quantityOfProduct;

        /**
         * Deal date.
         */
        private Date dealDate;

        /**
         * Deal type.
         */
        private DealTypes dealType;

        /**
         * Return <code>Integer</code> representation of this product record id.
         * @return recordId product record id.
         */
        public Integer getRecordId() {
            return recordId;
        }

        /**
         * Set the record id.
         * @param recId record id.
         * @return this ProductRecordDTO.
         */
        public ProductRecordDTO setRecordId(final Integer recId) {
            this.recordId = recId;
            return this;
        }

        /**
         * Return <code>String</code> representation of this product name.
         * @return productName product Name.
         */
        public String getProductName() {
            return productName;
        }

        /**
         * Set the product name.
         * @param prodName product name.
         * @return this ProductRecordDTO.
         */
        public ProductRecordDTO setProductName(final String prodName) {
            this.productName = prodName;
            return this;
        }

        /**
         * Return <code>Integer</code> representation of product quantity.
         * @return quantityOfProduct quantity of product.
         */
        public Integer getQuantityOfProduct() {
            return quantityOfProduct;
        }

        /**
         * Set the quantity of product.
         * @param quantity quantity of product.
         * @return this ProductRecordDTO.
         */
        public ProductRecordDTO setQuantityOfProduct(final Integer quantity) {
            this.quantityOfProduct = quantity;
            return this;
        }

        /**
         * Return <code>Date</code> representation of deal date.
         * @return dealDate deal date.
         */
        public Date getDealDate() {
            if (this.dealDate == null) {
                return null;
            }
            return new Date(dealDate.getTime());
        }

        /**
         * Set the deal date.
         * @param date deal date.
         * @return this ProductRecordDTO.
         */
        public ProductRecordDTO setDealDate(final Date date) {
            if (date == null) {
                this.dealDate = null;
            } else {
                this.dealDate = new Date(date.getTime());
            }
            return this;
        }

        /**
         * Return <code>DealType</code> representation of deal type.
         * @return dealType deal type.
         */
        public DealTypes getDealType() {
            return dealType;
        }

        /**
         * Set the deal type.
         * @param pDealType deal type.
         * @return this ProductRecord.
         */
        public ProductRecordDTO setDealType(final DealTypes pDealType) {
            this.dealType = pDealType;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "ProductRecord{"
                    + "recordId=" + recordId
                    + ", productName=" + productName
                    + ", quantityOfProduct=" + quantityOfProduct
                    + ", dealDate=" + dealDate
                    + ", dealType=" + dealType
                    + '}';
        }

}
