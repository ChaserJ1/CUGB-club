package edu.cugb.subject.infra.basic.service;

import edu.cugb.subject.infra.basic.entity.SubjectJudge;

import java.util.List;


/**
 * 判断题(SubjectJudge)表服务接口
 *
 * @author makejava
 * @since 2024-10-25 15:43:24
 */
public interface SubjectJudgeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectJudge queryById(Long id);


    /**
     * 新增数据
     *
     * @param subjectJudge 实例对象
     * @return 实例对象
     */
    SubjectJudge insert(SubjectJudge subjectJudge);

    /**
     * 批量新增判断题
     *
     * @param subjectJudgeList
     */
    void batchInsert(List<SubjectJudge> subjectJudgeList);

    /**
     * 修改数据
     *
     * @param subjectJudge 实例对象
     * @return 实例对象
     */
    SubjectJudge update(SubjectJudge subjectJudge);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);


    List<SubjectJudge> queryByCondition(SubjectJudge subjectJudge);
}
