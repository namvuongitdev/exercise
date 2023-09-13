package com.example.web.controller;

import com.example.web.model.CTSPKhuyenMai;
import com.example.web.model.ChiTietSanPham;
import com.example.web.model.KhuyenMai;
import com.example.web.model.SanPham;
import com.example.web.service.IChiTietSanPhamService;
import com.example.web.service.IKhuyenMaiService;
import com.example.web.service.ISanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/khuyen-mai")
public class KhuyenMaiController {

    @Autowired
    private IChiTietSanPhamService service;

    @Autowired
    private IKhuyenMaiService khuyenMaiService;

    @GetMapping("/")
    public String hienThi(Model model, @RequestParam(defaultValue = "1") Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<ChiTietSanPham> list = service.findAll(pageable);
        model.addAttribute("listCtspSanPham", list);
        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);
        model.addAttribute("khuyenMai", new KhuyenMai());
        model.addAttribute("ctspKhuyenMai", new CTSPKhuyenMai());
        return "quanlykhuyenmai/khuyenmai/khuyen-mai";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("khuyenMai") KhuyenMai khuyenMai) {
        System.out.println(khuyenMai.getChiTietSanPhams());
         return khuyenMaiService.addKhuyenMai(khuyenMai);
    }
}
