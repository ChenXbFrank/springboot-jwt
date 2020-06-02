package com.cxb.jwt.web;

import com.alibaba.fastjson.JSONObject;
import com.cxb.jwt.entity.User;
import com.cxb.jwt.service.IUserService;
import com.cxb.jwt.util.AjaxResult;
import com.cxb.jwt.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping
@Slf4j
public class LoginController {

    @Autowired
    private IUserService userService;


    @PostMapping("login")
    @ResponseBody
    public AjaxResult login (@RequestBody Map<String,String> map){
        String username = map.get("username");
        String passWord = map.get("passWord");
        //身份验证
        boolean isSuccess =  userService.checkUser(username,passWord);
        if (isSuccess) {
            //模拟数据库查询
            User user = userService.getUser(username);
            if (user != null) {
                //返回token
                String token = JwtUtil.sign(username, passWord);
                if (token != null) {
                    return AjaxResult.success("成功",token);
                }
            }
        }
        return AjaxResult.fail();
    }

    @PostMapping("getUser")
    @ResponseBody
    public AjaxResult getUserInfo(HttpServletRequest request, @RequestBody Map<String, String> map){
        String username = map.get("username");
        String token = request.getHeader("token");
        boolean verity = JwtUtil.verity(token);
        if (verity) {
            User user = userService.getUser(username);
            if (user != null) {
                return AjaxResult.success("成功", JSONObject.toJSONString(user));
            }
        }
        return AjaxResult.fail();
    }
}

