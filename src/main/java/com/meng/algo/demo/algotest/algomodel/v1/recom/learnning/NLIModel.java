package com.meng.algo.demo.algotest.algomodel.v1.recom.learnning;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Robinwb on 05/20/2017.
 * 知识点推荐学习接口
 * 描述：根据知识点之间的关系及学生对知识点掌握情况，为学生推荐知识点学习路径
 */

public class NLIModel {

	//下一知识点编号
	private String sNextLearningItem;   // the code of the next learning item recommended
	// inputs data
	private String sKMapCode;           // code name of knowledge map
	//一个图谱下所有知识点编码
	private String[] asElemCode;        // element code of all elements in the knowledge map
	private Integer[] aiLearningCounts;  // learning counts for all elements; default is 0
	private Double[] adAbility;         // student ability for all elements; default is -1
	private Set<String> asWeakElemCode;    // element code of weak elements
	private List<String> lssLearnedElemCode; // list of codes of element learned; should be empty initially
	// others
	private int iWeakElemNum;   // number of weak elements
	// constants
	private static final int MAX_TRIES = 1;
	private static final int MIN_DIFF = 2;

	private NlixParam paramNlix = new NlixParam();

	public String getResult(NlixParam nlixParam) {
			this.paramNlix = nlixParam;
			//解析传进来的参数&参数校验
			parseCheckJSONData(nlixParam);
			//验证下一个知识点
			determineNextLearningItem();


		// final output:
		return sNextLearningItem;
	}

	private void filterWeakElements(HashMap<String, Integer> hmLCountMap) {
		ArrayList<String> lssNew = new ArrayList<String>();
		if (asWeakElemCode != null && iWeakElemNum > 0) {//薄弱知识点和薄弱知识点总数量
			for (String sWeakCode : paramNlix.getWeak_nodes()) {//挑薄弱是按照索引来
				// if already tried MAX_TRIES times, assume that it is finished
				if (hmLCountMap.containsKey(sWeakCode) && hmLCountMap.get(sWeakCode) < MAX_TRIES) {// < MAX_TRIES即学习次数
					lssNew.add(sWeakCode);//lssNew：离散的薄弱知识点
				}
			}
		}
		//asWeakElemCode = (String[]) lssNew.toArray(new String[lssNew.size()]);
		asWeakElemCode = new TreeSet<>(lssNew);//modified by robin on 20170518  薄弱知识点

	}

	// determine the next learning item
	//验证下一个知识点（没学过的，薄弱的）
	private void determineNextLearningItem(){
		if (asWeakElemCode != null && iWeakElemNum > 0) {//薄弱知识点列表，薄弱知识点个数
			List<String> lssCandidates = new ArrayList<String>();
			HashMap<String, Integer> hmLCountMap = getLearnCountMap();//<知识点，知识点出现次数>
			Map<String, List<String>> hmPreKMap = paramNlix.getHmPreKMap();
			Map<String, List<String>> hmPostKMap = paramNlix.getHmPostKMap();
//			//动态知识图谱
//			if (sKMapCode.startsWith(AlgoCommon.DYNAMIC_MAP_PREFIX)) {
//				String redisKey = AlgoCommon.getDynamicKMapKey(sessionParam.getUser_id());
////				redisKey = "DYNAMIC_KMAP_6858901943927783";
//				hmPreKMap = KMapHelper.getPreKMapForDynamic(redisKey, sKMapCode, springCache);
//				hmPostKMap = KMapHelper.getPostKMapForDynamic(hmPreKMap);
//			} else {
//				//静态图谱
//				hmPreKMap = KMapHelper.getPreKMap(sKMapCode, springCache);//前置
//				hmPostKMap = KMapHelper.getPostKMap(sKMapCode, springCache);//后置
//			}

			filterWeakElements(hmLCountMap);

			// 1st step: determine the list of candidates ready to learn
			ArrayList<String> lssReadyToLearn = new ArrayList<String>();
			for (String sWeakCode : paramNlix.getWeak_nodes()) {//
				if (hmLCountMap.containsKey(sWeakCode) && hmLCountMap.get(sWeakCode) < MAX_TRIES) {//薄弱知识点没学过，没有异常的情况下，都会进来
					boolean bNotReady = false;
					if (hmPreKMap.containsKey(sWeakCode)) {//异常判断
						List<String> lssPreTmp = hmPreKMap.get(sWeakCode);//根据薄弱知识点取薄弱的前置知识点集合
						for (String sPreTmp : lssPreTmp) {//离散知识图谱或(没有前置知识点的薄弱知识点)不走此循环 薄弱知识点的前置知识点
							if (Arrays.asList(paramNlix.getWeak_nodes()).contains(sPreTmp)) {//如果某个薄弱知识点的前置在系统传入的薄弱知识点里（避免返回的薄弱知识点重复）
								bNotReady = true;
								break;
							}
						}
					}
					if (!bNotReady) {//如果该单个薄弱知识点没有前置知识点
						lssReadyToLearn.add(sWeakCode);//薄弱知识点集合
					}
				}
			}

			// 2nd step: determine the list of candidates and select one from the candidates
			if (lssReadyToLearn.size() > 0) {//总共的薄弱知识点
				for (String sReadyToLearn : lssReadyToLearn) {
					if (hmLCountMap.containsKey(sReadyToLearn)) {//判断单个薄弱知识点是否在所有的知识点集合里
						if (!(hmLCountMap.get(sReadyToLearn) == 1 && isRecentlyLearned(sReadyToLearn))) {//离散走次判断，判断要推的知识点是否为学过的知识点
							lssCandidates.add(sReadyToLearn);
						}
					}
				}
				if (lssCandidates.size() == 1) {//薄弱知识点个数
					//todo 1 ""
					sNextLearningItem = lssCandidates.get(0);
				}
				//size = 2
				if (lssCandidates.size() > 1) {//薄弱知识点
					Collections.shuffle(lssCandidates);

					int iMaxNum = 0;
					boolean run = true;
					//遍历所有薄弱知识点
					for (String sCandidate : lssCandidates) {
						if (hmPostKMap.containsKey(sCandidate)) {
							Set<String> sCandidateS = new HashSet<>();
							sCandidateS.add(sCandidate);//存薄弱
							Set<String> result = new HashSet<>();
							Set<String> allHouZhiSet = getPro(hmPostKMap, sCandidateS, result);

							//返回知识点直接加间接后置知识点的个数
							int iSizeTmp = allHouZhiSet.size();

							//所有后置知识点个数，有直接前置知识点
							if(iSizeTmp != 0||hmPreKMap.get(sCandidate).size()!=0){

							if (iSizeTmp >= iMaxNum) {
								// if there all sizes are zeros, choose the last in the list
								iMaxNum = iSizeTmp;

									sNextLearningItem = sCandidate;
									run = false;
							}
							}
						}
					}
					if(run) {
						//挑选没有直接前置知识点的,在上面的循环走完执行
						for (String sCandidate : lssCandidates) {
							if (hmPreKMap.get(sCandidate) == null || hmPreKMap.get(sCandidate).size() == 0) {
								sNextLearningItem = sCandidate;
							}
						}
					}


				}

				if (lssCandidates.size() == 0) {
					// need to continue select
					int iSizeOfLearned = lssLearnedElemCode.size();
					if (iSizeOfLearned > 1) {
						String sTmp = lssLearnedElemCode.get(iSizeOfLearned - 2);
						if (lssReadyToLearn.contains(sTmp)) {
							//todo 3
							sNextLearningItem = sTmp;
						}
					}
					//todo 4
					if (sNextLearningItem.isEmpty() && iSizeOfLearned > 0) {
						String sTmp = lssLearnedElemCode.get(iSizeOfLearned - 1);
						if (lssReadyToLearn.contains(sTmp)) {
							//todo 5
							sNextLearningItem = sTmp;
						}
					}
				}
			}

		}

	}

