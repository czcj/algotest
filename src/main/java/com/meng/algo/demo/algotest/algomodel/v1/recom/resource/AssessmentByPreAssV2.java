package com.meng.algo.demo.algotest.algomodel.v1.recom.resource;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by WeiCui on 09/11/2015. modified by robinwb on 09/22/2016
 */

public class AssessmentByPreAssV2 {

	private static Logger logger = LoggerFactory.getLogger(AssessmentByPreAssV2.class);
	private volatile static AssessmentByPreAssV2 assessment;
	public AssessmentByPreAssV2(){}

	/**
	 * 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
	 * @return
	 */
	public static AssessmentByPreAssV2 getSingleton() {
		return new AssessmentByPreAssV2();
	}


	//当前能力值与前一次能力值的差值
	Double abiDiff = 0.0;
	int iSizeOfOut = 1; // default is 1; must be at least one output question

	int iNumOfQuestsInAssm = 0;
	int iNumOfQuestsInBank = 0;

	Map<String, Double> hmQuestProbs = new HashMap<String, Double>();

	List<Double> adDifficulties= new ArrayList<>();
	List<String> aiQuestIds = new ArrayList<>();

	List<String> aiRecomQuestIds;

	double dStudentAbility = -1.0;

	protected int totalLevel =9	; // 能力值级别数量 默认9个级别

	Double preDiff = 1.0;

	Integer preAnswer = 1;

	// 难度级别翻译
	public double tranLevel(double orgLevel) {
		if (totalLevel == 3) {
			return 0.2 + 0.3 * (orgLevel - 1);
		} else if(totalLevel == 9) {
			return 0.1 * orgLevel;
		}else{
			return 0.01*orgLevel;
		}
	}

