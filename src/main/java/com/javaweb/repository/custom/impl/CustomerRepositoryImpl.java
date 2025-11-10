package com.javaweb.repository.custom.impl;


import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.MyUserDetail;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    public void joinTable(MyUserDetail userDetail, CustomerDTO customerDTO, StringBuilder sql) {
        if (customerDTO.getManagementStaff() != null && !customerDTO.getManagementStaff().isEmpty()) {
            sql.append(" INNER JOIN assignmentcustomer ac ON ac.customerid = c.id ");
        }

        Collection<GrantedAuthority> authorities = userDetail.getAuthorities();
        String authorityString = "";
        Iterator<GrantedAuthority> iterator = authorities.iterator();
        if (iterator.hasNext()) {
            authorityString = iterator.next().toString();
        }

        if (authorityString.equals("ROLE_STAFF")) {
            if (customerDTO.getManagementStaff() == null || customerDTO.getManagementStaff().isEmpty()) {
                sql.append(" INNER JOIN assignmentcustomer ac ON ac.customerid = c.id ");
            }
            sql.append(" INNER JOIN user u ON u.id = ac.staffid ");
        }
    }


    public void querySpecial(MyUserDetail userDetail, CustomerDTO customerDTO, StringBuilder sql) {
        if(customerDTO.getManagementStaff() != null && !customerDTO.getManagementStaff().isEmpty()){
            sql.append(" AND ac.staffid = " + customerDTO.getManagementStaff());
        }

        Collection<GrantedAuthority> authorities = userDetail.getAuthorities();
        String authorityString = "";
        Iterator<GrantedAuthority> iterator = authorities.iterator();
        if (iterator.hasNext()) {
            authorityString = iterator.next().toString();
        }

        if(authorityString.equals("ROLE_STAFF")){
            sql.append(" AND u.username LIKE '%" + userDetail.getUsername() + "%' ");
        }
    }

    public void queryNormal(CustomerDTO customerDTO, StringBuilder sql) {
        if(customerDTO.getFullName() != null && !customerDTO.getFullName().isEmpty()){
            sql.append(" AND c.fullname LIKE '%" + customerDTO.getFullName() + "%' ");
        }

        if(customerDTO.getEmail() != null && !customerDTO.getEmail().isEmpty()){
            sql.append(" AND c.email LIKE '%" + customerDTO.getEmail() + "%' ");
        }

        if(customerDTO.getCustomerPhone() != null && !customerDTO.getCustomerPhone().isEmpty()){
            sql.append(" AND c.phone LIKE '%" + customerDTO.getCustomerPhone() + "%' ");
        }
    }

    @Override
    public List<CustomerEntity> searchCustomer(MyUserDetail userDetail, CustomerDTO customerDTO, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT c.* FROM customer c ");

        joinTable(userDetail, customerDTO, sql);

        StringBuilder where = new StringBuilder(" WHERE 1 = 1 AND c.is_active = 1 ");
        querySpecial(userDetail, customerDTO, where);
        queryNormal(customerDTO, where);

        sql.append(where);

        sql.append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());

        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);

        return query.getResultList();
    }

    // java
    @Override
    public Integer totalSearchItems(MyUserDetail userDetail, CustomerDTO customerDTO) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(DISTINCT c.id) FROM customer c ");

        // Reuse the same join logic as searchCustomer
        joinTable(userDetail, customerDTO, sql);

        // Reuse the same where conditions
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 AND c.is_active = 1 ");
        querySpecial(userDetail, customerDTO, where);
        queryNormal(customerDTO, where);

        sql.append(where);

        Query query = entityManager.createNativeQuery(sql.toString());
        Object result = query.getSingleResult();
        if (result == null) return 0;
        if (result instanceof Number) return ((Number) result).intValue();

        try {
            return Integer.parseInt(result.toString());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

}
