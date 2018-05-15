package com.pjmike.project.controller;

import com.google.gson.Gson;
import com.pjmike.project.domain.Contact;
import com.pjmike.project.domain.Details;
import com.pjmike.project.domain.Home;
import com.pjmike.project.domain.Images;
import com.pjmike.project.domain.dto.DetailDTO;
import com.pjmike.project.exception.ImgException;
import com.pjmike.project.exception.ObjectException;
import com.pjmike.project.qiniu.IQiNIuService;
import com.pjmike.project.service.DetailAndImageService;
import com.pjmike.project.service.HomeService;
import com.pjmike.project.service.IndexAndCantactService;
import com.pjmike.project.service.impl.IndexAndCantactServiceImpl;
import com.pjmike.project.utils.*;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author pjmike
 * @create 2018-04-26 16:41
 */
@RestController
@Slf4j
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private IQiNIuService qiNIuService;
    @Autowired
    private HomeService homeService;
    @Autowired
    private IndexAndCantactService indexAndCantactService;
    @Autowired
    private DetailAndImageService detailAndImageService;
    @Autowired
    private FastDFSClientWrapper fastDFSClientWrapper;
    @Value("${Image.savePath}")
    private String ImageSavePath;
    @Value("${Image.url}")
    private String ImageUrl;
    @Value("${QiNiu}")
    private String QiNiu;

    /**
     * 更改
     *
     * @param id
     * @param home
     * @return
     */
    @PutMapping("/{id}")
    public ResponseResult updateHome(@PathVariable("id")Integer id, @RequestBody Home home) {
        Home target = homeService.findHomeById(id);
        if (target == null) {
            throw new ObjectException("资源不存在");
        }
        BeanUtils.copyProperties(home,target, BeanCopyUtils.getNullPropertyNames(home));
        Home result = homeService.updateHome(target);
        return ResultUtils.success(result);
    }

    /**
     * 查询某一个
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult findHomeById(@PathVariable("id") Integer id) {
        Home result = homeService.findHomeById(id);
        return ResultUtils.success(result);
    }

    /**
     * 保存
     *
     * @param home
     * @return
     */
    @PostMapping
    public ResponseResult saveHome(@RequestBody Home home) {
        Home result = homeService.save(home);
        return ResultUtils.success(result);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping
    public ResponseResult findAllHome() {
        List<Home> homes = homeService.findAll();
        return ResultUtils.success(homes);
    }

    /**
     * 上传图片到服务器保存
     *
     * @param id
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/{id}/photoes")
    public ResponseResult uploadFile(@PathVariable("id")Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
            throw new ImgException("图片为空");
        }
        Home home = homeService.findHomeById(id);
        //获取原始名
        String originalFileName = file.getOriginalFilename();
        File newFile = new File(ImageSavePath + originalFileName);
        //写入
        file.transferTo(newFile);
        home.setImg(originalFileName);
        homeService.updateHome(home);
        return ResultUtils.success(ImageUrl + originalFileName);
    }

    /**
     * 利用七牛云上传图片
     *
     * @param id
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/{id}/photos")
    public ResponseResult uploadImg(@PathVariable("id")Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
            throw new ImgException(ExceptionEnum.IMG_IS_NULL.getMsg());
        }
        Home home = homeService.findHomeById(id);
        //获取原始名
        String originalFileName = file.getOriginalFilename();
        if (home == null) {
            throw new ObjectException(ExceptionEnum.OBJECT_NOT_FOUND.getMsg());
        }
        //写入
        home.setImg(QiNiu+originalFileName);
        homeService.updateHome(home);
        Response response = qiNIuService.uploadFile(file.getInputStream(),originalFileName);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        log.info("filename: {}",putRet.key);
        return ResultUtils.success(QiNiu + originalFileName);
    }

    /**
     * 利用fastdfs上传文件
     * <P>失败，带探索</P>
     *
     * @param id
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/{id}/photo")
    public ResponseResult upload(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
            throw new ImgException("图片为空");
        }
        String url = fastDFSClientWrapper.uploadFile(file);
        return ResultUtils.success("127.0.0.1:8080/" + url);
    }

    /**
     * 联系我们
     *
     * @return
     */
    @GetMapping("/contact")
    public ResponseResult contact() {
        Contact contact = indexAndCantactService.findCantact();
        return ResultUtils.success(contact);
    }

    /**
     * 更新联系我们
     *
     * @param contact
     * @return
     */
    @PutMapping("/contact")
    public ResponseResult updateContact(@RequestBody Contact contact) {
        Contact target = indexAndCantactService.findCantact();
        if (target == null) {
            throw new ObjectException("资源不存在");
        }
        BeanUtils.copyProperties(contact,target, BeanCopyUtils.getNullPropertyNames(contact));
        Contact result = indexAndCantactService.update(target);
        return ResultUtils.success(result);
    }

    /**
     * 更新联系我们图片
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/contact")
    public ResponseResult updateContactImage(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
            throw new ImgException(ExceptionEnum.IMG_IS_NULL.getMsg());
        }
        //获取原始名
        String originalFileName = file.getOriginalFilename();
        Contact target = indexAndCantactService.findCantact();
        target.setImageUrl(QiNiu + originalFileName);
        indexAndCantactService.update(target);
        Response response = qiNIuService.uploadFile(file.getInputStream(),originalFileName);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        log.info("filename: {}",putRet.key);
        return ResultUtils.success(target);
    }

    @GetMapping("/inner")
    public ResponseResult inner() {
        Details details = detailAndImageService.find();
        List<Images> images = detailAndImageService.findAll();
        DetailDTO detailDTO = new DetailDTO();
        detailDTO.setContent(details.getContent());
        detailDTO.setImages(images);
        return ResultUtils.success(detailDTO);
    }

    @PutMapping("/inner/content")
    public ResponseResult innerContent(@RequestBody Details details) {
        detailAndImageService.updateDetails(details);
        return ResultUtils.success();
    }

    @DeleteMapping("/inner/images/{id}")
    public ResponseResult deleteImages(@PathVariable("id") Integer id) {
        detailAndImageService.delete(id);
        return ResultUtils.success();
    }

    @PostMapping("/inner/images")
    public ResponseResult postImages(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
            throw new ImgException(ExceptionEnum.IMG_IS_NULL.getMsg());
        }

        //获取原始名
        String originalFileName = file.getOriginalFilename();

        Response response = qiNIuService.uploadFile(file.getInputStream(),originalFileName);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        log.info("filename: {}",putRet.key);
        Images images = new Images();
        images.setImageUrl(QiNiu+originalFileName);
        Images result = detailAndImageService.save(images);
        return ResultUtils.success(result);
    }
    @PostMapping("/inner/images/{id}")
    public ResponseResult updateImages(@PathVariable("id")Integer id,@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
            throw new ImgException(ExceptionEnum.IMG_IS_NULL.getMsg());
        }

        //获取原始名
        String originalFileName = file.getOriginalFilename();

        Response response = qiNIuService.uploadFile(file.getInputStream(),originalFileName);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        log.info("filename: {}",putRet.key);
        Images images = detailAndImageService.findOne(id);
        images.setImageUrl(QiNiu+originalFileName);
        detailAndImageService.save(images);
        return ResultUtils.success(QiNiu+originalFileName);
    }
}
