/**
 *
 */
package com.meng.algo.demo.algotest.algomodel.v1.util;

/**
 * @author WeiCui
 *         2016-02-25 added a second threshold for stopping criteria: the total number of questions asked should no more than 25
 */

public final class Constants {





	/*
	 * Parameters for practice questions
	 */
	public static final double ALPHA_PRAC = 1.5;
	public static final double BETA_PRAC = 1.5;
	public static final double LH_WEIGHT_PRAC = 0.5;

	/*
	 * Parameters for assessment questions
	 */
	public static final double ALPHA_ASSM = 1.7;
	public static final double BETA_ASSM = 1.3;
	public static final double LH_WEIGHT_ASSM = 1;

	public static final double DFT_DIFFICULTY = 0;

	public static final int NUMABILITIES = 99;

}
