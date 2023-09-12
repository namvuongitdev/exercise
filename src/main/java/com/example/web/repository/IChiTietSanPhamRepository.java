package com.example.web.repository;
import com.example.web.model.ChiTietSanPham;
import com.example.web.model.MauSac;
import com.example.web.model.SanPham;
import com.example.web.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID> {

    @Query(value = "Select * from chi_tiet_san_pham where id=?1", nativeQuery = true)
    ChiTietSanPham getOne(UUID id);

    List<ChiTietSanPham> findBySanPham_Id(UUID id);

    @Query(value = "select ctsp.size from ChiTietSanPham ctsp where ctsp.sanPham.id = ?1")
    List<Size> getSize(UUID idSP);

    @Query(value = "select ctsp.mauSac from ChiTietSanPham ctsp where ctsp.sanPham.id = ?1")
    List<MauSac> getMauSac(UUID idSP);

    @Query(value = "select  ctsp.sanPham from ChiTietSanPham ctsp where ctsp.sanPham.id = ?1")
    SanPham getSanPham(UUID ID);

    @Query(value = "select * from ChiTietSanPham ctsp where ctsp.idmausac=?1 and ctsp.idsize = ?2 and ctsp.idsanpham = ?3" , nativeQuery = true)
    Optional<ChiTietSanPham> getChiTietSanPhamByMauSac_IdAndSize_IdAndSanPham_Id(String mauSac_Id, String size, String sanPham_Id);
}
