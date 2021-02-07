package com.ershiyi.service;

import com.ershiyi.common.dto.PageDTO;
import com.ershiyi.domain.AbstractBaseDomain;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.util.Collection;
import java.util.List;

/**
 * 通用的业务逻辑
 *
 * @author liy
 */
public interface BaseService<T extends AbstractBaseDomain> {

    /**
     * 开启分页
     * @param pageDTO
     */
    void startPage(PageDTO pageDTO);

    /**
     * 查询属性值是否唯一
     *
     * @param property
     * @param value
     * @return true/唯一，false/不唯一
     */
    boolean unique(String property, String value);

    /**
     * 添加数据(全部字段)
     * @param entity
     * @return 添加数据对应主键
     */
    String create(T entity);

    /**
     * 批量添加(全部字段)
     * @param entitys
     * @return 添加数据对应所有主键
     */
    List<String> create(List<T> entitys);

    /**
     * 添加数据(字段不为空)
     * @param entity
     * @return 添加数据对应主键
     */
    String createSelective(T entity);

    /**
     * 批量添加(字段不为空)
     * @param entitys
     * @return 添加数据对应所有主键
     */
    List<String> createSelective(List<T> entitys);

    /**
     * 更新数据(全部字段)
     * @param entity
     * @return 受影响行数
     */
    int update(T entity);

    /**
     * 多记录更新数据(全部字段)
     * @param entitys
     * @return 受影响行数
     */
    int update(List<T> entitys);

    /**
     * 更新数据(字段不为空)
     * @param entity
     * @return 受影响行数
     */
    int updateSelective(T entity);

    /**
     * 更新数据(字段不为空)
     * @param entitys
     * @return 受影响行数
     */
    int updateSelective(List<T> entitys);

    /**
     * 条件更新数据(全部字段)
     * @param entity
     * @param example
     * @return 受影响行数
     */
    int updateByExample(T entity, Example example);

    /**
     * 条件更新数据(全部字段)
     * @param entitys
     * @param example
     * @return 受影响行数
     */
    int updateByExample(List<T> entitys, Example example);

    /**
     * 条件更新数据(全部字段)
     * @param entitys
     * @param examples
     * @return 受影响行数
     */
    int updateByExample(List<T> entitys, List<Example> examples);

    /**
     * 条件更新数据(字段不为空)
     * @param entity
     * @param example
     * @return 受影响行数
     */
    int updateByExampleSelective(T entity, Example example);

    /**
     * 条件更新数据(字段不为空)
     * @param entitys
     * @param example
     * @return 受影响行数
     */
    int updateByExampleSelective(List<T> entitys, Example example);

    /**
     * 条件更新数据(字段不为空)
     * @param entitys
     * @param examples
     * @return 受影响行数
     */
    int updateByExampleSelective(List<T> entitys, List<Example> examples);

    /**
     * 删除数据(物理删除)
     * @param ids
     * @return 受影响行数
     */
    int delete(String... ids);

    /**
     * 删除数据(物理删除)
     * @param ids
     * @return 受影响行数
     */
    int delete(Collection<String> ids);

    /**
     * 删除数据(物理删除)
     * @param entity
     * @return 受影响行数
     */
    int delete(T entity);

    /**
     * 删除数据(物理删除)
     * @param entitys
     * @return 受影响行数
     */
    int delete(List<T> entitys);

    /**
     * 删除数据(物理删除)
     * @param example
     * @return 受影响行数
     */
    int deleteByExample(Example example);

    /**
     * 删除数据(逻辑删除)
     * @param ids
     * @return 受影响行数
     */
    int deleteLogic(String... ids);

    /**
     * 删除数据(逻辑删除)
     * @param ids
     * @return 受影响行数
     */
    int deleteLogic(Collection<String> ids);

    /**
     * 删除数据(逻辑删除)
     * @param entity
     * @return 受影响行数
     */
    int deleteLogic(T entity);

    /**
     * 删除数据(逻辑删除)
     * @param entitys
     * @return 受影响行数
     */
    int deleteLogic(List<T> entitys);

    /**
     * 删除数据(逻辑删除)
     * @param example
     * @return 受影响行数
     */
    int deleteLogicByExample(Example example);

    /**
     * 条件查询(所有数据)
     * @param example
     * @return 查询数据
     */
    List<T> selectByExample(Example example);

    /**
     * 条件查询(过滤逻辑删除)
     * @param example
     * @return 查询数据
     */
    List<T> selectLogicByExample(Example example);

    /**
     * 条件查询(过滤逻辑删除)
     * @param example
     * @return 第一行数据
     */
    T selectLogicOneByExample(Example example);

    /**
     * 主键查询(过滤逻辑删除)
     * @param pk
     * @return 主键对应数据
     */
    T selectLogicByPrimaryKey(String pk);

    /**
     * 分页条件查询(所有数据)
     * @param pageDTO
     * @param example
     * @return 单页数据
     */
    PageInfo<T> selectPageByExample(PageDTO pageDTO, Example example);

    /**
     * 分页条件查询(过滤逻辑删除)
     * @param pageDTO
     * @param example
     * @return 单页数据
     */
    PageInfo<T> selectLogicPageByExample(PageDTO pageDTO, Example example);
    
}
