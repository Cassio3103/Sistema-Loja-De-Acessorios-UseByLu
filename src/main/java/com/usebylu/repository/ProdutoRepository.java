package com.usebylu.repository;

import com.usebylu.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Override
    boolean existsById(Long aLong);

    @Override
    Optional<Produto> findById(Long aLong);

    @Override
    List<Produto> findAll();
}
