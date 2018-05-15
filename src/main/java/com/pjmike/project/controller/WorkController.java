package com.pjmike.project.controller;

import com.google.gson.Gson;
import com.pjmike.project.domain.Images;
import com.pjmike.project.domain.WorkImages;
import com.pjmike.project.domain.Works;
import com.pjmike.project.exception.ImgException;
import com.pjmike.project.exception.ObjectException;
import com.pjmike.project.qiniu.IQiNIuService;
import com.pjmike.project.service.ImageService;
import com.pjmike.project.service.WorkService;
import com.pjmike.project.utils.BeanCopyUtils;
import com.pjmike.project.utils.ExceptionEnum;
import com.pjmike.project.utils.ResponseResult;
import com.pjmike.project.utils.ResultUtils;
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
 * @create 2018-04-26 20:14
 */
@RestController
@Slf4j
@RequestMapping("/works")
public class WorkController {
    @Autowired
    private IQiNIuService qiNIuService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private WorkService workService;
    @Value("${Image.savePath}")
    private String ImageSavePath;
    @Value("${Image.url}")
    private String ImageUrl;
    @Value("${QiNiu}")
    private String QiNiu;

    /**
     * 查询某一个
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getWorkById(@PathVariable("id") Integer id) {
        Works work = workService.findWorksById(id);
        return ResultUtils.success(work);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping
    public ResponseResult getAllWorks() {
        List<Works> works = workService.findAllWorks();
        return ResultUtils.success(works);
    }

    /**
     * 保存
     *
     * @param works
     * @return
     */
    @PostMapping
    public ResponseResult saveWorks(@RequestBody Works works) {
        Works result = workService.save(works);
        return ResultUtils.success(result);
    }

    /**
     * 更改
     *
     * @param id
     * @param works
     * @return
     */
    @PutMapping("/{id}")
    public ResponseResult updateWork(@PathVariable("id") Integer id, @RequestBody Works works) {
        Works target = workService.findWorksById(id);
        if (target == null) {
            throw new ObjectException("资源不存在");
        }
        BeanUtils.copyProperties(works, target, BeanCopyUtils.getNullPropertyNames(works));
        Works result = workService.updateWorks(target);
        return ResultUtils.success(result);
    }
    @PostMapping("/{id}/photoes")
    public ResponseResult uploadFile(@PathVariable("id")Integer id,@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
            throw new ImgException("图片为空");
        }
        Works works = workService.findWorksById(id);
        //获取原始名
        String originalFileName = file.getOriginalFilename();
        File newFile = new File(ImageSavePath + originalFileName);
        //写入
        file.transferTo(newFile);
        if (works == null) {
            throw new ObjectException(ExceptionEnum.OBJECT_NOT_FOUND.getMsg());
        }
        works.setImg(originalFileName);
        workService.updateWorks(works);
        return ResultUtils.success(ImageUrl + originalFileName);
    }

    /**
     * 删除work
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult deleteWork(@PathVariable("id") Integer id) {
        workService.delete(id);
        return ResultUtils.success();
    }
    /**
     * 上传图片到七牛云
     *
     * @param id
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/{id}/photos")
    public ResponseResult upload(@PathVariable("id")Integer id,@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
            throw new ImgException(ExceptionEnum.IMG_IS_NULL.getMsg());
        }
        Works works = workService.findWorksById(id);
        //获取原始名
        String originalFileName = file.getOriginalFilename();
        if (works == null) {
            throw new ObjectException(ExceptionEnum.OBJECT_NOT_FOUND.getMsg());
        }
        works.setImg(QiNiu+originalFileName);
        workService.updateWorks(works);
        Response response = qiNIuService.uploadFile(file.getInputStream(),originalFileName);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        log.info("filename: {}",putRet.key);
        return ResultUtils.success(QiNiu + putRet.key);
    }

    /**
     * 详情页所有图片
     *
     * @param workId
     * @return
     */
    @GetMapping("/{workId}/images")
    public ResponseResult getWorkImages(@PathVariable("workId")Integer workId) {
        List<WorkImages> list = imageService.findImagesByWorkId(workId);
        return ResultUtils.success(list);
    }

    /**
     * 更新详情页的图片URL
     *
     * @param workId
     * @param imageId
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/{workId}/images/{imageId}")
    public ResponseResult updateImageUrL(@PathVariable("workId")Integer workId,@PathVariable("imageId")Integer imageId,
                                         @RequestParam("file")MultipartFile file) throws IOException {
        if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
            throw new ImgException(ExceptionEnum.IMG_IS_NULL.getMsg());
        }
        WorkImages workImages = imageService.findImageByIdAndWorkId(imageId, workId);
        //获取原始名
        String originalFileName = file.getOriginalFilename();
        if (workImages == null) {
            throw new ObjectException(ExceptionEnum.OBJECT_NOT_FOUND.getMsg());
        }
        workImages.setImageUrl(QiNiu+originalFileName);
        imageService.updateImage(workImages);
        Response response = qiNIuService.uploadFile(file.getInputStream(),originalFileName);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        log.info("filename: {}",putRet.key);
        return ResultUtils.success(QiNiu + putRet.key);
    }

    /**
     * 删除详情页图片
     *
     * @param workId
     * @param imageId
     * @return
     */
    @DeleteMapping("/{workId}/images/{imageId}")
    public ResponseResult deleteImage(@PathVariable("workId") Integer workId, @PathVariable("imageId") Integer imageId) {
        imageService.deleteImageByWorkIdAndId(imageId, workId);
        return ResultUtils.success();
    }

    /**
     * 添加图片
     *
     * @param workId
     * @param file
     * @return
     */
    @PostMapping("/{workId}/images")
    public ResponseResult addImages(@PathVariable("workId") Integer workId, @RequestParam("file")MultipartFile file) throws IOException {
        if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
            throw new ImgException(ExceptionEnum.IMG_IS_NULL.getMsg());
        }
        //获取原始名
        String originalFileName = file.getOriginalFilename();
        WorkImages images = new WorkImages();
        images.setWorkId(workId);
        images.setImageUrl(QiNiu + originalFileName);
        WorkImages result = imageService.addImage(images);
        Response response = qiNIuService.uploadFile(file.getInputStream(),originalFileName);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        log.info("filename: {}",putRet.key);
        return ResultUtils.success(result);
    }
}
