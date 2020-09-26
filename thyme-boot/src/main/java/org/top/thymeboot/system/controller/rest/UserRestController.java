package org.top.thymeboot.system.controller.rest;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.top.thymeboot.common.base.ApiResponse;
import org.top.thymeboot.common.base.Constants;
import org.top.thymeboot.common.utils.UUIDUtils;
import org.top.thymeboot.system.model.SysUser;
import org.top.thymeboot.system.model.SysUserRole;
import org.top.thymeboot.system.service.SysRoleService;
import org.top.thymeboot.system.service.SysUserRoleService;
import org.top.thymeboot.system.service.SysUserService;
import org.top.thymeboot.system.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysUserRoleService userRoleService;

    @GetMapping("/getUserInfo")
    public ApiResponse getUserInfo(@RequestParam("page") int page, @RequestParam("page_size") int pageSize){

        JSONObject jsonObject = new JSONObject();
        List<UserVO> userVOList = new ArrayList<>();
        // 获取用户信息
        IPage<SysUser> users = userService.getAll(new Page(page,pageSize));
        if(users.getRecords() != null && users.getRecords().size() > 0) {
            // 获取用户角色
            for (SysUser sysUser : users.getRecords()) {
                String roleName = roleService.getById(sysUser.getId());
                UserVO userVO = new UserVO(roleName);
                BeanUtils.copyProperties(sysUser,userVO);
                userVOList.add(userVO);
            }
        }
        jsonObject.put("total",users.getTotal());
        jsonObject.put("page",users.getCurrent());
        jsonObject.put("page_size",users.getSize());
        jsonObject.put("sysUserList",userVOList);
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/deleteUser")
    public ApiResponse deleteUser(@RequestParam("id") String id){

        JSONObject jsonObject = new JSONObject();
        try{
        userRoleService.deleteByUserId(id);
        userService.deleteById(id);
        jsonObject.put("code",200);
        }
        catch (Exception e){
            jsonObject.put("code",500);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @PostMapping("/updateUser")
    public ApiResponse updateUser(@RequestBody UserVO userVO){
        JSONObject jsonObject = new JSONObject();
        try{
            SysUser sysUser = new SysUser();
            BeanUtils.copyProperties(userVO,sysUser);
            userRoleService.deleteByUserId(userVO.getId());
            SysUserRole sysUserRole = new SysUserRole(userVO.getId(),roleService.getIdByName(userVO.getUserRole()));
            userRoleService.insert(sysUserRole);
            userService.updateById(sysUser);
            jsonObject.put("code",200);
        }
        catch (Exception e){
            jsonObject.put("code",500);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @PostMapping("/addUser")
    public ApiResponse addUser(@RequestBody UserVO userVO){
        JSONObject jsonObject = new JSONObject();
        SysUser user = userService.findByName(userVO.getName());
        if(user == null) {
            String userId = UUIDUtils.getUUID();
            userVO.setId(userId);
            // 密码加密
            userVO.setPassword(new BCryptPasswordEncoder().encode(userVO.getPassword()));
            SysUserRole sysUserRole = new SysUserRole(userVO.getId(), roleService.getIdByName(userVO.getUserRole()));
            userRoleService.insert(sysUserRole);
            SysUser sysUser = new SysUser();
            BeanUtils.copyProperties(userVO,sysUser);
            if(userService.insert(sysUser) > 0){
                jsonObject.put("code",200);
            }else {
                jsonObject.put("code", 500);
            }
        }
        else {
            // 用户已存在
            jsonObject.put("code",501);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/editPassword")
    public ApiResponse editPassword(String id){
        JSONObject jsonObject = new JSONObject();
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setPassword(new BCryptPasswordEncoder().encode(Constants.CZMM));
        try{
            if (userService.updateById(sysUser) > 0){
                jsonObject.put("code", 200);
            }
        }catch (Exception e) {
            jsonObject.put("code", 500);
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/getAllRoleName")
    public ApiResponse getAllRoleName(){
        JSONObject jsonObject = new JSONObject();
        List<String> allRoleName = roleService.getAllRoleName();
        jsonObject.put("allRoleName",allRoleName);
        return ApiResponse.ofSuccess(jsonObject);
    }

    @PostMapping("/editUser")
    public ApiResponse editUser(@RequestBody UserVO userVO){
        JSONObject jsonObject = new JSONObject();
        SysUser user = userService.findByName(userVO.getName());
        if(user == null){
            jsonObject.put("code",500);
            return ApiResponse.ofSuccess(jsonObject);
        }
        BeanUtils.copyProperties(userVO,user);
        if(userService.updateById(user) > 0){
            jsonObject.put("code",200);
            return ApiResponse.ofSuccess(jsonObject);
        }
        else{
            return ApiResponse.fail("用户信息更新失败");
        }
    }
}
