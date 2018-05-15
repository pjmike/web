package com.pjmike.project.repository;

import com.pjmike.project.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author pjmike
 * @create 2018-05-10 21:15
 */
public interface CantactRepository extends JpaRepository<Contact,Integer> {
    Contact findByContactId(Integer id);
}
