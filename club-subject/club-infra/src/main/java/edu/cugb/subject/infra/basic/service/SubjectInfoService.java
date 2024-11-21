package edu.cugb.subject.infra.basic.service;

import edu.cugb.subject.infra.basic.entity.SubjectInfo;

import java.util.List;


/**
 * 题目信息表(SubjectInfo)表服务接口
 *
 * @author makejava
 * @since 2024-10-25 15:42:11
 */
public interface SubjectInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectInfo queryById(Long id);


    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo insert(SubjectInfo subjectInfo);

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo update(SubjectInfo subjectInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据条件查询题目
     *
     * @param subjectInfo
     * @param categoryId
     * @param labelId
     * @return
     */
    int countByCondition(SubjectInfo subjectInfo, Long categoryId, Long labelId);

    /**
     * 分页查询题目
     *
     * @param subjectInfo
     * @param start
     * @param pageSize
     * @param categoryId
     * @param labelId
     * @return
     */
    List<SubjectInfo> queryPage(SubjectInfo subjectInfo, int start, Integer pageSize, Long categoryId, Long labelId);
}
