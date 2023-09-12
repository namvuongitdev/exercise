package com.example.web.controller;
import com.example.web.model.ChiTietSanPham;
import com.example.web.model.SanPham;
import com.example.web.response.SanPhamFilter;
import com.example.web.service.DanhMucService;
import com.example.web.service.IChatLieuService;
import com.example.web.service.IChiTietSanPhamService;
import com.example.web.service.IFormDangService;
import com.example.web.service.IMauSacService;
import com.example.web.service.ISanPhamService;
import com.example.web.service.SizeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/san-pham")
public class SanPhamController {

    @Autowired
    private IFormDangService iFormDangService;

    @Autowired
    private ISanPhamService iSanPhamService;

    @Autowired
    private IChatLieuService iChatLieuService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private IMauSacService mauSacService;

    @Autowired
    private DanhMucService danhMucService;

    @Autowired
    private IChiTietSanPhamService chiTietSanPhamService;

    private Page<SanPham> list;


    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(defaultValue = "1") int page) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 5);
        list = iSanPhamService.findAll(pageable);
        model.addAttribute("listSanPham", list);
        model.addAttribute("listChatLieu", iChatLieuService.getAll());
        model.addAttribute("listFromDang", iFormDangService.getAll());
        model.addAttribute("listDanhMuc", danhMucService.getAll());
        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);
        return "quanLySanPham/sanpham/san-pham";
    }

    @GetMapping("/filter")
    public String filterSanPham(@RequestParam String danhMuc , @RequestParam String tenSanPham){
      System.out.println("danh muc" + danhMuc);
        SanPhamFilter filter = SanPhamFilter.builder()
                .danhMuc(danhMuc)
                .build();
        System.out.println("san pham : " + iSanPhamService.sanPhamFilter(filter));
        return null;
    }

    @GetMapping("/api-hien-thi/{page}")
    @ResponseBody
    public Page<SanPham> apiSanPham(@PathVariable Integer page , @RequestParam(required = false) String value) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page listSanPham = null;
        if(value.isEmpty()){
             listSanPham = iSanPhamService.findAll(pageable);
            return listSanPham;
        }else{
             listSanPham =  iSanPhamService.getAllByTenOrMa(value, page);
             return listSanPham;
        }
    }

    @GetMapping("/new")
    public String viewAddSanPham(Model model) {
        model.addAttribute("listChatLieu", iChatLieuService.getAll());
        model.addAttribute("listFromDang", iFormDangService.getAll());
        model.addAttribute("listDanhMuc", danhMucService.getAll());
        model.addAttribute("sanPham", new SanPham());
        return "quanLySanPham/sanpham/new-san-pham";
    }

    @PostMapping(value = "/add")
    public String addSanPham(@Valid @ModelAttribute("sanPham")  SanPham sanPham , BindingResult result , @RequestParam(required = false) String id) {
       if(result.hasErrors()){
            return "redirect:/san-pham/new";
       }else {
           Date date = java.util.Calendar.getInstance().getTime();
           if (!id.isEmpty()) {
               SanPham sp = iSanPhamService.getOne(UUID.fromString(id));
               iSanPhamService.save(sp);
           } else {
               Integer maSanPham = iSanPhamService.getAll().size() + 1;
               sanPham.setMa("SP" + maSanPham);
               sanPham.setNgayTao(date);
               iSanPhamService.save(sanPham);
           }
       }
        return "redirect:/san-pham/hien-thi/" + sanPham.getId();
    }

    @GetMapping(value = "/hien-thi/{id}")
    public String getSanPham(@PathVariable String id, Model model) {
        SanPham sanPham = iSanPhamService.getOne(UUID.fromString(id));
        model.addAttribute("listChatLieu", iChatLieuService.getAll());
        model.addAttribute("listFromDang", iFormDangService.getAll());
        model.addAttribute("listKichCo", sizeService.getAll());
        model.addAttribute("listMuaSac", mauSacService.getAll());
        model.addAttribute("listDanhMuc", danhMucService.getAll());
        model.addAttribute("listChiTietSanPhamBySP", chiTietSanPhamService.getChiTietSanPham(id));
        model.addAttribute("sanPham", new SanPham());
        model.addAttribute("chiTietSanPham", new ChiTietSanPham());
        model.addAttribute("sp", sanPham);
        return "quanLySanPham/sanpham/new-san-pham";
    }

    @GetMapping(value = "/add-anh-mac-dinh")
    public String addAnhMacDinhSanPham(@RequestParam String img, @RequestParam String idSP) {
        SanPham sanPham = iSanPhamService.getOne(UUID.fromString(idSP));
        sanPham.setImg(img);
        iSanPhamService.save(sanPham);
        return "redirect:/san-pham/hien-thi/" + sanPham.getId();
    }

}
