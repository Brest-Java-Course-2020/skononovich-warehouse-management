package com.epam.courses.warehouse.model.dto;

import com.epam.courses.warehouse.model.DealTypes;

import java.sql.Date;

/**
 * POJO ProductRecord with String field productName.
 */
public class ProductRecordDTO {

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
         * Deal type
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
         * @param recordId record id.
         * @return this ProductRecordDTO.
         */
        public ProductRecordDTO setRecordId(Integer recordId) {
            this.recordId = recordId;
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
         * @param productName product name.
         * @return this ProductRecordDTO.
         */
        public ProductRecordDTO setProductName(String productName) {
            this.productName = productName;
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
         * @param quantityOfProduct quantity of product.
         * @return this ProductRecordDTO.
         */
        public ProductRecordDTO setQuantityOfProduct(Integer quantityOfProduct) {
            this.quantityOfProduct = quantityOfProduct;
            return this;
        }

        /**
         * Return <code>Date</code> representation of deal date.
         * @return dealDate deal date.
         */
        public Date getDealDate() {
            if(this.dealDate == null){
                return null;
            }
            return new Date(dealDate.getTime());
        }

        /**
         * Set the deal date.
         * @param dealDate deal date.
         * @return this ProductRecordDTO.
         */
        public ProductRecordDTO setDealDate(Date dealDate) {
            if(dealDate == null){
                this.dealDate = null;
                return this;
            } else {
                this.dealDate = new Date(dealDate.getTime());
                return this;
            }
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
         * @param dealType deal type.
         * @return this ProductRecord.
         */
        public ProductRecordDTO setDealType(DealTypes dealType) {
            this.dealType = dealType;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "ProductRecord{" +
                    "recordId=" + recordId +
                    ", productName=" + productName +
                    ", quantityOfProduct=" + quantityOfProduct +
                    ", dealDate=" + dealDate +
                    ", dealType=" + dealType +
                    '}';
        }

}
