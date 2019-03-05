package com.meng.algo.demo.algotest.algomodel.v1.stoppolicy;

import com.alibaba.fastjson.JSON;
//import com.yixue.tool.props.PropertyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description : .
 * @Author : robinwb.
 * @Date : 2017年04月26日 18:20
 */
public class StopPolicy {
	private static Logger logger = LoggerFactory.getLogger(StopPolicy.class);
	/**
	 * 根据系统编号获取BKT不同的参数
	 */
	private HashMap<String ,Double> initBKTParam(String subjectCode) {
		HashMap<String, Double> bktMap = new HashMap<>(16);
		//系统传来的课程类型编号(两位)
//		BKT_stoppolicy 不同科目对应的BKT的四个参数(lo&tran&guess&slip)
//		STOPPPOLICY.PARAMS=0.517&0.00255&0.434&0.205
//		BKT.P10=0.517&0.00255&0.434&0.205
//		BKT.P11=0.517&0.00255&0.434&0.205
//		BKT.P12=0.517&0.00255&0.434&0.205
//		BKT.P20=0.517&0.00255&0.434&0.205
//		BKT.P30=0.517&0.00255&0.434&0.205
//		BKT.P40=0.517&0.00255&0.434&0.205
		try {
//			String subjectCode = sessionParam.getSys_code().substring(0, 2);
//			String bktparam = PropertyConfig.getProperty("BKT.P" + subjectCode);
//			if (!StringUtil.checkNull(bktparam)) {
//				String[] params = bktparam.split("&");
//				if (params != null && params.length == 4) {
//					bktMap.put("lo",Double.parseDouble(params[0]));
//					bktMap.put("tran",Double.parseDouble(params[1]));
//					bktMap.put("guess",Double.parseDouble(params[2]));
//					bktMap.put("slip",Double.parseDouble(params[3]));
//				}
//			}
			bktMap.put("lo",0.517);//学生最初掌握知识点的概率
			bktMap.put("tran",0.00255);//学生从不会到会的转移概率
			bktMap.put("guess",0.434);//学生在不会的状态下，仍然猜对的概率
			bktMap.put("slip",0.205); //学生在会的状态下，仍然做错的概率
		} catch (Exception e) {
			bktMap.put("lo",0.517);//学生最初掌握知识点的概率
			bktMap.put("tran",0.00255);//学生从不会到会的转移概率
			bktMap.put("guess",0.434);//学生在不会的状态下，仍然猜对的概率
			bktMap.put("slip",0.205); //学生在会的状态下，仍然做错的概率
			e.printStackTrace();
		}
		return bktMap;
	}

	/**
	 * 接口入口
	 * @param sInput
	 * @return
	 */
	 public String getResult(String sInput) {
		StopPolicyParam param = new StopPolicyParam();
		HashMap<String,Object> process = new HashMap<>(16);
		try {
			//解析传进来的参数&参数校验
			param = parseJSONData(sInput);
			//根据科目加载相应系统对应的BKT四个概率参数
			HashMap<String, Double> bktMap = initBKTParam(param.getSubjectCode());
			List<Integer> ansList = null;
			//全量答案
			if (ansList == null)
				ansList = new ArrayList<Integer>();
			//添加本次作答结果
			ansList.addAll(param.getAnswers());
			//通过概率值，答案对错，偏移率标准判断是否掌握
			                      //停止及达标的原因
			process = process(ansList, bktMap);
			boolean is_stop = (boolean) process.get("is_stop");
			//如果模型未停止，当传入的全量答案数量大于系统方规定的最大题量时，根据能力值判断是否达标
			if (!is_stop) {
				if (ansList.size() >= param.getMax_size()) {
					//根据能力值判断是否达标
					process = estimateByAbility(param,bktMap,process);
				}
			}
		}  catch (Exception e) {
            e.printStackTrace();
		}

		// 返回值
		String result = composeReturnResult(process,param);
		return result;

	}

