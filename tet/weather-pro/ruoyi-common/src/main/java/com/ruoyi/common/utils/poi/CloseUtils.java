package com.ruoyi.common.utils.poi;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author yyeran yyearn_a@163.cim
 * @title CloseUtils
 * @date 2021/4/2
 */
public class CloseUtils {

    public static void closeStream(Workbook workbook, OutputStream out) {
        if (workbook != null)
        {
            try
            {
                workbook.close();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
        if (out != null)
        {
            try
            {
                out.close();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
    }
}
