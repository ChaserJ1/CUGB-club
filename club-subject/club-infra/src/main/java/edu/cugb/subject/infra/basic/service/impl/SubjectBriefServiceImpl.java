package edu.cugb.subject.infra.basic.service.impl;

import edu.cugb.subject.infra.basic.entity.SubjectBrief;
import edu.cugb.subject.infra.basic.mapper.SubjectBriefDao;
import edu.cugb.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 简答题(SubjectBrief)表服务实现类
 *
 * @author makejava
 * @since 2024-10-25 15:43:00
 */
@Service("subjectBriefService")
public class SubjectBriefServiceImpl implements SubjectBriefService {
    @Resource
    private SubjectBriefDao subjectBriefDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectBrief queryById(Long id) {
        return this.subjectBriefDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectBrief insert(SubjectBrief subjectBrief) {
        this.subjectBriefDao.insert(subjectBrief);
        return subjectBrief;
    }

    @Override
    public void batchInsert(List<SubjectBrief> subjectBriefList) {
        this.subjectBriefDao.insertBatch(subjectBriefList);
    }

    /**
     * 修改数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectBrief update(SubjectBrief subjectBrief) {
        this.subjectBriefDao.update(subjectBrief);
        return this.queryById(subjectBrief.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectBriefDao.deleteById(id) > 0;
    }

    /***
     * 根据条件查询简答题详情
     * @param subjectBrief
     * @return
     */
    @Override
    public SubjectBrief queryByCondition(SubjectBrief subjectBrief) {
        return subjectBriefDao.queryAllByLimit(subjectBrief);
    }


}