	/**
	 * 根据能力值判断是否达标
	 *
	 */
	private HashMap<String, Object> estimateByAbility(StopPolicyParam param, HashMap<String,Double> bktMap, HashMap<String,Object> process) {
		Double abilityOfStandard = null;
		//能力值达标
		//系统传来的课程类型编号(两位)
		String subjectCode = param.getSubjectCode();
		switch (subjectCode) {
//			STANDARD.ABILITY.ENGLISH=0.8
//			STANDARD.ABILITY.ENGLISH_VOCABULARY=0.8
//			STANDARD.ABILITY.ENGLISH_READ=0.8
//			STANDARD.ABILITY.MATH=0.65
//			STANDARD.ABILITY.PHYSICS=0.7
			case StopPolicyCode.ENGLISH:
//				abilityOfStandard = PropertyConfig.getDoubleProperty("STANDARD.ABILITY.ENGLISH");
				abilityOfStandard = 0.8;
				break;
			case StopPolicyCode.ENGLISH_READ:
//				abilityOfStandard = PropertyConfig.getDoubleProperty("STANDARD.ABILITY.ENGLISH_READ");
				abilityOfStandard = 0.8;
				break;
			case StopPolicyCode.ENGLISH_VOCABULARY:
//				abilityOfStandard = PropertyConfig.getDoubleProperty("STANDARD.ABILITY.ENGLISH_VOCABULARY");
				abilityOfStandard = 0.8;
				break;
			case StopPolicyCode.MATH:
//				abilityOfStandard = PropertyConfig.getDoubleProperty("STANDARD.ABILITY.MATH");
				abilityOfStandard = 0.65;
				break;
			case StopPolicyCode.CHINESE:
				String levelStr = "1";
				if (param.getNode_code().contains("_")) {
					levelStr = param.getNode_code().substring(param.getNode_code().lastIndexOf("_") + 1);
					try {
						Integer.parseInt(levelStr);
					} catch (Exception e) {
						levelStr = "1";
					}
				}
//				STANDARD.ABILITY.CN_1=0.76
//				STANDARD.ABILITY.CN_2=0.79
//				STANDARD.ABILITY.CN_3=0.81
//				STANDARD.ABILITY.CN_4=0.75
//				STANDARD.ABILITY.CN_5=0.79
//				STANDARD.ABILITY.CN_6=0.83
//				STANDARD.ABILITY.CN_7=0.79
//				STANDARD.ABILITY.CN_8=0.82
//				STANDARD.ABILITY.CN_9=0.85
//				abilityOfStandard = PropertyConfig.getDoubleProperty("STANDARD.ABILITY.CN_" + levelStr);
				break;
			case StopPolicyCode.PHYSICS:
//				abilityOfStandard = PropertyConfig.getDoubleProperty("STANDARD.ABILITY.PHYSICS");
				abilityOfStandard = 0.7;
				break;
		}
		System.out.println("根据能力值判断是否达标:"+abilityOfStandard);

		//系统传过来的能力值大于配置中的能力值，达标
		if (param.getAbility() >= abilityOfStandard) {

			//达标，停止
			process.put("is_stop",true);

			//掌握
			process.put("makeIt",true);

			//做题能力未稳定，题量达到最大值,通过能力值判断已达标
//			process.put("cause",AlgoMessage.NOTSTABLED_MASTERED.getId());
		} else {

			//未达标，停止
			process.put("is_stop",true);

			//未掌握
			process.put("makeIt",false);

			//做题能力未稳定，题量达到最大值,通过能力值判断未达标
//			process.put("cause", AlgoMessage.NOTSTABLED_NOTMASTERED.getId());
		}
		return process;
	}

	/**
	 * 参数校验
	 * @param sInput
	 * @throws Exception
	 */
	private StopPolicyParam parseJSONData(String sInput) throws Exception {
		StopPolicyParam param = null;
		try {
			param = JSON.parseObject(sInput, StopPolicyParam.class);
		} catch (Exception e) {
            e.printStackTrace();
		}
		// 验证参数是否存在
		if (param == null) {
//			throw BizException.PARAMETER_MISSING; //参数对象不能为空
		}
		//必备参数基本验证
//		new ValidateParam().validateIllegal(param.getMap_code())
//				.validateIllegal(param.getNode_code()).validateNull(param.getAbility())
//				.validateNull(param.getMax_size()).validateNull(param.getAnswers());
		if (param.getAnswers().size() < 1) {
            logger.error("题目答案参数不合法");
		}
		//验证答案格式是否正确
		for (Integer answer : param.getAnswers()) {
//			if (!AlgoCommon.RIGHT_FLAG.equals(String.valueOf(answer)) && !AlgoCommon.WRONG_FLAG
//					.equals(String.valueOf(answer))) {
//				throw BizException.PARAMETER_ANSWER_INVALID;
//			}
		}
		return param;
	}

