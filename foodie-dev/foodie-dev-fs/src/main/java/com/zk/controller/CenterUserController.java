package com.zk.controller;

import com.zk.pojo.Users;
import com.zk.resource.FileResource;
import com.zk.service.FdfsService;
import com.zk.service.center.CenterUserService;
import com.zk.utils.JSONResult;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CoderZk
 */
@RestController
@RequestMapping("/fdfs")
public class CenterUserController {

    @Autowired
    private FdfsService fdfsService;

    @Autowired
    private FileResource fileResource;

    @Autowired
    private CenterUserService centerUserService;

    @ApiOperation(value = "用户头像修改", notes = "用户头像修改", httpMethod = "POST")
    @PostMapping("/uploadFace")
    public JSONResult uploadFace(
            String userId,
            MultipartFile file,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String path = "";

        // 开始文件上传
        if (file != null) {
            // 获得文件上传的文件名称
            String fileName = file.getOriginalFilename();

            if (StringUtils.isNotBlank(fileName)) {

                // 文件重命名 lalala-face.png -> ["lalala-face", "png"]
                String[] fileNameArr = fileName.split("\\.");

                // 获取文件的后缀名
                String suffix = fileNameArr[fileNameArr.length - 1];

                if (!suffix.equalsIgnoreCase("png") &&
                        !suffix.equalsIgnoreCase("jpg") &&
                        !suffix.equalsIgnoreCase("jpeg")) {
                    return JSONResult.errorMsg("图片格式不正确!");
                }

//                path = fdfsService.upload(file, suffix);

                path = fdfsService.uploadOSS(file, userId, suffix);
                System.out.println(path);

            }
        } else {
            return JSONResult.errorMsg("文件不能为空!");
        }

        if (StringUtils.isNotBlank(path)) {
//            String finalUserFaceUrl = fileResource.getHost() + path;
            String finalUserFaceUrl = fileResource.getOssHost() + path;

            // 更新用户头像到数据库
            Users userResult = centerUserService.updateUserFace(userId, finalUserFaceUrl);


//            // 增加令牌token, 会整合进redis, 分布式会话
//            UsersVO usersVO = convertUsersVO(userResult);
//
//            // 把用户信息放入cookie中, 前端更新用户信息展示
//            CookieUtils.setCookie(request, response, "user",
//                    JsonUtils.objectToJson(usersVO), true);

        } else {
            return JSONResult.errorMsg("上传头像视频失败");
        }

        return JSONResult.ok();
    }
}
