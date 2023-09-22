package com.yc.biz;

import com.yc.bean.Resorder;
import com.yc.bean.ResorderItem;
import com.yc.bean.Resuser;
import com.yc.dao.ResorderDao;
import com.yc.dao.ResorderitemDao;
import com.yc.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional( propagation = Propagation.REQUIRED,
    isolation = Isolation.DEFAULT,timeout = 2000,
    readOnly = false, rollbackFor = RuntimeException.class
)

public class ResorderBizImpl implements ResorderBiz{
    @Autowired
    private ResorderDao resorderDao;
    @Autowired
    private ResorderitemDao resorderitemDao;
    @Override
    public int order(Resorder resorder, Set<CartItem> cartItems, Resuser resuser) {

        resorder.setUserid(resuser.getUserid());
        this.resorderDao.insert(resorder);

        for (CartItem ci : cartItems) {
            ResorderItem roi = new ResorderItem();
            roi.setRoid(resorder.getRoid());
            roi.setFid(ci.getFood().getFid());
            roi.setDealprice(ci.getFood().getRealprice());
            roi.setNum(ci.getNum());
            this.resorderitemDao.insert(roi);
        }
        return 1;
    }
}
