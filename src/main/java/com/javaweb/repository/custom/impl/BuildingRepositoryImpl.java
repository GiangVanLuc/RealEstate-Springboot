package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            sql.append(" INNER JOIN assignmentbuilding ON assignmentbuilding.buildingid = b.id ");
        }
    }

    public static void queryNomal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        try{
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true); // enable Access
                String fieldName = item.getName();
                if(!fieldName.equals("staffId") && !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")) {
                    Object value = item.get(buildingSearchBuilder);
                    if(value != null && !value.toString().isEmpty()) {
                        if(value instanceof Long || value instanceof Integer){
                            where.append(" AND b." + fieldName + " = " + value);
                        }
                        else if(value instanceof String){
                            where.append(" AND b." + fieldName + " LIKE '%" + value + "%'");
                        }
                    }

                }

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            where.append(" AND assignmentbuilding.staffid = " + staffId);
        }
        Long rentAreaTo = buildingSearchBuilder.getAreaTo();
        Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
        if (rentAreaTo != null || rentAreaFrom != null) {
            where.append(" AND EXISTS (SELECT * FROM rentarea r where r.buildingid =  b.id ");
            if(rentAreaFrom != null) {
                where.append(" AND r.value >= " + rentAreaFrom);
            }
            if(rentAreaTo != null) {
                where.append(" AND r.value <= " + rentAreaTo);
            }
            where.append(") ");
        }

        Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        if(rentPriceTo != null|| rentPriceFrom != null) {
            if(rentPriceFrom != null) {
                where.append(" AND b.rentprice >=" + rentPriceFrom);
            }
            if(rentPriceTo != null) {
                where.append(" AND b.rentprice <=" + rentPriceTo);
            }


        }
        // java 8
        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if(typeCode != null && typeCode.size() != 0) {
            where.append(" AND(");
            String sql = typeCode.stream().map(it -> "b.type Like " + "'%" + it + "%'").collect(Collectors.joining(" OR "));
            where.append(sql);
            where.append(" ) ");
        }
    }


    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable) {
        // Sql Native
        StringBuilder sql = new StringBuilder("SELECT b.* FROM building b");
        joinTable(buildingSearchBuilder, sql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1");
        queryNomal(buildingSearchBuilder, where);
        querySpecial(buildingSearchBuilder, where);
        where.append(" GROUP BY b.id");
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        if (pageable != null) {
            query.setFirstResult((int) pageable.getOffset()); // Tương đương OFFSET
            query.setMaxResults(pageable.getPageSize());    // Tương đương LIMIT
        }
        return query.getResultList();

    }

    @Override
    public int countTotalItems(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(DISTINCT b.id) FROM building b");
        joinTable(buildingSearchBuilder, sql);
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
        queryNomal(buildingSearchBuilder, where);
        querySpecial(buildingSearchBuilder, where);
        sql.append(where);

        Query query = entityManager.createNativeQuery(sql.toString());
        Object result = query.getSingleResult();
        return ((Number) result).intValue();
    }


    public void splitPage(Pageable pageable, StringBuilder where) {
        where.append(" LIMIT ").append(pageable.getPageSize()).append("\n").append("OFFSET ").append(pageable.getOffset());
    }
}
