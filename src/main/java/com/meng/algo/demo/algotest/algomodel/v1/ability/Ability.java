/**
 *
 */

package com.meng.algo.demo.algotest.algomodel.v1.ability;

import com.meng.algo.demo.algotest.algomodel.enums.ModelVersion;
import com.meng.algo.demo.algotest.algomodel.v1.util.Constants;
import com.meng.algo.demo.algotest.algomodel.v1.util.IRTModel;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author WeiCui 2015.12.21 remove the softening mechanism, because the ability value becomes very
 * small after wrong answers should consider dynamic softening in next improvement
 * 2016.02.25 1) added hyperparameters for practice questions alpha = beta = 0.5 2) added a
 * new parameter: likelihood wight parameter 2016.02.28 adjust the difficulty level from
 * [1,2,3] to [-1,0,1]
 */

public class Ability {

    private static Logger logger = LoggerFactory.getLogger(Ability.class);
    private volatile static Ability ability;

    public Ability() {
    }

    public static String version = ModelVersion.ABI_V1.getModelVersion();

    /**
     * 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
     *
     * @return
     */
    public static Ability getSingleton() {
//        if (ability == null) {
//            synchronized (Ability.class) {
//                if (ability == null) {
//                    ability = new Ability();
//                }
//            }
//        }
        return  new Ability();
    }

    private static final double INITPROB = -10000000.0;

    private int iContentType = 1; // content type: assessment question (1), practice question (2),

    private List<Double> adDifficulty;
    private List<Double> adScore;

    private boolean bFirst = true; // the first time to estimate ability

    private List<Double> adMaxAPosterior = new ArrayList<>();
    private List<Double> adOldMaxAPosterior;

    private int iMostLikely;
    private double dMostLikely = -1;
    private double dMostLikelyProb = INITPROB; // #Maximum A Poteria for probability of correct response#
    protected int totalLevel = 9; // 能力值级别数量 默认9个级别


//	public Ability(Integer total_level) {
//		this.totalLevel = total_level;
//	}

    // used by main system
    public AbilityDto getResult(AbilityParam abilityParam) {
        AbilityDto abilityDto = new AbilityDto();
        try {
            parseJSONData(abilityParam);
            double dRtn = getMostLikely();
            abilityDto = new AbilityDto(String.valueOf(dRtn), adMaxAPosterior, dMostLikelyProb);
            abilityDto.setVersion(version);
            adDifficulty = new ArrayList<>();
            adScore = new ArrayList<>();
            adMaxAPosterior = new ArrayList<>();
            adOldMaxAPosterior = new ArrayList<>();
            bFirst = true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return abilityDto;
    }

    private double DiscreteAbility(int iAbility) {
        return (double) iAbility / (Constants.NUMABILITIES + 1); // Discrete points of ability
    }

    // get log ability value
    private double LogAbility(double dTheta) {
        return Math.log(dTheta / (1 - dTheta));
        // return dTheta;
    }

    void checkIsFirstEstimation() {
        // check if it is the first time
        // if not, modify bFirst to false, and initialize adOldMaxAPosterior
        if (adOldMaxAPosterior != null && adOldMaxAPosterior.size() == Constants.NUMABILITIES) {
            bFirst = false;
        }
    }

    // to calculate joint probability function for each ability
    private List<Double> calcPosteriorLogVal() {
        double dTheta = 0;
        double dInitVal = 0;
        boolean bAssmQuest = iContentType == 1;
        for (int i = 0; i < Constants.NUMABILITIES; i++) {
            dTheta = LogAbility(DiscreteAbility(i + 1));
            if (bFirst == true) {
                dInitVal = IRTModel.DefaultLogLK(dTheta, bAssmQuest); // initial log likelihood
            }

            double dLhWeight = Constants.LH_WEIGHT_ASSM;
            if (!bAssmQuest) {
                dLhWeight = Constants.LH_WEIGHT_PRAC;
            }
            adMaxAPosterior.add(i, IRTModel
                    .LogJointLikeliHoodFunction(dTheta, adDifficulty, adScore, dInitVal, dLhWeight));

        }
        if (bFirst == false) {
            softenOldMaxAPosterior();
        }
        return adMaxAPosterior;
    }

    // update ability when new evidence comes
    private void softenOldMaxAPosterior() {
        for (int i = 0; i < Constants.NUMABILITIES; i++) {
            try {
                adMaxAPosterior.set(i, adMaxAPosterior.get(i) * adOldMaxAPosterior.get(i)); // not to soften
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    // get estimated ability value
    private double getMostLikely() {
        calcPosteriorLogVal();
        dMostLikelyProb = INITPROB;
        for (int i = 0; i < Constants.NUMABILITIES; i++) {
            if (dMostLikelyProb < adMaxAPosterior.get(i)) {
                dMostLikelyProb = adMaxAPosterior.get(i);
                iMostLikely = i + 1;
            }
        }
        dMostLikelyProb = dMostLikelyProb;
        // dLogMostLikely = LogAbility(DiscreteAbility(iMostLikely)); // log ability
        dMostLikely = DiscreteAbility(iMostLikely); // decimal ability

        return dMostLikely;
    }

    private void parseJSONData(AbilityParam abilityParam) {

        try {
            adDifficulty = new ArrayList<Double>(
                    abilityParam.getDifficulty());// (double[]) mInputData.get("difficulty");
            adScore = abilityParam.getScore();// (double[]) mInputData.get("score");
            adOldMaxAPosterior = abilityParam.getLikelihood();// (double[])
            // mInputData.get("likelihood");
            if (abilityParam.getTotalLevel() != null) {
                if (abilityParam.getTotalLevel() > 0) {
                    totalLevel = abilityParam.getTotalLevel();
                }
            }

            iContentType = abilityParam.getType().get(0).intValue(); // 1: assessment; 2: practice;
            /*
             * adjust difficulty values difficulty level should be -1, 0, 1, easy, moderate,
             * difficult
             */
            if (adDifficulty != null && adDifficulty.size() > 0) {
                for (int i = 0; i < adDifficulty.size(); i++) {
                    // adDifficulty[i] = adDifficulty[i] - 2;
                    // adDifficulty[i]=-1+0.25*(adDifficulty[i]-1);
                    adDifficulty.set(i, tranDiff(adDifficulty.get(i)));
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        checkIsFirstEstimation();

    }

    /**
     * 难度翻译 题目难度保持在[-1,1]
     *
     * @param diff
     * @return
     */
    private double tranDiff(Double diff) {
        if (totalLevel == 3 && diff <= 3) { //题目难度3个级别
            return diff - 2;
        }else if(totalLevel == 9 && diff <= 9){//题目难度9个级别
            return -1 + 0.25 * (diff - 1);
        } else {//题目难度100个级别
            return -1 + 0.02 * (diff - 1);
        }
    }

    private AbilityDto composeReturnResult(String sAbility) {
//		Arrays.asList(adMaxAPosterior);
        AbilityDto dto = new AbilityDto(sAbility, adMaxAPosterior, dMostLikelyProb);
        dto.setVersion(Ability.version);
        return dto;
    }
}
