package com.deraah.demo_api.repository;


import com.deraah.demo_api.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact,Long> {


    @Query(value = "select * from contacts where name like :search  or phone like :seaarch and active=1",nativeQuery = true,countQuery = "select * from contacts where name like :search  or phone like :seaarch")
    Page<Contact> findAllWithSearch(Pageable pageable, String search);


    @Query(value = "select * from contacts where active=1",nativeQuery = true,countQuery = "select * from contact  where active=1")
    Page<Contact> findAllActive(Pageable pageable);


    @Query(value = "select * from contacts where id=:id limit 1",nativeQuery = true)
    Optional<Contact> findOneByIdActive(Long id);


}
