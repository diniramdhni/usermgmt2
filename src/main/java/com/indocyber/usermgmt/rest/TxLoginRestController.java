package com.indocyber.usermgmt.rest;

import com.indocyber.usermgmt.dto.RequestChangepwdDTO;
import com.indocyber.usermgmt.dto.RequestDTO;
import com.indocyber.usermgmt.dto.ResponseLoginDTO;
import com.indocyber.usermgmt.dto.ResponseLogoutDTO;
import com.indocyber.usermgmt.dto.login.CheckAuthDTO;
import com.indocyber.usermgmt.dto.login.ResponseMenuUserDTO;
import com.indocyber.usermgmt.exception.LoginException;
import com.indocyber.usermgmt.dto.login.ForgotPasswordResponseDTO;
import com.indocyber.usermgmt.dto.login.ProcessForgotPwdDTO;
import com.indocyber.usermgmt.redis.RedisTrxLoginService;
import com.indocyber.usermgmt.service.TrxLoginService;
import com.indocyber.usermgmt.utility.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/")
public class TxLoginRestController {

    @Autowired
    private TrxLoginService trxLoginService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtToken jwtToken;

    //login using username=userId and password
    @PostMapping("login")
    public ResponseEntity<ResponseLoginDTO> login(
            @RequestBody RequestDTO dto){

        try{
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(dto.getUserId(), dto.getPassword());

            Authentication authentication = authenticationManager.authenticate(token);

        } catch(Exception e){
            throw new LoginException("User atau password salah");
        }

        // Generate Token JWT dengan utility JwtToken
        String token = jwtToken.generateToken(
                dto.getSubject(),
                dto.getUserId(),
                dto.getSECRET_KEY(),
                "role",
                dto.getAudience()
        );

        String name = trxLoginService.findNameById(dto.getUserId());

        ResponseLoginDTO responseLoginDTO = new ResponseLoginDTO(dto.getUserId(), name, token);

        trxLoginService.saveLogin(dto, token);


        return new ResponseEntity<>(responseLoginDTO, HttpStatus.OK);
    }


    //Logout
    @PostMapping("logoutUser")
    public ResponseEntity<ResponseLogoutDTO> logout(HttpServletRequest request){

//        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        String authorizationHeader = request.getHeader("Authorization");
        String username;
        String token;

        if(authorizationHeader != null){
            token = authorizationHeader.replace("Bearer", "");
            username = jwtToken.getUserId(token);
        }
        else {
            throw new LoginException("Authorization kosong");
        }

        LocalDateTime logoutDate = trxLoginService.getLogoutDate(username);

        if (logoutDate == null){
            trxLoginService.saveUpdateLogout(username, token);
        } else {
            throw new LoginException("Invalid Process");
        }

        ResponseLogoutDTO responseLogoutDTO = new ResponseLogoutDTO(username);

        return new ResponseEntity<>(responseLogoutDTO, HttpStatus.OK);
    }

    @PostMapping("forcelogout")
    public ResponseEntity<Object> forceLogout(
            @RequestBody RequestDTO dto
    ){

        LocalDateTime logoutDate = trxLoginService.getLogoutDate(dto.getUserId());

        if (logoutDate == null){
            trxLoginService.saveUpdateForceLogout(dto.getUserId(), dto.getPassword());
        } else {
            throw new LoginException("Invalid Process");
        }


        ResponseLogoutDTO responseLogoutDTO = new ResponseLogoutDTO(dto.getUserId());

        return new ResponseEntity<>(responseLogoutDTO, HttpStatus.OK);


    }

    //user can change password
    @PostMapping("changepwd")
    public ResponseEntity<ResponseLogoutDTO> changepwd(
            @RequestBody RequestChangepwdDTO dto,
            HttpServletRequest request
            ){

//        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        // dapatkan header Authorization yang berisi Bearer<token>
        String authorizationHeader = request.getHeader("Authorization");
        String user;

        if(authorizationHeader != null){ // Jika Authorization tidak null
            String token = authorizationHeader.replace("Bearer", "");
            user = jwtToken.getUserId(token); // dapatkan userId dari token
        }
        else {
            throw new LoginException("Please login");
        }

        LocalDateTime logoutDate = trxLoginService.getLogoutDate(user);

        if (logoutDate == null){

            trxLoginService.updateChangepwd(dto, user);

            ResponseLogoutDTO response = new ResponseLogoutDTO();
            response.setUserId(user);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new LoginException("Please Login");
        }
    }




    @PostMapping("forgotpwd")
    public ResponseEntity<ForgotPasswordResponseDTO> forgot(@RequestBody RequestDTO dto){
        try{
            ForgotPasswordResponseDTO responseDTO = trxLoginService.getForgotPasswordDTO(dto);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e){
            throw new LoginException("User ID tidak ditemukan");
        }
    }

    @PostMapping("forgotpwd/process")
    public ResponseEntity<Object> forgotProc(@RequestBody ProcessForgotPwdDTO dto){
        try{
            ResponseLoginDTO responseDTO = trxLoginService.processForgotPwd(dto);

            ResponseLogoutDTO responseLogoutDTO = new ResponseLogoutDTO();
            responseLogoutDTO.setUserId(dto.getUserId());
            return new ResponseEntity<>(responseLogoutDTO, HttpStatus.OK);
        } catch (Exception e){
            throw new LoginException("Validasi gagal, tidak dapat memproses");
        }
    }

//
//    @PostMapping("getmenubyuser")
//    public ResponseEntity<ResponseMenuUserDTO> getMenuByUser(HttpServletRequest request){
//
//        String authorizationHeader = request.getHeader("Authorization");
//        String user;
//
//        //get usrId
//        if(authorizationHeader != null){
//            String token = authorizationHeader.replace("Bearer", "");
//            user = jwtToken.getUserId(token);
//        }
//        else {
//            throw new LoginException("userId tidak ditemukan");
//        }
//
//        LocalDateTime logoutDate = trxLoginService.getLogoutDate(user);
//
//        //Check user login or logout
//        if (logoutDate == null){
//
//            ResponseMenuUserDTO responseMenuUserDTO = trxLoginService.getMenuUserDTO(user);
//            return new ResponseEntity<>(responseMenuUserDTO, HttpStatus.OK);
//
//        } else {
//            throw new LoginException("Please Login");
//        }
//    }
//
//    @PostMapping("checkuserauth")
//    public ResponseEntity<ResponseLogoutDTO> checkUserAuth(@RequestBody CheckAuthDTO dto, HttpServletRequest request){
//
//        String userId;
//
//        String authorizationHeader = request.getHeader("Authorization");
//        if(authorizationHeader != null){
//            String token = authorizationHeader.replace("Bearer", "");
//            userId = jwtToken.getUserId(token);
//        }
//        else {
//            throw new LoginException("login terlebih dahulu");
//        }
//
//        LocalDateTime logoutDate = trxLoginService.getLogoutDate(userId);
//
//
//        //Check user login or logout
//        if (logoutDate == null){
//            ResponseLogoutDTO responseDTO = trxLoginService.checkAuth(userId, dto);
//            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//
//        } else {
//            throw new LoginException("Please Login");
//        }
//
//
//    }

}
