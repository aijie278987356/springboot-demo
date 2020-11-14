package org.aijie.helloworld.capital.service;

import org.aijie.helloworld.capital.entity.Capital;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (Capital)表服务接口
 *
 * @author makejava
 * @since 2020-11-10 21:17:41
 */
public interface CapitalService {

    public int addCapital(MultipartFile file) throws Exception;

    /**
     * 通过ID查询单条数据
     *
     * @param contract 主键
     * @return 实例对象
     */
    Capital queryById(String contract);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Capital> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param capital 实例对象
     * @return 实例对象
     */
    Capital insert(Capital capital);

    /**
     * 修改数据
     *
     * @param capital 实例对象
     * @return 实例对象
     */
    Capital update(Capital capital);

    /**
     * 通过主键删除数据
     *
     * @param contract 主键
     * @return 是否成功
     */
    boolean deleteById(Integer contract);

}