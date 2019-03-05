package com.meng.algo.demo.algotest.algomodel.v1.recom.resource;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by WeiCui on 09/11/2015. modified by robinwb on 09/22/2016
 */

public class Assessment  {

	private static Logger logger = LoggerFactory.getLogger(Assessment.class);
	private volatile static Assessment assessment;
	public Assessment (){}

	/**
	 * 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
	 * @return
	 */
	public static Assessment getSingleton() {
//		if (assessment == null) {
//			synchronized (Assessment.class) {
//				if (assessment == null) {
//					assessment = new Assessment();
//				}
//			}
//		}
		return new Assessment();
	}


	int iSizeOfOut = 1; // default is 1; must be at least one output question

	int iNumOfQuestsInAssm = 0;
	int iNumOfQuestsInBank = 0;

	Map<String, Double> hmQuestProbs = new HashMap<String, Double>();

	List<Double> adDifficulties= new ArrayList<>();
	List<String> aiQuestIds = new ArrayList<>();

	List<String> aiRecomQuestIds;

	double dStudentAbility = -1.0;

	protected int totalLevel =9	; // 能力值级别数量 默认9个级别


	// 难度级别翻译
	public double tranLevel(double orgLevel) {
		if (totalLevel == 3) {
			return 0.2 + 0.3 * (orgLevel - 1);
		} else if(totalLevel == 9) {
			return 0.1 * orgLevel;
		} else{
			return 0.01*orgLevel;
		}
	}

	public String getResult(AssessmentParam assessmentParam) {
		String result = null;
		try {
			parseJSONData(assessmentParam);
//			System.out.println(JSON.toJSONString(assessmentParam));
			// levelTransiton
			// added by robin for transforming the difficulty value
			if (adDifficulties != null) {
				for (int i = 0; i < adDifficulties.size(); i++) {
					adDifficulties.set(i, tranLevel(adDifficulties.get(i)));
				}
			} else {
				adDifficulties = new ArrayList<>();
			}
			GetRecommendedQuestions();
			result = composeReturnResult();
		} catch (Exception e) {
			 logger.error(e.getMessage());
		}
		return result;
	}

	public List<String> GetRecommendedQuestions() {

		iNumOfQuestsInAssm = iSizeOfOut;

		double dBandwidth = 0.5 * Math.pow(iNumOfQuestsInBank, -0.2);
		for (int i = 0; i < iNumOfQuestsInBank; i++) {
			hmQuestProbs.put(aiQuestIds.get(i), Math
					.exp(-Math.pow(adDifficulties.get(i) - dStudentAbility, 2) / (2 * Math.pow(dBandwidth, 2))));
		}
		aiRecomQuestIds = getRandomSelection(iNumOfQuestsInAssm, hmQuestProbs);
		return aiRecomQuestIds;
	}

//	static class NodeString {
//
//		private String key;
//		private Double value;
//
//		public NodeString() {
//
//		}
//
//		public NodeString(String key, Double value) {
//			this.key = key;
//			this.value = value;
//		}
//
//		/**
//		 * key.
//		 *
//		 * @return the key
//		 */
//		public String getKey() {
//			return key;
//		}
//
//		/**
//		 * value.
//		 *
//		 * @return the value
//		 */
//		public Double getValue() {
//			return value;
//		}
//
//		/**
//		 * @param key the key to set
//		 */
//		public void setKey(String key) {
//			this.key = key;
//		}
//
//		/**
//		 * @param value the value to set
//		 */
//		public void setValue(Double value) {
//			this.value = value;
//		}
//	}

	private static Map<Double, List<NodeString>> getGroupHashMap(Map<String, Double> hmQuestionProbs) {
		Map<Double, List<NodeString>> hashMaps = new TreeMap<>(new Comparator<Double>() {
			@Override
			public int compare(Double o1, Double o2) {
				return o2.compareTo(o1);
			}

		});
		for (Entry<String, Double> entity : hmQuestionProbs.entrySet()) {
			List<NodeString> tempList = hashMaps.get(entity.getValue());
			NodeString nod = new NodeString(entity.getKey(), entity.getValue());
			/* 如果取不到数据,那么直接new一个空的ArrayList **/
			if (tempList == null) {
				tempList = new CopyOnWriteArrayList<>();
				tempList.add(nod);
				hashMaps.put(entity.getValue(), tempList);
			} else {
				/* 某个sku之前已经存放过了,则直接追加数据到原来的List里 **/
				tempList.add(nod);
			}

		}
		return hashMaps;
	}

	public static List<String> getRandomSelection(int iRtnQuestNum, Map<String, Double> hmQuestionProbs) {
		List<String> aiRtnQuestIds = null;
		if (hmQuestionProbs != null) {
			Random random = new Random();
			aiRtnQuestIds = new ArrayList<>();// new String[iRtnQuestNum];
			Map<Double, List<NodeString>> groupNodeString = getGroupHashMap(hmQuestionProbs);
			int count = 0;
			lableB: for (Entry<Double, List<NodeString>> entity : groupNodeString.entrySet()) {

				List<NodeString> listString = entity.getValue();
				for (NodeString node : listString) {
					if (count < iRtnQuestNum) {
						int index = random.nextInt(listString.size());
						// aiRtnQuestIds[count] = listString.get(index).getKey();
						aiRtnQuestIds.add(listString.get(index).getKey());
						groupNodeString.get(entity.getKey()).remove(index);
						count++;
					} else {
						break lableB;
					}
				}
			}
		}
		return aiRtnQuestIds;
	}

	private void parseJSONData(AssessmentParam assessmentParam) {

		dStudentAbility = assessmentParam.getAbility();

		if (dStudentAbility <= 0) {
			dStudentAbility = 0.5; // 默认为0.5 （中等）
		}
		aiQuestIds = assessmentParam.getQuestion_ids();

		if (assessmentParam.getTotalLevel() != null) {
			try {
				totalLevel = assessmentParam.getTotalLevel();
			} catch (Exception e) {
				totalLevel = 3;
				 logger.error(e.getMessage());
			}
		}
		for(Double dif : assessmentParam.getQuestion_difficulties()){
			adDifficulties.add(dif);
		}

		if (aiQuestIds != null && aiQuestIds.size() > 0) {
			iNumOfQuestsInBank = aiQuestIds.size();
		}

		iSizeOfOut = Integer.valueOf((assessmentParam.getAssessment_size() == null || "".equals(assessmentParam.getAssessment_size())) ? "1" : (assessmentParam.getAssessment_size() + ""));
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

		  totalLevel = 3; // 能力值级别数量 默认3个级别





		return JSON.toJSONString(dto);
	}


}