	/**
	 *
	 * @param ansList
	 * @return
	 */
	public HashMap<String,Object> process(List<Integer> ansList,HashMap<String,Double> bktMap) {
		Boolean makeIt = false; //是否掌握
		Boolean is_stop =false;
		String cause = "";
		Double eps1 = 0.02;//0.01,0.015,0.012,0.018,0.02 //偏移率参考标准
		double lo = bktMap.get("lo");
		double tran = bktMap.get("tran");
		double guess = bktMap.get("guess");
		double slip = bktMap.get("slip");
		ArrayList<Double> c1list = new ArrayList<>();
		ArrayList<Double> c0list = new ArrayList<>();
		ArrayList<Double> prevlist = new ArrayList<>();
		ArrayList<Double> predlist = new ArrayList<>();
		ArrayList<Double> upperList = new ArrayList<>();

		Double prev_c1 = 0.0;
		Double prev_c0 = 0.0;
		Integer find_sta = 1;
		Double pc1 = 0.0;
		Double up0 = 0.0;
		Double up_t = 0.0;
		Double u_cc = 0.0;
		Double pl1_f = 0.0;
		Double pl1_t = 0.0;
		Double p_cc = 0.0;
		Double p_cbc = 0.0;
		HashMap<String, Object> resMap = new HashMap<>(16);
		for (int i = 0; i < ansList.size(); i++) {
			prevlist.add(lo);
			//学生答对题目的概率被解释为在知道知识点的情况下没有犯错，以及在不知道知识点的情况下猜对的概率之和
			//p(Correctn)=p(Ln)p(﹁S)+p(﹁Ln)p(G)
			pc1 = (1 - slip) * lo + guess * (1 - lo);
			predlist.add(pc1);

			if (i == 0) {
				up0 = lo;
			}
			up0 = up0 * (1 - slip) / (up0 * (1 - slip) + (1 - up0) * guess);
			up_t = up0 + (1 - up0) * tran;
			u_cc = (1 - slip) * up_t + guess * (1 - up_t);
			upperList.add(u_cc);
			up0 = up_t;

			if (pc1 > 0) {
				Double ul0 = lo * (1 - slip) / (lo * (1 - slip) + (1 - lo) * guess);
				pl1_t = ul0 + (1 - ul0) * tran;
				p_cc = (1 - slip) * pl1_t + guess * (1 - pl1_t);
				c1list.add(p_cc);
			}

			if (pc1 < 1) {
				Double ul0 = lo * slip / (lo * slip + (1 - lo) * (1 - guess));
				pl1_f = ul0 + (1 - ul0) * tran;
				p_cbc = (1 - slip) * pl1_f + guess * (1 - pl1_f);
				c0list.add(p_cbc);
			}

			if (ansList.get(i) == 0)
				lo = pl1_f;
			if (ansList.get(i) == 1)
				lo = pl1_t;

			//模型是否停止,通过循环答案集合
			boolean stop_sta = stability_stop_policy(prev_c1, prev_c0, lo, tran, guess, slip);
			//传过来的该答案未停止，两个条件有一个为true,即未停止：未停止也有可能达标（判断由系统传过来的能力值是否达标）。
			if (stop_sta && find_sta == 1) {
				//logger.info("stability stop at here " + (i + 1) + "th problem " + Math.abs(u_cc - pc1));

				//模型停止
				is_stop = true;

				//系统传来的题量
//				print(stop_sta,find_sta)
				Double epsOfOutput = Math.abs(u_cc - pc1);

				//做题能力已稳定，通过稳定性判断未达标
//				cause = AlgoMessage.STABLED_NOTMASTERED.getId();

				////小于模型计算出的偏移率为达标
				if (epsOfOutput < eps1) {
					//掌握
					resMap.put("makeIt",true);
					resMap.put("is_stop",is_stop);
					//做题能力已稳定，通过稳定性判断已达标
//					resMap.put("cause",AlgoMessage.STABLED_MASTERED.getId());
					//达标
					//logger.info("++ condition is met at here " + (i + 1));
					return resMap;
				}
				find_sta = 0;
			}
			prev_c1 = p_cc;
			prev_c0 = p_cbc;
		}
		resMap.put("makeIt",makeIt);
		resMap.put("is_stop",is_stop);
		resMap.put("cause",cause);
		return resMap;
	}

	/**
	 * 判断模型是否停止 eps = 0.01
	 * @param prevc1
	 * @param prevc0
	 * @param lo     学生最初掌握知识点的概率
	 * @param tran   学生从不会到会的转移概率
	 * @param guess  学生在不会的状态下，仍然猜对的概率
	 * @param slip   学生在会的状态下，仍然做错的概率
	 * @return
	 */
	public boolean stability_stop_policy(Double prevc1, Double prevc0, Double lo, Double tran, Double guess,
			Double slip) {
		Double eps = 0.01;
		//P(AB)=P(A)P(B|A)=P(B)P(A|B)
		Double pc1 = (1 - slip) * lo + guess * (1 - lo);
		Double ul0 = 0.0;
		Double pl1 = 0.0;
		Double p_cc = 0.0;
		Double p_cbc = 0.0;
		if (pc1 > 0) {
			ul0 = lo * (1 - slip) / (lo * (1 - slip) + (1 - lo) * guess);
			pl1 = ul0 + (1 - ul0) * tran;
			p_cc = (1 - slip) * pl1 + guess * (1 - pl1);
		}
		if (pc1 < 1) {
			ul0 = lo * slip / (lo * slip + (1 - lo) * (1 - guess));
			pl1 = ul0 + (1 - ul0) * tran;
			p_cbc = (1 - slip) * pl1 + guess * (1 - pl1);
		}
		boolean result = (Math.abs(p_cc - prevc1) < eps && Math.abs(p_cbc - prevc0) < eps);
		return result;
	}

	/**
	 * 组装返回值dto
	 * @return
	 */
	private String composeReturnResult(HashMap<String,Object> process, StopPolicyParam param) {
		StopPolicyDto stopPolicyDto = new StopPolicyDto();
		//会话编号
		if (param != null) {

		}
		//makeit ：学习是否达标，true-达标，false-未达标
		stopPolicyDto.setMastered((Boolean) process.get("makeIt"));

		//code： 错误码，0：成功，其他值：失败
//		stopPolicyDto.setCode((String) process.get("sErrors"));

		//停止及达标的原因
		stopPolicyDto.setCause((String) process.get("cause"));

		//是否停止
		stopPolicyDto.setStopped((Boolean) process.get("is_stop"));
		String result = JSON.toJSONString(stopPolicyDto);
		return result;
	}

}
