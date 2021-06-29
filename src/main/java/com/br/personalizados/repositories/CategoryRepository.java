package com.br.personalizados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.personalizados.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