	public String getResult(AssessmentParam assessmentParam) {
		String result = null;
		try {
			//取出知识点难度对应map
			List<String> question_ids = assessmentParam.getQuestion_ids();
			List<Double> question_difficulties = assessmentParam.getQuestion_difficulties();
			HashMap<String, Double> quesDiffMap = new HashMap<>();
			if(question_ids != null && question_ids.size() > 0){
				for(int i=0;i<question_ids.size();i++){
					if(question_difficulties != null && question_difficulties.get(i) > 0){
						quesDiffMap.put(question_ids.get(i),question_difficulties.get(i));
					}else {
						return null;
					}
				}
			}else{
				return null;
			}
			//根据上一题难度和对错删选题目范围
			if(assessmentParam.getPreDiff() != null ){
				preDiff = assessmentParam.getPreDiff();
			}
			if(assessmentParam.getPreAnswer() != null){
				preAnswer = assessmentParam.getPreAnswer();
			}
			//优先推送的题目集合
			ArrayList<String> qids = new ArrayList<>();
			ArrayList<Double> qdiffs = new ArrayList<>();

			//容错推送的题目集合
//			ArrayList<String> qids2 = new ArrayList<>();
//			ArrayList<Double> qdiffs2 = new ArrayList<>();

			if(0 == preAnswer){
				//答题错误取难度小于或等于前一题难度的题
				quesDiffMap.forEach((qid,qdiff) ->{
					if(qdiff <= preDiff){
						qids.add(qid);
						qdiffs.add(qdiff);
					}else{
//						qids2.add(qid);
//						qdiffs2.add(qdiff);
					}
				});
			}else if(1 == preAnswer){
				//答题正确取难度大于或等于前一题难度的题
				quesDiffMap.forEach((qid,qdiff) ->{
					if(qdiff >= preDiff){
						qids.add(qid);
						qdiffs.add(qdiff);
					}else{
//						qids2.add(qid);
//						qdiffs2.add(qdiff);
					}
				});
			}
			//优先将符合题目难度的题推出
			if(qids.size() > 0 && qdiffs.size()> 0){
				assessmentParam.setQuestion_ids(qids);
				assessmentParam.setQuestion_difficulties(qdiffs);
				//将参数赋值给类变量进行推题模型计算
				/**
				 * 增加返回值当前模型推题map
				 */
				Map<String, Double> stringDoubleMap = parseJSONData(assessmentParam);
				if (adDifficulties != null) {
					for (int i = 0; i < adDifficulties.size(); i++) {
						adDifficulties.set(i, tranLevel(adDifficulties.get(i)));
					}
				} else {
					adDifficulties = new ArrayList<>();
				}
				/**
				 * 模型计算
				 */
				GetRecommendedQuestions(stringDoubleMap);
				result = composeReturnResult();
			}
//			AssessmentDto assessmentDto = JSON.parseObject(result, AssessmentDto.class);
//			if(qids.size() < assessmentParam.getAssessment_size()){
//				if(assessmentDto != null && assessmentDto.getQuestions()!=null){
//					int size = assessmentParam.getAssessment_size() - assessmentDto.getQuestions().size();
//					assessmentParam.setAssessment_size(size);
//				}
//				//当难度值符合条件的题目不足时
//				assessmentParam.setQuestion_ids(qids2);
//				assessmentParam.setQuestion_difficulties(qdiffs2);
//				//将参数赋值给类变量进行推题模型计算
//				/**
//				 * 增加返回值当前模型推题map
//				 */
//				Map<String, Double> stringDoubleMap = parseJSONData(assessmentParam);
//				if (adDifficulties != null) {
//					for (int i = 0; i < adDifficulties.size(); i++) {
//						adDifficulties.set(i, tranLevel(adDifficulties.get(i)));
//					}
//				} else {
//					adDifficulties = new ArrayList<>();
//				}
//				/**
//				 * 模型计算
//				 */
//				GetRecommendedQuestions(stringDoubleMap);
//				result = composeReturnResult();
//				AssessmentDto assessmentDtoErr = JSON.parseObject(result, AssessmentDto.class);
//				if(assessmentDto != null && assessmentDto.getQuestions() != null && assessmentDto.getQuestions().size() > 0){
//					assessmentDtoErr.getQuestions().addAll(assessmentDto.getQuestions());
//				}
//				result = JSON.toJSONString(assessmentDtoErr);
//			}else{
//				result = JSON.toJSONString(assessmentDto);
//			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	public List<String> GetRecommendedQuestions(Map<String, Double> quesDiffMap) {

		iNumOfQuestsInAssm = iSizeOfOut;

		double dBandwidth = 0.5 * Math.pow(iNumOfQuestsInBank, -0.2);
		for (int i = 0; i < iNumOfQuestsInBank; i++) {
			hmQuestProbs.put(aiQuestIds.get(i), Math
					.exp(-Math.pow(adDifficulties.get(i) - dStudentAbility, 2) / (2 * Math.pow(dBandwidth, 2))));
		}
		aiRecomQuestIds = getRandomSelection(iNumOfQuestsInAssm, hmQuestProbs ,quesDiffMap);
		return aiRecomQuestIds;
	}

	private Map<Double, List<NodeString>> getGroupHashMap(Map<String, Double> hmQuestionProbs,Map<String, Double> quesDiffMap) {
		Map<Double, List<NodeString>> hashMaps = new TreeMap<>(new Comparator<Double>() {
			@Override
			public int compare(Double o1, Double o2) {
				return o2.compareTo(o1);
			}

		});
		for (Entry<String, Double> entity : hmQuestionProbs.entrySet()) {
			List<NodeString> tempList = hashMaps.get(entity.getValue());
			NodeString nod = new NodeString(entity.getKey(), entity.getValue(),quesDiffMap.get(entity.getKey()));
			/* 如果取不到数据,那么直接new一个空的ArrayList **/
			if (tempList == null) {
				tempList = new ArrayList<>();
				tempList.add(nod);
				hashMaps.put(entity.getValue(), tempList);
			} else {
				/* 某个sku之前已经存放过了,则直接追加数据到原来的List里 **/
				/**
				 * 对这个题目集合进行排序，取题目难度与上一题难度的绝对值进行排序
				 */
				tempList.add(nod);
				Collections.shuffle(tempList);
				Collections.sort(tempList, new Comparator<NodeString>() {
					@Override
					public int compare(NodeString o1, NodeString o2) {
						double v = Math.abs(preDiff - o1.getDiff()) - Math.abs(preDiff - o2.getDiff());
						if(v > 0){
							return 1;
						}else if(v < 0){
							return -1;
						}else{
							return 0;
						}
					}
				});
			}
		}
		return hashMaps;
	}

	public List<String> getRandomSelection(int iRtnQuestNum, Map<String, Double> hmQuestionProbs,Map<String, Double> quesDiffMap) {
		List<String> aiRtnQuestIds = null;
		if (hmQuestionProbs != null) {
			Random random = new Random();
			aiRtnQuestIds = new ArrayList<>();// new String[iRtnQuestNum];
			Map<Double, List<NodeString>> groupNodeString = getGroupHashMap(hmQuestionProbs,quesDiffMap);
			int count = 0;
			lableB: for (Entry<Double, List<NodeString>> entity : groupNodeString.entrySet()) {

				List<NodeString> listString = entity.getValue();
				for (int i = 0;i< listString.size();i++) {
					if (count < iRtnQuestNum) {
//						int index = random.nextInt(listString.size());
//            aiRtnQuestIds.add(listString.get(index).getKey());
//            groupNodeString.get(entity.getKey()).remove(index);
						aiRtnQuestIds.add(listString.get(i).getKey());
//            groupNodeString.get(entity.getKey()).remove(0);
						count++;
					} else {
						break lableB;
					}
				}
			}
		}
		return aiRtnQuestIds;
	}

	private Map<String,Double> parseJSONData(AssessmentParam assessmentParam) {

		dStudentAbility = assessmentParam.getAbility();

		if (dStudentAbility <= 0) {
			dStudentAbility = 0.5; // 默认为0.5 （中等）
		}
		aiQuestIds = assessmentParam.getQuestion_ids();

		if (assessmentParam.getTotalLevel() != null) {
			try {
				totalLevel = assessmentParam.getTotalLevel();
			} catch (Exception e) {
				totalLevel = 9;
				logger.error(e.getMessage());
			}
		}
//		adDifficulties = assessmentParam.getQuestion_difficulties();
		for(Double diff :assessmentParam.getQuestion_difficulties()){
			adDifficulties.add(diff);
		}
//		Collections.copy(adDifficulties,assessmentParam.getQuestion_difficulties());
		//添加题目与难度的map
		HashMap<String, Double> quesDiffMap = new HashMap<>();
		if (aiQuestIds != null && aiQuestIds.size() > 0) {
			iNumOfQuestsInBank = aiQuestIds.size();
			for(int i = 0;i<assessmentParam.getQuestion_ids().size();i++){
				Double diff;
				try {
					diff = assessmentParam.getQuestion_difficulties().get(i);
				} catch (Exception e) {
					diff = null;
				}
				quesDiffMap.put(assessmentParam.getQuestion_ids().get(i),diff);
			}
		}

		iSizeOfOut = Integer.valueOf((assessmentParam.getAssessment_size() == null || "".equals(assessmentParam.getAssessment_size())) ? "1" : (assessmentParam.getAssessment_size() + ""));

		return quesDiffMap;
	}

	private String composeReturnResult() {
		AssessmentDto dto = new AssessmentDto(aiRecomQuestIds);
		for(String aa:aiRecomQuestIds){
			if(!aiQuestIds.contains(aa)){
				//logger.info("==========产生异常不存在的题目id："+aiRecomQuestIds);
			}

		}
//		assessment = new Assessment();


		iSizeOfOut = 1; // default is 1; must be at least one output question

		iNumOfQuestsInAssm = 0;
		iNumOfQuestsInBank = 0;

		hmQuestProbs = new HashMap<String, Double>();

		adDifficulties= new ArrayList<>();
		aiQuestIds = new ArrayList<>();

		aiRecomQuestIds = new ArrayList<>();

		dStudentAbility = -1.0;
		totalLevel = 9; // 能力值级别数量 默认3个级别
		return JSON.toJSONString(dto);
	}


}
