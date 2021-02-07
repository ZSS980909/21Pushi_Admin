package com.ershiyi.entity;

import lombok.Data;

/**
 * @Description: 学生荣耀得分
 * @author: zss98
 * @date: 2020-12-06 11:19
 * @version: 1.0
 */
@Data
public class GloryScore {
    private int finishKnow;   // 完成的知识点数量
    private double accuracy;  // 做题正确率
    private int numberOfQuestions;  // 做题数量
    private int studyLength;  // 学习时长
    private int riseFinishKnow;  // 对比昨天学习知识点数量提升
    private double riseAccuracy;  // 对比昨天正确率提升
    private int riseNumberOfQuestions;  // 对比昨天做题数量提升
    private long riseStudyLength;  // 对比昨天学习时长提升
}
