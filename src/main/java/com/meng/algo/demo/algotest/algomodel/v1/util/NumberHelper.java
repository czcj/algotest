package com.meng.algo.demo.algotest.algomodel.v1.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Formatter;


/**
 * Created by Apple on 11/28/2015.
 */



public final class NumberHelper
{

    public NumberHelper()
    {
    }

    /**
     * ʹ��BigDecimal������С�������λ
     */
    public static double RoundDecimal(double value, int num)
    {

      BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(num, RoundingMode.HALF_UP);
        return bd.doubleValue();  //.toString();
    }

    /**
     * ʹ��DecimalFormat,����С�������λ
     */
    public static String format2(double value)
    {

        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(value);
    }

    /**
     * ʹ��NumberFormat,����С�������λ
     */
    public static String format3(double value)
    {

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        /*
         * setMinimumFractionDigits���ó�2
         *
         * �������ô������ô��value��ֵ��100.00��ʱ�򷵻�100
         *
         * ������100.00
         */
        nf.setMinimumFractionDigits(2);
        nf.setRoundingMode(RoundingMode.HALF_UP);
        /*
         * ���������ĸ�ʽ�ö��Ÿ������������ó�true
         */
        nf.setGroupingUsed(false);
        return nf.format(value);
    }

    /**
     * ʹ��java.util.Formatter,����С�������λ
     */
    public static String format4(double value)
    {
        /*
         * %.2f % ��ʾ С����ǰ����λ�� 2 ��ʾ��λС�� ��ʽ��Ľ��Ϊ f ��ʾ������
         */
      Formatter formatter = new Formatter();
      try {
        return formatter.format("%.2f", value).toString();
      } finally {
        formatter.close();
      }
    }

    /**
     * ʹ��String.format��ʵ�֡�
     *
     * ���������ʵ��format4��һ����
     */
    public static String format5(double value)
    {

      return String.format("%.2f", value);
    }
}
