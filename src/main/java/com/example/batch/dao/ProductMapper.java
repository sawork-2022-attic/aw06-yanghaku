package com.example.batch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.example.batch.model.ProductWebPos;

@Mapper
public interface ProductMapper {
	int deleteByPrimaryKey(String id);

	int insert(ProductWebPos row);

	ProductWebPos selectByPrimaryKey(String id);

	List<ProductWebPos> selectAll();

	int updateByPrimaryKey(ProductWebPos row);
}