	//hmPostKMap:所有后置的map sCandidateS:需要求后置的薄弱知识点 result:所有所求后置的集合
	private Set<String> getPro(Map<String, List<String>> hmPostKMap,Set<String> sCandidateS,Set<String> result)
	{
		Set<String> proSetAll = new HashSet<>();
		for (String sCandidate : sCandidateS) {
			List<String> prolist = hmPostKMap.get(sCandidate);
			for (String sCandidatePro : prolist) {
				proSetAll.add(sCandidatePro);
			}
		}
		int i = result.size();
		for (String string : proSetAll) {
			result.add(string);
		}
		if(i == result.size())
		{
			return result;
		}else{
			return getPro(hmPostKMap, proSetAll, result);
		}
	}

	private boolean isRecentlyLearned(String sElem) {
		boolean bRecentlyLearned = false;
		int iCount = lssLearnedElemCode.size();
		if (iCount > 0) {
			int i = iCount;
			int iDiff = -1;
			for (String sLearned : lssLearnedElemCode) {
				i--;
				if (sElem.equals(sLearned)) {
					iDiff = i;
					break;   // because we know sElem is only learned once
				}
			}
			if (iDiff > -1 && iDiff < MIN_DIFF) {
				bRecentlyLearned = true;
			}
		}
		return bRecentlyLearned;
	}

	/*
	 *
	 */
	private HashMap<String, Integer> getLearnCountMap() {
		HashMap<String, Integer> hmRtn = new HashMap<String, Integer>();
		if (asElemCode != null && aiLearningCounts != null && aiLearningCounts.length > 0 && aiLearningCounts.length == asElemCode.length) {//aiLearningCounts：是否有前后置，知识点出现次数<知识点，次数>
			for (int i = 0; i < asElemCode.length; i++) {//遍历整个图谱所有知识点编号
				hmRtn.put(asElemCode[i], aiLearningCounts[i]);//key:知识点 value:学习次数
			}
		}
		return hmRtn;
	}

	/**
	 * analyse input JSON data
	 *
	 * @throws Exception
	 */
	private void parseCheckJSONData(NlixParam nlixParam){
		/*******************************************************/
		//薄弱知识点列表
		asWeakElemCode = new HashSet(nlixParam.getWeak_nodes());

		//验证知识点集合信息
		List<String> entities = new ArrayList<>(nlixParam.getNodesWithCounts().keySet());//返回知识图谱下所有知识点
		asElemCode = new String[entities.size()];//整个图谱的知识点编号
		aiLearningCounts = new Integer[entities.size()];//整个图谱的知识点个数
		adAbility = new Double[entities.size()];//整个图谱的单个知识点能力值
		for (int i = 0; i < entities.size(); i++) {
			asElemCode[i] = entities.get(i);
			aiLearningCounts[i] = nlixParam.getNodesWithCounts().get(entities.get(i));
			adAbility[i] = 0.5;
		}
		Set<String> sLearnedElements =new HashSet<>(nlixParam.getLearned_nodes());//学过的知识点集合，第一次为空
		//判断学习过知识点
		if (sLearnedElements != null && sLearnedElements.size() != 0) {
			//数组转换成学习过的知识点List
			lssLearnedElemCode = new ArrayList<>(sLearnedElements);//学过的知识点
		}
		//薄弱知识点列表
		if (paramNlix.getWeak_nodes() != null) {
			//薄弱知识点数量
			iWeakElemNum = paramNlix.getWeak_nodes().size();
		}
	}
}
