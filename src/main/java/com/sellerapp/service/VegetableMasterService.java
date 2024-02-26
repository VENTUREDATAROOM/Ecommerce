package com.sellerapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.sellerapp.entity.VegetableMasterEntity;


import com.sellerapp.model.VegetableMasterDTO;

import org.springframework.stereotype.Service;

import com.sellerapp.repository.VegetableMasterRepo;
@Service
public class VegetableMasterService {
	
	@Autowired
	private VegetableMasterRepo vegetableMasterRepo;
	
	@Autowired
	ModelMapper mapper;
	
	public List<VegetableMasterEntity> getAllVegetable()
	{
		return vegetableMasterRepo.findAll();
	}
    public String saveVegetable(VegetableMasterDTO vegetableMasterDTO)
    {
    	
    	VegetableMasterEntity vegetableMasterEntity=mapper.map(vegetableMasterDTO,VegetableMasterEntity.class);
    	vegetableMasterRepo.save(vegetableMasterEntity);
    	return "Success";
    }
}
