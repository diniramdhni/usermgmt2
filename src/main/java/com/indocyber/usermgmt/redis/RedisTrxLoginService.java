package com.indocyber.usermgmt.redis;

import com.indocyber.usermgmt.entity.TrxLogin;

public interface RedisTrxLoginService {
    public void add(TrxLogin trxLogin);
    public Object getByToken(String token);
    public void deleteByToken(String token);
    public void deleteAll();

}
