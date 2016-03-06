package com.lenovo.crepes.utils;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * Description：时间格式转换类<br/>
 * Copyright (c) ,2015 , janson <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name:DataTransUtils.java <br/>
 * Date: 2015-10-9
 *
 * @author 郭少兵
 * @version : 1.0
 */
@SuppressLint("SimpleDateFormat")
public class DataTransUtils {

	/**
	 *
	 * @param dataString
	 * @param formatString  例如："yyyy-MM-dd hh:mm:ss"
	 * @return
	 */
	public static long transdata(String dataString,String formatString) {

		SimpleDateFormat format=new SimpleDateFormat(formatString);
		try {
			Date parse = format.parse(dataString);
			return parse.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 *
	 * @param milliseconds 毫秒
	 * @param formatString 例如："yyyy-MM-dd hh:mm:ss"
	 * @return "yyyy-MM-dd hh:mm:ss"格式字符串
	 */
	public static String transdata(long milliseconds,String formatString)  {
		SimpleDateFormat format=new SimpleDateFormat(formatString);
		String format2 = format.format(new Date(milliseconds));
		return format2;
	}

	/**
	 * @param data 类型：Date
	 * @param formatString  类型：String 例如："yyyy-MM-dd hh:mm:ss" 
	 * @return "yyyy-MM-dd hh:mm:ss"格式字符串
	 */
	public static String transdata(Date data,String formatString)  {
		SimpleDateFormat format=new SimpleDateFormat(formatString);
		String format2 = format.format(data);
		return format2;
	}
}
