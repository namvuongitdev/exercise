package com.example.web.service.impl;
import com.example.web.model.DanhMuc;
import com.example.web.model.SanPham;
import com.example.web.repository.ISanPhamRepository;
import com.example.web.response.SanPhamFilter;
import com.example.web.service.ISanPhamService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SanPhamServiceImpl implements ISanPhamService {

    @Autowired
    private ISanPhamRepository iSanPhamRepository;

    @Override
    public Page<SanPham> findAll(Pageable pageable) {

        return iSanPhamRepository.findAllSanPham(pageable);
    }

    @Override
    public SanPham save(SanPham sanPham) {
        return iSanPhamRepository.save(sanPham);
    }

    @Override
    public SanPham getOne(UUID id) {
        return iSanPhamRepository.getOne(id);
    }

    @Override
    public Page<SanPham> getAllByTenOrMa(String value  , Integer page) {
        Pageable pageable =  PageRequest.of(page - 1 , 5);
        Page<SanPham> sanPhams = iSanPhamRepository.getAllSanPhamByTenOrMa("%" + value + "%" , pageable);
        return sanPhams;
    }

    @Override
    public List<SanPham> getAll() {
        return iSanPhamRepository.findAll();
    }

    @Override
    public List<SanPham> sanPhamFilter(SanPhamFilter filter) {
        return iSanPhamRepository.findAll(new Specification<SanPham>() {
            @Override
            public Predicate toPredicate(Root<SanPham> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                 List<Predicate> predicates = new ArrayList<>();
//                 if(!filter.getSearch().isEmpty() && filter.getSearch() != null){
//                      predicates.add(criteriaBuilder.equal(root.get("ten"),filter.getSearch()));
//                 }
                 if(!filter.getDanhMuc().isEmpty() && filter.getDanhMuc() != null){
                     DanhMuc danhMuc = DanhMuc.builder().id(filter.getDanhMuc()).build();
                      predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("danhMuc") , danhMuc)));
                 }
                 return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

}
