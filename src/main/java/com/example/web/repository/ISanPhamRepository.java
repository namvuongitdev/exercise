package com.example.web.repository;
import com.example.web.model.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.UUID;

public interface ISanPhamRepository extends JpaRepository<SanPham, UUID>  , JpaSpecificationExecutor<SanPham> {

    @Query(value = "Select * from san_pham where id=?1", nativeQuery = true)
    SanPham getOne(UUID id);

    @Query(value = "select  sanPham.id , sanPham.ten , sanPham.giaBan , sanPham.img ,sanPham.trangThai  , sanPham.ngayTao ,sanPham.ma from SanPham sanPham where sanPham.ten like ?1 or sanPham.ma like ?1 ")
    Page<SanPham> getAllSanPhamByTenOrMa(String value, Pageable pageable);

    @Override
    Page<SanPham> findAll(Pageable pageable);
}
