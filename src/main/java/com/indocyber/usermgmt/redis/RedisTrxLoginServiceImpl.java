package com.indocyber.usermgmt.redis;

import com.indocyber.usermgmt.entity.TrxLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisTrxLoginServiceImpl implements RedisTrxLoginService {

    @Autowired
    RedisRepository redisRepository;

    @Override
    public void add(TrxLogin trxLogin) {
        redisRepository.put("TRX_LOGIN",trxLogin.getJwtToken(), trxLogin);
    }

    @Override
    public Object getByToken(String token) {
        return redisRepository.get("TRX_LOGIN", token);
    }

    @Override
    public void deleteByToken(String token) {
        redisRepository.delete("TRX_LOGIN", token);
    }

    @Override
    public void deleteAll() {
        redisRepository.deleteAll("TRX_LOGIN");
    }
}
