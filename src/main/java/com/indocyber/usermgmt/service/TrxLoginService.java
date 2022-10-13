package com.indocyber.usermgmt.service;

import com.indocyber.usermgmt.dto.RequestChangepwdDTO;
import com.indocyber.usermgmt.dto.RequestDTO;
import com.indocyber.usermgmt.dto.ResponseLoginDTO;
import com.indocyber.usermgmt.dto.ResponseLogoutDTO;
import com.indocyber.usermgmt.dto.login.CheckAuthDTO;
import com.indocyber.usermgmt.dto.login.ForgotPasswordResponseDTO;
import com.indocyber.usermgmt.dto.login.ProcessForgotPwdDTO;
import com.indocyber.usermgmt.dto.login.ResponseMenuUserDTO;
import com.indocyber.usermgmt.entity.TrxLogin;

import java.time.LocalDateTime;
import java.util.List;

public interface TrxLoginService {
    List<TrxLogin> findAll();
    void deleteById(Long id);
    TrxLogin findById(Long id);
    void save(TrxLogin trxLogin);
    void delete(TrxLogin trxLogin);

    String findNameById(String userId);

    void saveLogin(RequestDTO dto, String token);

    LocalDateTime getLogoutDate(String userId);

    LocalDateTime getLoginDate(String userId);

    void saveUpdateLogout(String username, String token);

    void updateChangepwd(RequestChangepwdDTO dto, String user);

    ForgotPasswordResponseDTO getForgotPasswordDTO(RequestDTO dto);

    ResponseLoginDTO processForgotPwd(ProcessForgotPwdDTO dto);

    ResponseMenuUserDTO getMenuUserDTO(String userId);

    ResponseLogoutDTO checkAuth(String userId, CheckAuthDTO dto);

    void saveUpdateForceLogout(String userId, String password);
}
