package com.meng.algo.demo.algotest.algomodel.v1.stoppolicy;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description : .
 * @Author : robinwb.
 * @Date : 2017年05月13日 11:17
 */
public enum StopPolicyCode {
	SYSTEM_SPECIAL("9999999999","促销系统、活动系统和展示系统");

    //学科常量
    public static final String ENGLISH = "10";
    public static final String ENGLISH_READ = "11";
    public static final String ENGLISH_VOCABULARY = "12";
    public static final String MATH    = "20";
    public static final String PRIMARYMATH    = "21";
    public static final String CHINESE = "30";
    public static final String PRIMARYCHINESE = "31";
    public static final String PHYSICS = "40";
    public static final String PRIMARYENGLISH = "13";


	StopPolicyCode(String code, String desc) {
		this.code=code;
		this.desc=desc;
	}
	//科目编号
	public static Map<String,String> subMap=new HashMap<>();
	//系统类型编号
	public static Map<String,String> sysMap=new HashMap<>();
	//课程阶段编号
    public static Map<String,String> phaseMap=new HashMap<>();
	//课程类型编号
    public static Map<String,String> courseMap=new HashMap<>();

	static {
		//初始化科目编号
		subMap.put(ENGLISH,"英语");
        subMap.put(ENGLISH_READ,"英语分级阅读");
        subMap.put(ENGLISH_VOCABULARY,"英语词汇");
		subMap.put(MATH,"数学");
    subMap.put(PRIMARYMATH,"小学数学");
		subMap.put(CHINESE,"语文");
    subMap.put(PRIMARYCHINESE,"小学语文");
		subMap.put(PHYSICS,"物理");
		subMap.put(PRIMARYENGLISH,"小学英语");
        //初始化系统类型编号
        sysMap.put("10","线下系统");
        sysMap.put("20","线上系统");
        sysMap.put("30","抓手系统(DL测评系统)");
        sysMap.put("40","体验系统(demo课)");
        sysMap.put("50","通用系统");
        //初始化课程阶段
        phaseMap.put("10","春季课");
        phaseMap.put("20","暑期课");
        phaseMap.put("30","秋季课");
        phaseMap.put("40","寒假课");
        phaseMap.put("50","通用");
        phaseMap.put("91","期中");
        phaseMap.put("92","期末");
        phaseMap.put("93","中考");
        //初始化课程类型
        courseMap.put("01","常规课/通用");
        courseMap.put("02","同步");
        courseMap.put("03","冲刺");
        courseMap.put("04","预习");
        courseMap.put("05","Demo课");
        courseMap.put("06","先锋");
        courseMap.put("07","复习");
        courseMap.put("08","奥数");

	}



	private String code;

	private String desc;

	public String getDesc() {
		return desc;
	}
	public String getCode() {
		return code;
	}
}
