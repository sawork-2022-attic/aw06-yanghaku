package com.example.batch.service;

import com.example.batch.dao.ProductMapper;
import com.example.batch.model.Product;
import com.example.batch.model.ProductWebPos;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductWriter implements ItemWriter<Product>, StepExecutionListener {
	private ProductMapper productMapper;

	@Autowired
	public void setProductMapper(ProductMapper p) {
		this.productMapper = p;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {

	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

	@Override
	public void write(List<? extends Product> list) throws Exception {
		for (Product p : list) {
			ProductWebPos i = new ProductWebPos();
			if (p.getAsin() == null || p.getTitle() == null || p.getImageURLHighRes() == null
					|| p.getImageURLHighRes().size() < 1) {
				continue;
			}

			i.setId(p.getAsin());
			if (p.getTitle().length() > 1024) {
				i.setName(p.getTitle().substring(0, 1024));
			} else {
				i.setName(p.getTitle());
			}
			i.setImage(p.getImageURLHighRes().get(0));
			i.setPrice(120);
			try {
				productMapper.insert(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
