package com.javaweb.converter;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.TransactionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TransactionConverter {


    private ModelMapper modelMapper;

    TransactionConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TransactionDTO toTransactionDTO(TransactionEntity entity) {
       return modelMapper.map(entity, TransactionDTO.class);
    }

    public TransactionEntity toTransactionEntity(TransactionDTO dto) {
        return modelMapper.map(dto, TransactionEntity.class);
    }
}
