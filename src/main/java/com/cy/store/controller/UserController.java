package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.impl.UserService;
import com.cy.store.units.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    public UserService userService;

    @RequestMapping("register")
    public JsonResult userRegister(@RequestBody User user) {
       userService.userRegister(user);
       return new JsonResult(OK);
    }

    @RequestMapping("login")
    public JsonResult userLogin(String username, String password, HttpSession session) {
        User result = userService.login(username, password);

        session.setAttribute("uid", result.getUid());
        session.setAttribute("username", result.getUsername());
        System.out.println(getUidFromSession(session));
        return new JsonResult(OK, result);

    }

    @RequestMapping("resetPassword")
    public JsonResult changePassword(String oldPassword, String newPassword, HttpSession session) {
        userService.changePassword(getUidFromSession(session), oldPassword, newPassword);
        return new JsonResult(OK);
    }

    @GetMapping("queryUser")
    public JsonResult queryUser(HttpSession session) {
        Integer uid = getUidFromSession(session);
        User result = userService.findUserById(uid);
        return new JsonResult(OK, result);
    }

    @RequestMapping("updateInfo")
    public JsonResult updateInfo(User user, HttpSession session) {
        user.setUid(getUidFromSession(session));
        userService.changeUserInfo(user);
        return new JsonResult(OK);
    }

    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
        AVATAR_TYPES.add("image/jpg");
    }
    @RequestMapping("upload_avatar")
    public JsonResult uploadAvatar(MultipartFile file, HttpSession session) throws IOException {
        Integer uid = getUidFromSession(session);
        if (file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if(file.getSize() > AVATAR_MAX_SIZE){
            throw new FileSizeException("文件超出限制");
        }
        if (!AVATAR_TYPES.contains(file.getContentType())){
            throw new FileTypeException("文件类型不支持");
        }

        // 创建文件存储目录

        String fileName = session.getServletContext().getRealPath("/upload/avatar");
        File filePath = new File(fileName);
        if (!filePath.exists()){
            filePath.mkdirs();
        }
        // 重新定义文件名称
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String fileNameSuffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase() + fileNameSuffix;

        File dest = new File(filePath, filename);

        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileStateException("文件状态异常");
        } catch (IllegalStateException e) {
            throw new FileUploadException("文件上传异常");
        }
        String avatar = "/upload/avatar/" + filename;
        userService.changeUserAvatar(uid, avatar, getUsernameFromSession(session));
        return new JsonResult(OK, avatar);
    }
}
