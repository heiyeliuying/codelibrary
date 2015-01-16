package com.icode.library.tools.utils;

import java.math.BigDecimal;

import android.graphics.Point;
/**
 * 精密的数学运算相关的辅助类
 */
public class IMathUtils {

	    //默认除法运算精度
	    private static final int DEF_DIV_SCALE = 10;
	    /**
	     * 提供精确的加法运算。
	     * @param v1 被加数
	     * @param v2 加数
	     * @return 两个参数的和
	     */
	    public static double add(double v1,double v2){
	        BigDecimal b1 = new BigDecimal(Double.toString(v1));
	        BigDecimal b2 = new BigDecimal(Double.toString(v2));
	        return b1.add(b2).doubleValue();
	    }
	    /**
	     * 提供精确的减法运算。
	     * @param v1 被减数
	     * @param v2 减数
	     * @return 两个参数的差
	     */
	    public static double sub(double v1,double v2){
	        BigDecimal b1 = new BigDecimal(Double.toString(v1));
	        BigDecimal b2 = new BigDecimal(Double.toString(v2));
	        return b1.subtract(b2).doubleValue();
	    }
	    /**
	     * 提供精确的乘法运算。
	     * @param v1 被乘数
	     * @param v2 乘数
	     * @return 两个参数的积
	     */
	    public static double mul(double v1,double v2){
	        BigDecimal b1 = new BigDecimal(Double.toString(v1));
	        BigDecimal b2 = new BigDecimal(Double.toString(v2));
	        return b1.multiply(b2).doubleValue();
	    }
	 
	    /**
	     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
	     * 小数点以后10位，以后的数字四舍五入。
	     * @param v1 被除数
	     * @param v2 除数
	     * @return 两个参数的商
	     */
	    public static double div(double v1,double v2){
	        return div(v1,v2,DEF_DIV_SCALE);
	    }
	 
	    /**
	     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	     * 定精度，以后的数字四舍五入。
	     * @param v1 被除数
	     * @param v2 除数
	     * @param scale 表示表示需要精确到小数点以后几位。
	     * @return 两个参数的商
	     */
	    public static double div(double v1,double v2,int scale){
	        if(scale<0){
	            throw new IllegalArgumentException(
	                "The scale must be a positive integer or zero");
	        }
	        BigDecimal b1 = new BigDecimal(Double.toString(v1));
	        BigDecimal b2 = new BigDecimal(Double.toString(v2));
	        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	    }
	 
	    /**
	     * 提供精确的小数位四舍五入处理。
	     * @param v 需要四舍五入的数字
	     * @param scale 小数点后保留几位
	     * @return 四舍五入后的结果
	     */
	    public static double round(double v,int scale){
	        if(scale<0){
	            throw new IllegalArgumentException(
	                "The scale must be a positive integer or zero");
	        }
	        BigDecimal b = new BigDecimal(Double.toString(v));
	        BigDecimal one = new BigDecimal("1");
	        return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	    }
	    
	    
	    
	    
	    
	    public static int GetDistance(double x1, double y1, 
	    		double x2, double y2)  
	    {  
	        double radLat1 = rad(x1);  
	        double radLat2 = rad(x2);  
	        double a = radLat1 - radLat2;  
	        double b = rad(y1) - rad(y2);  
	        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)  
	                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));  
	        s = Math.round(s * 10000);  
	        return (int)s;  
	    } 
	 
	 private  static double rad(double d)  
	    {  
	        return d * Math.PI / 180.0;  
	    }
	    

	public static double getDistance(Point pStart,Point pEnd){
		double width = Math.abs(pStart.x - pEnd.x);
		double height = Math.abs(pStart.y - pEnd.y);
		return Math.sqrt((width * width + height * height));
	}
}