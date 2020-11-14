package org.aijie.helloworld.capital.dao;

import org.aijie.helloworld.capital.entity.Capital;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Capital)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-10 21:17:40
 */
public interface CapitalDao {

    /**
     * 通过ID查询单条数据
     *
     * @param contract 主键
     * @return 实例对象
     */
    Capital queryById(String contract);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Capital> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param capital 实例对象
     * @return 对象列表
     */
    List<Capital> queryAll(Capital capital);

    /**
     * 新增数据
     *
     * @param capital 实例对象
     * @return 影响行数
     */
    int insert(Capital capital);

    /**
     * 修改数据
     *
     * @param capital 实例对象
     * @return 影响行数
     */
    int update(Capital capital);

    /**
     * 通过主键删除数据
     *
     * @param contract 主键
     * @return 影响行数
     */
    int deleteById(Integer contract);

}