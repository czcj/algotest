/**
 * 
 */
package com.meng.algo.demo.algotest.algomodel.v1.util;


import java.util.List;

/**
 * @author WeiCui
 *
 * 2016.02.25 1) added hyperparameters for practice questions alpha = beta = 0.5
 * 			  2) added a new parameter: likelihood wight parameter
 */


public class IRTModel 
{

	public IRTModel()
	{

	}

	// get response from 1PL-IRT model
	// @params: ability value, difficulty value
	protected static double OneIRTFunction(double dTheta, double dDifficulty)
	{
		return Math.pow(1.0 + Math.exp(dDifficulty - dTheta), -1);
	}

	// get default log likelihood function
	// used for the first time estimating latent ability
	public static double DefaultLogLK(double dTheta, boolean bAssessment)
	{
		if (bAssessment == true)
		{
			return LogLikeliHoodFunction(Constants.ALPHA_ASSM - 1, Constants.BETA_ASSM - 1, IRTModel.OneIRTFunction(dTheta, Constants.DFT_DIFFICULTY));
		}
		else
		{
			return LogLikeliHoodFunction(Constants.ALPHA_PRAC - 1, Constants.BETA_PRAC - 1, IRTModel.OneIRTFunction(dTheta, Constants.DFT_DIFFICULTY));
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
	public static double LogJointLikeliHoodFunction(double dTheta, List<Double> adDifficulty, List<Double> adScore, double dInitVal, double dWeightLh)
	{
		double dRsp = 0; 
		double dBetaDistVal = dInitVal;
		for(int i =0 ; i < adDifficulty.size(); i++)
		{
			if((i < adScore.size()) && adScore.get(i)>=0)
			{
				dRsp = OneIRTFunction(dTheta, adDifficulty.get(i));
				dBetaDistVal += dWeightLh * LogLikeliHoodFunction(adScore.get(i), 1-adScore.get(i), dRsp);
			}
		}
		// return dBetaDistVal;   // return log value
		return NumberHelper.RoundDecimal(Math.pow(Math.E, dBetaDistVal), 5);
	}

}
