/**
 * 
 */
package com.meng.algo.demo.algotest.algomodel.v2.Ability;

import com.meng.algo.demo.algotest.algomodel.v1.util.Constants;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author Tao
 *
 * 2018.07.26 
 * 1) cited IRTModel from original file
 * 2) added a new parameter relativeTime into model
 */


public class IRTModelWithTime {

	public IRTModelWithTime() {

	}

	// get response from 1PL-IRT model
	// @params: ability value, difficulty value, related time value
	protected static double OneIRTFunction(double dTheta, double dDifficulty, double dRelatedTime) {
		double tempParam = (dDifficulty - dTheta) / dRelatedTime;
		double result = Math.pow(1.0 + Math.exp(tempParam), -1);
		if(1 == result){
			result=0.99;
		}
		return result ;
	}

	// get default log likelihood function
	// used for the first time estimating latent ability
	protected static double DefaultLogLK(double dTheta, boolean bAssessment)
	{
		if (bAssessment == true)
		{
			return LogLikeliHoodFunction(Constants.ALPHA_ASSM - 1, Constants.BETA_ASSM - 1, IRTModelWithTime.OneIRTFunction(dTheta, Constants.DFT_DIFFICULTY, 1));
		}
		else
		{
			return LogLikeliHoodFunction(Constants.ALPHA_PRAC - 1, Constants.BETA_PRAC - 1, IRTModelWithTime.OneIRTFunction(dTheta, Constants.DFT_DIFFICULTY, 1));
		}
	}
	
	// get log likelihood function
	// @params: alpha, beta, probability of correct response
	protected static double LogLikeliHoodFunction(double dExpA, double dExpB, double dCorrectProb)
	{
		return dExpA * Math.log(dCorrectProb) + dExpB * Math.log(1-dCorrectProb);
	}
	
	// get accumulated 
	// score must be zero or one
	// dInitVal only be assigned when first time estimating ability; will be zero afterwards in updates
	protected static double LogJointLikeliHoodFunction(double dTheta, List<Double> adDifficulty, List<Double> adScore, double dInitVal, double dWeightLh, List<Double> adRelateTime)
	{
		double dRsp = 0; 
		double dBetaDistVal = dInitVal;
		for(int i =0 ; i < adDifficulty.size(); i++)
		{
			if((i < adScore.size()) && adScore.get(i) > 0) {
				dRsp = OneIRTFunction(dTheta, adDifficulty.get(i), adRelateTime.get(i));
				dBetaDistVal += dWeightLh * LogLikeliHoodFunction(adScore.get(i), 1-adScore.get(i), dRsp);
			} else {
				dRsp = OneIRTFunction(dTheta, adDifficulty.get(i), 1.0);
				dBetaDistVal += dWeightLh * LogLikeliHoodFunction(adScore.get(i), 1-adScore.get(i), dRsp);
			}

		}
		// return dBetaDistVal;   // return log value
		return IRTModelWithTime.RoundDecimal(Math.pow(Math.E, dBetaDistVal), 5);
	}
	public static double RoundDecimal(double value, int num)
	{

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(num, RoundingMode.HALF_UP);
		return bd.doubleValue();  //.toString();
	}
}
