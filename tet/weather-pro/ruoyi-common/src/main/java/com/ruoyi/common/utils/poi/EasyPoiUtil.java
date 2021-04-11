package com.ruoyi.common.utils.poi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.CustomException;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.UUID;

/**
 * EasyPOI
 * @author yyeran yyearn_a@163.cim
 * @title EasyPoiUtil
 * @date 2021/3/31
 */
public class EasyPoiUtil {

    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     *
     * @param templateUrl 模板地址
     * @param fileName 导出文件名称
     * @param dataMap  数据集合
     * @return data
     */
    public static AjaxResult export(String templateUrl,String fileName,Map<String, Object> dataMap){

        TemplateExportParams params = new TemplateExportParams(templateUrl);
        Workbook workbook = ExcelExportUtil.exportExcel(params, dataMap);
        OutputStream out = null;
        try
        {

            String filenameStr = encodingFilename(fileName,false);
            out = new FileOutputStream(getAbsoluteFile(filenameStr));
            workbook.write(out);
            return AjaxResult.success(filenameStr);
        }
        catch (Exception e)
        {
            log.error("导出Excel异常{}", e.getMessage());
            throw new CustomException("导出Excel失败，请联系网站管理员！");
        }
        finally
        {
            CloseUtils.closeStream(workbook, out);
        }
    }

    /**
     * 编码文件名
     */
    public static String encodingFilename(String filename,Boolean isRandomNumber)
    {
        if(isRandomNumber){
            return UUID.randomUUID().toString() + "_" + filename + ".xls";
        }
        return filename + ".xls";
    }

    /**
     * 获取下载路径
     *
     * @param filename 文件名称
     */
    public static String getAbsoluteFile(String filename)
    {
        String downloadPath = RuoYiConfig.getDownloadPath() + filename;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists())
        {
            desc.getParentFile().mkdirs();
        }
        return downloadPath;
    }
}
