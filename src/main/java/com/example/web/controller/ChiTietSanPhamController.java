package com.example.web.controller;
import com.example.web.model.Anh;
import com.example.web.model.ChiTietSanPham;
import com.example.web.model.SanPham;
import com.example.web.response.ChiTietSanPhamResponse;
import com.example.web.service.IAnhService;
import com.example.web.service.IChiTietSanPhamService;
import com.example.web.service.ISanPhamService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/chi-tiet-san-pham")
public class ChiTietSanPhamController {

    @Autowired
    private IChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private ISanPhamService sanPhamService;

    @Autowired
    private IAnhService anhService;

    @PostMapping(value = "/add")
    public String addCTSP(@ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham
            , @RequestParam("id") String idSanPham, RedirectAttributes redirectAttributes) {

        SanPham sanPham = sanPhamService.getOne(UUID.fromString(idSanPham));
        chiTietSanPham.setSanPham(sanPham);
        List<ChiTietSanPham> listChiTietSanPham = chiTietSanPhamService.getChiTietSanPham(idSanPham);
        chiTietSanPhamService.save(chiTietSanPham);
        redirectAttributes.addFlashAttribute("listChiTietSanPhamBySP", listChiTietSanPham);
        return "redirect:/san-pham/hien-thi/" + idSanPham;
    }

    @PostMapping(value = "/add-anh")
    public String addAnhChiTietSanPham(@RequestParam("file") MultipartFile file, HttpServletRequest request, @RequestParam String idCTSP) throws IOException {
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(UUID.fromString(idCTSP));
        anhService.addAnhCtsp(file, request, chiTietSanPham);
        return "redirect:/chi-tiet-san-pham/anh/" + chiTietSanPham.getId() + "?idSP=" + chiTietSanPham.getSanPham().getId();
    }

    @PostMapping(value = "/update-chi-tiet-san-pham")
    public String updateCTSP(@ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham, @RequestParam String idCTSP, @RequestParam String idSP) {
        chiTietSanPham.setId(UUID.fromString(idCTSP));
        chiTietSanPham.setSanPham(sanPhamService.getOne(UUID.fromString(idSP)));
        chiTietSanPhamService.save(chiTietSanPham);
        return "redirect:/san-pham/hien-thi/" + chiTietSanPham.getSanPham().getId();
    }

    @GetMapping(value = "/anh/{id}")
    public String getAnhByChiTietSanPham_id(RedirectAttributes redirectAttributes, @PathVariable("id") String idCTSP, @RequestParam String idSP) {
        List<Anh> anhs = anhService.getAnh(idCTSP);
        redirectAttributes.addFlashAttribute("listAnhChiTietSanPham_id", anhs);
        return "redirect:/san-pham/hien-thi/" + idSP;
    }

    @GetMapping(value = "/remove-anh")
    public String removeAnhById(@RequestParam String idAnh, @RequestParam String idCTSP, @RequestParam String idSP) {
        anhService.reomveAnhById(idAnh);
        return "redirect:/chi-tiet-san-pham/anh/" + idCTSP + "?idSP=" + idSP;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public List<ChiTietSanPhamResponse> getChiTietSanPham(@PathVariable("id") String id) {
        List<ChiTietSanPhamResponse> chiTietSanPhams = chiTietSanPhamService.getCTSP(id);
        return chiTietSanPhams;
    }

    @GetMapping(value = "/so-luong")
    @ResponseBody
    public ChiTietSanPhamResponse getByMuaSacAnhKichCoAndSanPham(@RequestParam(value = "mauSac", required = false) String idMS, @RequestParam(value = "kichCo", required = false) String idKC, @RequestParam(value = "sanPham", required = false) String idSP) {
        ChiTietSanPhamResponse ctspResponse = chiTietSanPhamService.getByMauSacAndKichCoAndSanPham(idMS, idKC, idSP);
        return ctspResponse;
    }

}
