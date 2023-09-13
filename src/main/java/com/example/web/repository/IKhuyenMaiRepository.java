package com.example.web.repository;
import com.example.web.model.KhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface IKhuyenMaiRepository extends JpaRepository<KhuyenMai , UUID> {

    @Override
    <S extends KhuyenMai> S save(S entity);
}
