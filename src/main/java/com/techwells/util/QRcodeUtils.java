package com.techwells.util;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码的生成工具
 */
public class QRcodeUtils {





    public static void main(String[] args) {
        Gson gson=new Gson();

        Map<String,Object> map=new HashMap<>();
        map.put("name","http://www.baidu.com");

        //设置字符集
        Map<EncodeHintType,Object> hints=new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");

//        map.put("age","陈加兵");
        System.out.println(gson.toJson(map));
        try {
            BitMatrix matrix=new MultiFormatWriter().encode(gson.toJson(map),BarcodeFormat.QR_CODE,200,200,hints);
            //生成的路径
            Path path=FileSystems.getDefault().getPath("/home/chenjiabing/image");
            //将二维码生成到指定的位置
            MatrixToImageWriter.writeToPath(matrix,"jpg",path);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
