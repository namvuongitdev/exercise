package com.example.web.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "danh_muc")
@Builder
@Getter
@Setter

public class DanhMuc {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "ten")
    @Nationalized
    private String ten;

    @Column(name = "trangthai")
    private Integer trangThai;

    @OneToMany(mappedBy = "danhMuc")
    private List<SanPham> sanPhams;
}
