package com.sellerapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.sellerapp.entity.ProfileEntity;
public interface  ProfileRepo extends JpaRepository<ProfileEntity,Long>
{

	ProfileEntity findByUserCode(String userCode);

}
