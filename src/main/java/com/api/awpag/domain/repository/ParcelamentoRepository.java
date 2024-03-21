package com.api.awpag.domain.repository;

import com.api.awpag.domain.model.Parcelamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelamentoRepository extends JpaRepository<Parcelamento, Long> {
}
