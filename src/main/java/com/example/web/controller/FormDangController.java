package com.example.web.controller;

import com.example.web.model.KieuDang;
import com.example.web.service.IFormDangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("formdang")
public class FormDangController {

    Page<KieuDang> list;
    @Autowired
    IFormDangService iFormDangService;

    @GetMapping("/hienthi")
    String getSideBar(@RequestParam(defaultValue = "1") Integer page, Model model) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 5);
        list = iFormDangService.findAll(pageable);
        model.addAttribute("list", list);
        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);
        return "quanLySanPham/formdang/formdang";
    }

    @GetMapping("/delete/{id}")
    String delete(@PathVariable(name = "id") String id) {
        iFormDangService.deleteById(UUID.fromString(id));
        return "redirect:/formdang/hienthi";
    }

    @PostMapping("/add")
    String add(@RequestParam(name = "ten") String ten,
               @RequestParam(name = "trangthai") String trangthai, Model model) {
        KieuDang formDang = new KieuDang(ten, Integer.parseInt(trangthai));
        KieuDang formDang1 = iFormDangService.save(formDang);
        //hiển thị
        return "redirect:/formdang/hienthi";
    }

    @PostMapping("/update")
    String update(@RequestParam(name = "id") String id,
                  @RequestParam(name = "ten") String ten,
                  @RequestParam(name = "trangthai") String trangthai
    ) {
        KieuDang formDang = iFormDangService.getOne(UUID.fromString(id));
        formDang.setTen(ten);
        formDang.setTrangThai(Integer.parseInt(trangthai));
        KieuDang formDang1 = iFormDangService.save(formDang);

        return "redirect:/formdang/hienthi";
    }


}
