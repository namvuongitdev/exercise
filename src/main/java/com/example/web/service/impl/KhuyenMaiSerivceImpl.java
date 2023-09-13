package com.example.web.service.impl;
import com.example.web.model.CTSPKhuyenMai;
import com.example.web.model.KhuyenMai;
import com.example.web.repository.ICTSPKhuyenMai;
import com.example.web.repository.IKhuyenMaiRepository;
import com.example.web.service.IKhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KhuyenMaiSerivceImpl implements IKhuyenMaiService {

    @Autowired
    private IKhuyenMaiRepository repository;

    @Autowired
    private ICTSPKhuyenMai ictspKhuyenMai;

    @Override
    public String addKhuyenMai(KhuyenMai khuyenMai ) {
       KhuyenMai km = repository.save(khuyenMai);
        khuyenMai.getChiTietSanPhams().forEach(o -> {
            CTSPKhuyenMai ctspKhuyenMai = CTSPKhuyenMai.builder()
                    .khuyenMai(km)
                    .chiTietSanPham(o)
                    .build();
            o.getCtspKhuyenMais().add(ctspKhuyenMai);
            ictspKhuyenMai.save(ctspKhuyenMai);
        });

        return null;
    }
}
