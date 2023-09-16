package com.example.web.controller;
import com.example.web.model.KhuyenMai;
import com.example.web.response.FilterKhuyenMai;
import com.example.web.service.IChiTietSanPhamService;
import com.example.web.service.IKhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin/khuyen-mai")
public class KhuyenMaiController {

    @Autowired
    private IChiTietSanPhamService service;

    @Autowired
    private IKhuyenMaiService khuyenMaiService;

    @GetMapping("/")
    public String hienThi(Model model, @RequestParam(defaultValue = "1") Integer page , @ModelAttribute("filterKhuyenMai")FilterKhuyenMai filter) {
        Page<KhuyenMai> khuyenMais = khuyenMaiService.getAll(page);
        Integer stt = page != 1 ? page * 10 - 9 : page;
        model.addAttribute("khuyenMais" , khuyenMais);
        model.addAttribute("khuyenMai" , new KhuyenMai());
        model.addAttribute("pageNo", page);
        model.addAttribute("page", stt);
        return "quanlykhuyenmai/khuyenmai/khuyen-mai";
    }

    @GetMapping("/new-create")
    public String newCreate(Model model){
//        List<ChiTietSanPham> list = service.findAll();
//        model.addAttribute("listCtspSanPham", list);
//        model.addAttribute("khuyenMai", new KhuyenMai());
        return "quanlykhuyenmai/khuyenmai/new-khuyen-mai";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("khuyenMai") KhuyenMai khuyenMai) {
        String url = khuyenMaiService.addKhuyenMai(khuyenMai);
         return url;
    }
}
