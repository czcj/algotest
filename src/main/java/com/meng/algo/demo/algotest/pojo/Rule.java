package com.meng.algo.demo.algotest.pojo;


import java.io.Serializable;
import java.util.List;
import org.msgpack.annotation.Message;


/**
 * rule主表
 *
 * @author robinwb
 * @email wangbiao@classba.cn
 * @date 2017-10-28 11:23:57
 */
@Message
public class Rule implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean isDynamtic;//是否动态生成
	private String ruleId; //规则ID
	private String ruleType; //规则类型(做题，切换知识点，更改知识点范围)
	private String project; //科目枚举 查看重构基本数据整理文档
	private List<RuleAction> actions;//触发后的动作
  	private List<Condition> conditions;//触发规则的条件
  	private RuleTrigger trigger;//触发器

	public boolean isDynamtic() {
		return isDynamtic;
	}

	public void setDynamtic(boolean dynamtic) {
		isDynamtic = dynamtic;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public List<RuleAction> getActions() {
		return actions;
	}

	public void setActions(List<RuleAction> actions) {
		this.actions = actions;
	}

	public List<Condition> getConditions() {
    return conditions;
  }

  public void setConditions(List<Condition> conditions) {
    this.conditions = conditions;
  }

  public RuleTrigger getTrigger() {
    return trigger;
  }

  public void setTrigger(RuleTrigger trigger) {
    this.trigger = trigger;
  }



	/**
	 * 设置：
	 */
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	/**
	 * 获取：
	 */
	public String getRuleType() {
		return ruleType;
	}

  public String getRuleId() {
    return ruleId;
  }

  public void setRuleId(String ruleId) {
    this.ruleId = ruleId;
  }

  public Rule() {
  }

  public Rule(String ruleId, String name, Integer triggerId, Integer conditionId,
      Integer actionId, String ruleType,
			List<RuleAction> ruleActions,
      List<Condition> conditions,
      RuleTrigger trigger) {
    this.ruleId = ruleId;
		this.ruleType = ruleType;
		this.actions = ruleActions;
    this.trigger = trigger;
    this.conditions = conditions;
  }


}
