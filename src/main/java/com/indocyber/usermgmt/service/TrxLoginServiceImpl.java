package com.indocyber.usermgmt.service;

import com.indocyber.usermgmt.ApplicationUserDetails;
import com.indocyber.usermgmt.PasswordConfig;
import com.indocyber.usermgmt.dto.RequestChangepwdDTO;
import com.indocyber.usermgmt.dto.RequestDTO;
import com.indocyber.usermgmt.dto.ResponseLoginDTO;
import com.indocyber.usermgmt.dto.ResponseLogoutDTO;
import com.indocyber.usermgmt.dto.login.CheckAuthDTO;
import com.indocyber.usermgmt.dto.login.ForgotPasswordResponseDTO;
import com.indocyber.usermgmt.dto.login.ProcessForgotPwdDTO;
import com.indocyber.usermgmt.dto.login.ResponseMenuUserDTO;
import com.indocyber.usermgmt.dto.rolemenu.MenuActionDTO;
import com.indocyber.usermgmt.entity.*;
import com.indocyber.usermgmt.redis.RedisTrxLoginService;
import com.indocyber.usermgmt.repository.*;
import com.indocyber.usermgmt.exception.LoginException;
import com.indocyber.usermgmt.exception.NotFoundException;
import com.indocyber.usermgmt.utility.JwtToken;
import com.indocyber.usermgmt.utility.RoleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrxLoginServiceImpl implements TrxLoginService, UserDetailsService {

    @Autowired
    private TrxLoginRepository trxLoginRepository;

    @Autowired
    private MstUserRepository mstUserRepository;

    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    private TrxForgotPwdRepository trxForgotPwdRepository;

    @Autowired
    private MstGlobalSettingRepository mstGlobalSettingRepository;

    @Autowired
    private MstRoleMenuRepository mstRoleMenuRepository;

    @Autowired
    private MstUserRoleRepository mstUserRoleRepository;

    @Autowired
    private MstMenuRepository mstMenuRepository;

    @Autowired
    private RedisTrxLoginService redisTrxLoginService;

    @Autowired
    private JwtToken jwtToken;

    @Override
    public List<TrxLogin> findAll() {
        return trxLoginRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        trxLoginRepository.deleteById(id);
    }

    @Override
    public TrxLogin findById(Long id) {
        Optional<TrxLogin> optionalTrxLogin = trxLoginRepository.findById(id);
        return optionalTrxLogin.orElse(null);
    }

    @Override
    public void save(TrxLogin trxLogin) {
        trxLoginRepository.save(trxLogin);
    }

    @Override
    public void delete(TrxLogin trxLogin) {
        trxLoginRepository.delete(trxLogin);
    }

    @Override
    public String findNameById(String userId) {

        return mstUserRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User tidak ketemu")
        ).getUserFullname();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MstUser mstUser = mstUserRepository.findById(username).orElseThrow(() -> new LoginException("User tidak ketemu"));

        return new ApplicationUserDetails(mstUser);
    }

    @Override
    public void saveLogin(RequestDTO dto, String token) {

        TrxLogin trxLogin = new TrxLogin(
                dto.getUserId(),
                LocalDateTime.now(),
                token,
                "test_jwt_refresh", // parameter value untuk pemakaian Refresh Token nantinya
                null
        );

        //mengecek apakah ada cache di redis
        if (redisTrxLoginService.getByToken(token) == null){

            //membuat cache/storage data login
            redisTrxLoginService.add(trxLogin);
        }

        LocalDateTime logoutDate = getLogoutDate(dto.getUserId());
        LocalDateTime loginDate = getLoginDate(dto.getUserId());

        if (logoutDate == null){
            throw new LoginException("Anda sudah pernah login pada " + loginDate);
        }

        //menyimpan data login ke database
        trxLoginRepository.save(trxLogin);

    }

    @Override
    public LocalDateTime getLogoutDate(String userId) {

        Long checkNullByCount = trxLoginRepository.checkLogoutByCount(userId);
        if (checkNullByCount > 0){ // checkNullByCount bernilai > 0
            return trxLoginRepository.findByUserIdNull(userId).getLogoutDate(); // ya, return null
        } else {
            return LocalDateTime.now(); // tidak, return localdatetime now
        }


    }

    @Override
    public LocalDateTime getLoginDate(String userId) {
        Long checkNullByCount = trxLoginRepository.checkLogoutByCount(userId);
        if (checkNullByCount > 0){
            return trxLoginRepository.findByUserIdNull(userId).getLoginDate();
        } else {
            return LocalDateTime.now();
        }
    }

    @Override
    public void saveUpdateLogout(String username, String token) {

        LocalDateTime logoutDate = LocalDateTime.now();

        //Pengambilan token dari header mendapatkan spasi di depan token makan harus di trim
        String tokenRedis = token.trim();

        //mengecek apakah ada cache di redis dari token user id
        if (redisTrxLoginService.getByToken(tokenRedis) != null){
            //menghapus cache user sesuai dengan user id dari token
            redisTrxLoginService.deleteByToken(tokenRedis);
        }

        //menambahkan data logoutdate pada table
        trxLoginRepository.updateLogout(logoutDate, username);

    }

    @Override
    public void updateChangepwd(RequestChangepwdDTO dto, String user) {

        // instantiate encoder dari passwordConfig
        PasswordEncoder encoder = passwordConfig.passwordEncoder();

        // apabila null, maka akan throw loginException
        String oldHash = mstUserRepository.findById(user).orElseThrow(
                ()-> new LoginException("userId tidak ditemukan di database") // perubahan message dari "error"
        ).getPassword();

        // kondisional apabila password pada database sama dengan input dto OldPassword
        if(encoder.matches(dto.getOldPassword(), oldHash)){
            String newpwd = encoder.encode(dto.getNewPassword());
            trxLoginRepository.updatePassword(newpwd, user);
        } else {
            throw new LoginException("Old Password Salah input"); // jika tidak sama, maka throw login exception
        }


    }

    @Override
    public ForgotPasswordResponseDTO getForgotPasswordDTO(RequestDTO dto) {

        // panggil entityMstUser dengan optional dari basic CRUD Repositorynya
        // apabila null, maka akan masuk ke catch karena NullPointer Exception
        Optional<MstUser> optionalMstUser = mstUserRepository.findById(dto.getUserId());
        MstUser entity = optionalMstUser.orElse(null);

        LocalDateTime requestDate = LocalDateTime.now();
        String token = passwordConfig.passwordEncoder().encode(entity.getId() + requestDate);
        String link = "http://<alamat_ui>/<forgot_page>?key=" + token;
        String status = "0";

        // dapatkan setting Expired Date dari tabel Master Global Setting dengan Setting Group LINK_FORGOT_EXP
        MstGlobalSetting globalSettingEntity = mstGlobalSettingRepository.findById_SettingGroup("LINK_FORGOT_EXP");

//        List<MstGlobalSetting> globalSettingList = mstGlobalSettingRepository.findAll();
//        MstGlobalSetting globalSettingEntity = null;
//        for (MstGlobalSetting globalSetting:globalSettingList) {
//            if(globalSetting.getId().getSettingGroup().equals("LINK_FORGOT_EXP")){
//                globalSettingEntity = globalSetting;
//                break;
//            }
//        }

        long settingValue = Long.parseLong(globalSettingEntity.getSettingValue());
        String settingCode = globalSettingEntity.getId().getSettingCode();
        LocalDateTime expiredDate = null;

        if (settingCode.equals("MINUTE")){
            expiredDate = requestDate.plusMinutes(settingValue);
        } else if (settingCode.equals("HOUR")){
            expiredDate = requestDate.plusHours(settingValue);
        } else{
            throw new RuntimeException();
        }

        // save entity baru TrxForgotPwd
        trxForgotPwdRepository.save(new TrxForgotPwd(dto.getUserId(), requestDate, token, status, expiredDate));

        return new ForgotPasswordResponseDTO(entity.getId(), link);
    }

    @Override
    public ResponseLoginDTO processForgotPwd(ProcessForgotPwdDTO dto) {

        // panggil TrxForgotPwd
        TrxForgotPwd trxForgotPwd = trxForgotPwdRepository.findByToken(dto.getToken());

        if(dto.getUserId().equals(trxForgotPwd.getUserId()) // Jika UserID DTO sama dengan userID pada Token di tabel
                && trxForgotPwd.getStatus().equals("0")  // Jika Status sama dengan 0
                && trxForgotPwd.getExpiredDate().isAfter(LocalDateTime.now())) // Jika ExpireDate belum habis
        {

            Optional<MstUser> optionalMstUser = mstUserRepository.findById(dto.getUserId());
            if(optionalMstUser.isEmpty()){
                throw new RuntimeException(); // apabila userId tidak ditemukan << kemungkinan besar harusnya gaada
            }
            else { // Update password baru ke tabel Mst_User
                optionalMstUser.get().setPassword(passwordConfig.passwordEncoder().encode(dto.getPassword()));
            }

            // update status di TrxForgotPwd
            trxForgotPwd.setStatus("1"); // set status menjadi 1
            trxForgotPwdRepository.save(trxForgotPwd);

            // set response apabila berhasil
            ResponseLoginDTO response = new ResponseLoginDTO();
            response.setUserId(dto.getUserId());
            return response;
        } else{
            throw new RuntimeException();
        }
    }

    @Override
    public ResponseMenuUserDTO getMenuUserDTO(String userId) {

        // Instantiate list Role User dan list menuAction
        List<RoleUser> roleUserList = new ArrayList<>();
        List<MenuActionDTO> menuActionList = new ArrayList<>();

        // dapatkan string roleID dari MstUserRole
        MstUserRole mstUserRole = mstUserRoleRepository.findById_UserId(userId)
                .orElseThrow(
                        () -> new NotFoundException("MstUserRole entity not found"));
        String roleId = mstUserRole.getId().getRoleId();

        // dapatkan List entity MstRoleMenu berdasarkan roleId
        List<MstRoleMenu> mstRoleMenuList = mstRoleMenuRepository.findById_RoleId(roleId);

        // dapatkan List entity MstMenu
        List<MstMenu> menuList = mstMenuRepository.findAll();
        for (MstMenu mstMenu:menuList) {

        }

        return null;
    }

    @Override
    public ResponseLogoutDTO checkAuth(String userId, CheckAuthDTO dto) {


        return null;
    }

    @Override
    public void saveUpdateForceLogout(String userId, String password) {

    }
}
