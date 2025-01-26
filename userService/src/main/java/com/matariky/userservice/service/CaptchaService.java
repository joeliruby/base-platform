package com.matariky.userservice.service;

import com.google.code.kaptcha.Producer;
import com.matariky.redis.RedisUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Base64;

import javax.imageio.ImageIO;

/**
 * 验证码
 *
 */
@Service
public class CaptchaService {
    @Autowired
    private Producer producer;
    @Autowired
    private RedisUtils redisUtils;
    
    /**
     * 定义图形大小
     */
    private static final int width = 90;
    /**
     * 定义图形大小
     */
    private static final int height = 36;
    /**
     * 定义干扰线 Quantity
     */
    private static final int count = 200;

    /**
     * 干扰线的长度=1.414*lineWidth
     */
    private static final int lineWidth = 2;

    /**
     * 图片格式
     */
    private static final String IMG_FORMAT = "JPEG";

    /**
     * base64 图片前缀
     */
    private static final String BASE64_PRE = "data:image/jpg;base64,";
    
    
    
    /**
     *  Generation base64字符串
     * @param resultCode
     * @return
     * @throws IOException
     */
    public  String generate(String resultCode) throws IOException {
        BufferedImage image = getImageBuffer(resultCode);

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        //写入流中
        ImageIO.write(image, IMG_FORMAT, byteStream);
        //转换成字节
        byte[] bytes = byteStream.toByteArray();
        //转换成base64串
        String base64 = Base64.getEncoder().encodeToString(bytes).trim();
        base64 = base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n

        //写到指定位置
        //ImageIO.write(bufferedImage, "png", new File(""));

        return BASE64_PRE+base64;
    }
    
    
    private  BufferedImage getImageBuffer(String resultCode){
        // 在内存中创建图象
        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        final Graphics2D graphics = (Graphics2D) image.getGraphics();
        // 设定背景颜色
        graphics.setColor(Color.WHITE); // ---1
        graphics.fillRect(0, 0, width, height);
        // 设定边框颜色
//		graphics.setColor(getRandColor(100, 200)); // ---2
        graphics.drawRect(0, 0, width - 1, height - 1);

        final Random random = new Random();
        // 随机产生干扰线，使图象中的认证码不易被其它程序探测到
        for (int i = 0; i < count; i++) {
            graphics.setColor(getRandColor(150, 200)); // ---3

            final int x = random.nextInt(width - lineWidth - 1) + 1; // 保证画在边框之内
            final int y = random.nextInt(height - lineWidth - 1) + 1;
            final int xl = random.nextInt(lineWidth);
            final int yl = random.nextInt(lineWidth);
            graphics.drawLine(x, y, x + xl, y + yl);
        }
        // 取随机产生的认证码
        for (int i = 0; i < resultCode.length(); i++) {
            // 将认证码显示到图象中,调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接 Generation 
            // graphics.setColor(new Color(20 + random.nextInt(130), 20 + random
            // .nextInt(130), 20 + random.nextInt(130)));
            // 设置字体颜色
            graphics.setColor(Color.BLACK);
            // 设置字体样式
//			graphics.setFont(new Font("Arial Black", Font.ITALIC, 18));
            graphics.setFont(new Font("Times New Roman", Font.BOLD, 24));
            // 设置字符，字符间距，上边距
            graphics.drawString(String.valueOf(resultCode.charAt(i)), (23 * i) + 8, 26);
        }
        // 图象生效
        graphics.dispose();
        return image;
    }
    
    
    private  Color getRandColor(int fc, int bc) { // 取得给定范围随机颜色
        final Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }

        final int r = fc + random.nextInt(bc - fc);
        final int g = fc + random.nextInt(bc - fc);
        final int b = fc + random.nextInt(bc - fc);

        return new Color(r, g, b);
    }
    
    public BufferedImage create(String uuid) {
        // Generation 文字验证码
        String code = producer.createText();

        //保存到缓存
        setCache(uuid, code);
        

        return producer.createImage(code);
    }

    public boolean validate(String uuid, String code) {
        //获取验证码
        String captcha = getCache(uuid);

        //效验成功
        if(code.equalsIgnoreCase(captcha)){
            return true;
        }

        return false;
    }
    
    

    private void setCache(String key, String value){
          key = "sys:captcha:" + key;
          redisUtils.set(key, value, 300);
    }

    private String getCache(String key){
    	key = "sys:captcha:" + key;
        String captcha = (String)redisUtils.get(key);
        //删除验证码
        if(captcha != null){
            redisUtils.delete(key);
        }
        return captcha;    
    }
}