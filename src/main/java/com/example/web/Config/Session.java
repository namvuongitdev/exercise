package com.example.web.Config;
import com.example.web.service.IHoaDonService;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@WebListener
public class Session implements HttpSessionListener {

    @Autowired
    private IHoaDonService hoaDonService;


    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent se) {
//        Object value = se.getSession().getAttribute("HD789");
//        System.out.println("value : " + value.toString());
       // hoaDonService.updateHoaDonTrangThai(value.toString(), "huỷ hoá đơn");
        System.out.println("session time out!");
    }

}

