package com.example.web.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SanPhamFilter {

    private String search;
    private String danhMuc;
    private String chatLieu;
    private String kieuDang;
}
