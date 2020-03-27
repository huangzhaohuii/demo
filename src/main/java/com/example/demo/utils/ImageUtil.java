package com.example.demo.utils;

import com.jcraft.jsch.SftpException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.FileCopyUtils;

import java.io.*;


public  class ImageUtil{

	
	/**
     * 文件转为二进制数组
     * @param file
     * @return
     */
    public static byte[] fileToBinArray(File file){
        try {
            InputStream fis = new FileInputStream(file);
            byte[] bytes = FileCopyUtils.copyToByteArray(fis);
            return bytes;
        }catch (Exception ex){
            throw new RuntimeException("transform file into bin Array 出错",ex);
        }
    }
    
    /**
    * 读取网络图片并保存至服务器硬盘中
    * @return 图片保存的服务器路径
    */
    public static void getImages(byte[] bytes,String name){
    	System.out.println("============");
    String imgPath = "";
    try{
//           
            imgPath = ImageUtil.class.getClassLoader().getResource("").getPath();
            imgPath = imgPath.split("ems")[0];
            imgPath = imgPath+"images/"+name;
            imgPath = "d:/images/"+name;
//            imgPath = "/usr/images/"+name;
            //new一个文件对象用来保存图片，默认保存当前工程根目录
            File imageFile = new File(imgPath);
            //创建输出流
//            FileOutputStream outStream = new FileOutputStream(imageFile);
            FileOutputStream outStream = new FileOutputStream(imageFile);            
            //写入数据
            outStream.write(bytes);
            //关闭输出流
            outStream.close();
            //服务器文件路径
            System.out.println(imgPath);
    }catch(Exception e){
    	e.printStackTrace();
    }
    	
    }
        
    /**
    * 读取文件流
    * @param inStream
    * @return
    * @throws Exception
    */
        public static byte[] readInputStream(InputStream inStream) throws Exception{
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while( (len=inStream.read(buffer)) != -1 ){
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            //关闭输入流
            inStream.close();
            //把outStream里的数据写入内存
            return outStream.toByteArray();
        }
        
        public static byte[] baseToByte(String image) {
        	
        	image = image.replaceAll("data:image/jpeg;base64,", ""); 

			// Base64解码      
			byte[] imageByte = null;
			try {
                imageByte= Base64.decodeBase64(image.toString().replace("\r\n", ""));
                for (int i = 0; i < imageByte.length; ++i) {
                    // 调整异常数据
					if (imageByte[i] < 0) {
						imageByte[i] += 256;      
					}      
				}      
			} catch (Exception e) {
				 e.printStackTrace(); 
			}		
			System.out.println(imageByte+"bytes");
			return imageByte;
        }

    /**
     * 上传图片到服务器
     * @param image
     * @param orderId
     */
    public static void uploadImage(String image,String orderId) {
        InputStream input = new ByteArrayInputStream(ImageUtil.baseToByte(image));
        //SftpUtil sftpUtil = new SftpUtil("image", "image123", "10.186.0.60");
        SftpUtil sftpUtil = new SftpUtil("root", "Post987#@$11Nbt", "200.200.200.54");
        sftpUtil.login();
        try {
            // 存到指定位置
            sftpUtil.upload("/usr/images/", orderId+".png", input);
        } catch (SftpException e) {
            e.printStackTrace();
        }
        sftpUtil.logout();
    }

}
