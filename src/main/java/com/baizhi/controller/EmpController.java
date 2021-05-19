package com.baizhi.controller;

import com.baizhi.entity.Emp;
import com.baizhi.service.EmpService;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 姚天下
 */
@RestController
@CrossOrigin    //允许跨域
@RequestMapping("/emp")
@Slf4j
public class EmpController {
    @Autowired
    private EmpService empService;

    @Value("${photo.dir}")
    private String realPath;

    /**
     * 获取员工列表
     * @return  List<Emp>
     */
    @GetMapping("/findAll")
    public List<Emp> findAll(){
        log.info("into-------------------->Emp--->findAll");
        List<Emp> empsDB = empService.findAll();
        log.info("empsDB:"+empsDB);
        return empsDB;
    }


    @GetMapping("/findOneById")
    public Emp findOneById(String id){
        Emp empDB = empService.findOneById(id);
        return  empDB;
    }

    /**
     * 保存员工信息
     * @param emp emp对象
     * @param photo emp头像
     * @return  结果集
     * @throws IOException
     */
    @PostMapping("/save")
    public Map<String,Object> save(Emp emp,MultipartFile photo) throws IOException {
        log.info("员工信息: [{}]", emp.toString());
        log.info("头像信息: [{}]", photo.getOriginalFilename());
        Map<String,Object> map = new HashMap<>();
        try{
            //头像保存
           String newFileName= UUID.randomUUID().toString()+"."+FilenameUtils.getExtension(photo.getOriginalFilename());
           photo.transferTo(new File(realPath,newFileName));
           //设置头像地址
            emp.setPath(newFileName);
            //保存员工信息
            empService.save(emp);
            map.put("state",true);
            map.put("msg","员工信息保存成功!");
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg", "员工信息保存失败!");
        }
        return map;
    }

    /**
     *  删除
     */
    @GetMapping("/delete")
    public Map<String,Object> delete(String id){
        log.info("删除员工的id:[{}]",id);
        Map<String,Object> map = new HashMap<>();
        try{
            //删除员工头像
            Emp empDB = empService.findOneById(id);
            File file=new File(realPath,empDB.getPath());
            if(file.exists()){
                //删除头像
                file.delete();
                //删除员工信息
                empService.delete(id);
                map.put("state",true);
                map.put("msg","删除员工信息成功!");
            }

        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg","\"删除员工信息失败!");
        }
        return map;
    }
}
