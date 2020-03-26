package com.epam.courses.warehouse.service;

import com.epam.courses.warehouse.dao.ProductRecordDtoDAO;
import com.epam.courses.warehouse.model.dto.ProductRecordDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ProductRecordDtoServiceImpl implements ProductRecordDtoService {

    private Logger LOGGER = LoggerFactory.getLogger(ProductRecordDtoServiceImpl.class);

    @Autowired
    private ProductRecordDtoDAO productRecordDtoDAO;

    @Override
    public List<ProductRecordDTO> getAll() {
        LOGGER.debug("ProductRecordDtoDAO:getAll");
        return productRecordDtoDAO.getAll();
    }

    @Override
    public List<ProductRecordDTO> getAllInTimeInterval(Date from, Date by) {
        LOGGER.debug("ProductRecordDtoDAO:getAllInTimeInterval from  ( "
                + from.toString() + " ) to ( " + by.toString() + " )");
        return productRecordDtoDAO.getAllInTimeInterval(from, by);
    }
}
