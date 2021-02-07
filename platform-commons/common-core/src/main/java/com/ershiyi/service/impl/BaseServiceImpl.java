package com.ershiyi.service.impl;

import cn.hutool.core.util.ReflectUtil;
import com.ershiyi.aspect.annotation.AutowiredData;
import com.ershiyi.common.dto.PageDTO;
import com.ershiyi.domain.AbstractBaseDomain;
import com.ershiyi.service.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.AbstractMapper;
import tk.mybatis.mapper.entity.Example;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public class BaseServiceImpl<T extends AbstractBaseDomain, M extends AbstractMapper<T>> implements BaseService<T> {

    protected Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Autowired
    protected M mapper;

    /**
     * 开启分页
     * @param pageDTO
     */
    @Override
    public void startPage(PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNumber(), pageDTO.getPageSize());
        PageHelper.orderBy(pageDTO.orderBy());
    }

    /**
     * 查询属性值是否唯一
     *
     * @param property
     * @param value
     * @return true/唯一，false/不唯一
     */
    @Override
    public boolean unique(String property, String value) {
        Example example = new Example(entityClass);
        example.and().andEqualTo(property, value);
        int result = mapper.selectCountByExample(example);
        if (result > 0) {
            return false;
        }
        return true;
    }

    /**
     * 添加数据(全部字段)
     * @param entity
     * @return 添加数据对应主键
     */
    @Override
    @Transactional
    @AutowiredData
    public String create(T entity) {
        mapper.insert(entity);
        return entity.getGuid();
    }

    /**
     * 批量添加(全部字段)
     * @param entitys
     * @return 添加数据对应所有主键
     */
    @Override
    @Transactional
    @AutowiredData
    public List<String> create(List<T> entitys) {
        List<String> ids = Lists.newArrayList();
        entitys.forEach( entity ->{
            mapper.insert(entity);
            ids.add(entity.getGuid());
        });
        return ids;
    }

    /**
     * 添加数据(字段不为空)
     * @param entity
     * @return 添加数据对应主键
     */
    @Override
    @Transactional
    @AutowiredData
    public String createSelective(T entity) {
        mapper.insertSelective(entity);
        return entity.getGuid();
    }

    /**
     * 批量添加(字段不为空)
     * @param entitys
     * @return 添加数据对应所有主键
     */
    @Override
    @Transactional
    @AutowiredData
    public List<String> createSelective(List<T> entitys) {
        List<String> ids = Lists.newArrayList();
        entitys.forEach( entity ->{
            mapper.insertSelective(entity);
            ids.add(entity.getGuid());
        });
        return ids;
    }

    /**
     * 更新数据(全部字段)
     * @param entity
     * @return 受影响行数
     */
    @Override
    @Transactional
    @AutowiredData
    public int update(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    /**
     * 多记录更新数据(全部字段)
     * @param entitys
     * @return 受影响行数
     */
    @Override
    @Transactional
    @AutowiredData
    public int update(List<T> entitys) {
        int updateCount = 0;
        for(T entity : entitys){
            updateCount += mapper.updateByPrimaryKey(entity);
        }
        return updateCount;
    }

    /**
     * 更新数据(字段不为空)
     * @param entity
     * @return 受影响行数
     */
    @Override
    @Transactional
    @AutowiredData
    public int updateSelective(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 更新数据(字段不为空)
     * @param entitys
     * @return 受影响行数
     */
    @Override
    @Transactional
    @AutowiredData
    public int updateSelective(List<T> entitys) {
        int updateCount = 0;
        for(T entity : entitys){
            updateCount += mapper.updateByPrimaryKeySelective(entity);
        }
        return updateCount;
    }

    /**
     * 条件更新数据(全部字段)
     * @param entity
     * @param example
     * @return 受影响行数
     */
    @Override
    @Transactional
    @AutowiredData
    public int updateByExample(T entity, Example example) {
        return mapper.updateByExample(entity, example);
    }

    /**
     * 条件更新数据(全部字段)
     * @param entitys
     * @param example
     * @return 受影响行数
     */
    @Override
    @Transactional
    @AutowiredData
    public int updateByExample(List<T> entitys, Example example) {
        int updateCount = 0;
        for(T entity : entitys){
            updateCount += mapper.updateByExample(entity, example);
        }
        return updateCount;
    }

    /**
     * 条件更新数据(全部字段)
     * @param entitys
     * @param examples
     * @return 受影响行数
     */
    @Override
    @Transactional
    @AutowiredData
    public int updateByExample(List<T> entitys, List<Example> examples) {
        int updateCount = 0;
        for(int i = 0; i < entitys.size(); i++){
            updateCount += mapper.updateByExample(entitys.get(i), examples.get(i));
        }
        return updateCount;
    }

    /**
     * 条件更新数据(字段不为空)
     * @param entity
     * @param example
     * @return 受影响行数
     */
    @Override
    @Transactional
    @AutowiredData
    public int updateByExampleSelective(T entity, Example example) {
        return mapper.updateByExampleSelective(entity, example);
    }

    /**
     * 条件更新数据(字段不为空)
     * @param entitys
     * @param example
     * @return 受影响行数
     */
    @Override
    @Transactional
    @AutowiredData
    public int updateByExampleSelective(List<T> entitys, Example example) {
        int updateCount = 0;
        for(T entity : entitys){
            updateCount += mapper.updateByExampleSelective(entity, example);
        }
        return updateCount;
    }

    /**
     * 条件更新数据(字段不为空)
     * @param entitys
     * @param examples
     * @return 受影响行数
     */
    @Override
    @Transactional
    @AutowiredData
    public int updateByExampleSelective(List<T> entitys, List<Example> examples) {
        int updateCount = 0;
        for(int i = 0; i < entitys.size(); i++){
            updateCount += mapper.updateByExampleSelective(entitys.get(i), examples.get(i));
        }
        return updateCount;
    }

    /**
     * 删除数据(物理删除)
     * @param ids
     * @return 受影响行数
     */
    @Override
    public int delete(String... ids) {
        int deleteCount = 0;
        for(String id : ids){
            deleteCount += mapper.deleteByPrimaryKey(id);
        }
        return deleteCount;
    }

    /**
     * 删除数据(物理删除)
     * @param ids
     * @return 受影响行数
     */
    @Override
    public int delete(Collection<String> ids) {
        int deleteCount = 0;
        for(String id : ids){
            deleteCount += mapper.deleteByPrimaryKey(id);
        }
        return deleteCount;
    }

    /**
     * 删除数据(物理删除)
     * @param entity
     * @return 受影响行数
     */
    @Override
    public int delete(T entity) {
        return mapper.delete(entity);
    }

    /**
     * 删除数据(物理删除)
     * @param entitys
     * @return 受影响行数
     */
    @Override
    public int delete(List<T> entitys) {
        int deleteCount = 0;
        for(T entity : entitys){
            deleteCount += mapper.delete(entity);
        }
        return deleteCount;
    }

    /**
     * 删除数据(物理删除)
     * @param example
     * @return 受影响行数
     */
    @Override
    public int deleteByExample(Example example) {
        return mapper.deleteByExample(example);
    }

    /**
     * 删除数据(逻辑删除)
     * @param ids
     * @return 受影响行数
     */
    @Override
    public int deleteLogic(String... ids) {
        int deleteCount = 0;
        for(String id : ids){
            T t = createEntity();
            t.setGuid(id).setDeleted(1);
            deleteCount += mapper.updateByPrimaryKeySelective(t);
        }
        return deleteCount;
    }

    /**
     * 删除数据(逻辑删除)
     * @param ids
     * @return 受影响行数
     */
    @Override
    public int deleteLogic(Collection<String> ids) {
        int deleteCount = 0;
        for(String id : ids){
            T t = createEntity();
            t.setGuid(id).setDeleted(1);
            deleteCount += mapper.updateByPrimaryKeySelective(t);
        }
        return deleteCount;
    }

    /**
     * 删除数据(逻辑删除)
     * @param entity
     * @return 受影响行数
     */
    @Override
    public int deleteLogic(T entity) {
        T t = createEntity();
        t.setGuid(entity.getGuid()).setDeleted(1);
        return mapper.updateByPrimaryKeySelective(t);
    }

    /**
     * 删除数据(逻辑删除)
     * @param entitys
     * @return 受影响行数
     */
    @Override
    public int deleteLogic(List<T> entitys) {
        int deleteCount = 0;
        for(T entity : entitys){
            T t = createEntity();
            t.setGuid(entity.getGuid()).setDeleted(1);
            deleteCount += mapper.updateByPrimaryKeySelective(t);
        }
        return deleteCount;
    }

    /**
     * 删除数据(逻辑删除)
     * @param example
     * @return 受影响行数
     */
    @Override
    public int deleteLogicByExample(Example example) {
        T t = createEntity();
        t.setDeleted(1);
        return mapper.updateByExampleSelective(t, example);
    }

    /**
     * 条件查询(所有数据)
     * @param example
     * @return 查询数据
     */
    @Override
    public List<T> selectByExample(Example example) {
        return mapper.selectByExample(example);
    }

    /**
     * 条件查询(过滤逻辑删除)
     * @param example
     * @return 查询数据
     */
    @Override
    public List<T> selectLogicByExample(Example example) {
        example.and().andEqualTo(T.DELETED, BigDecimal.ZERO);
        return mapper.selectByExample(example);
    }

    /**
     * 条件查询(过滤逻辑删除)
     * @param example
     * @return 第一行数据
     */
    @Override
    public T selectLogicOneByExample(Example example) {
        example.and().andEqualTo(T.DELETED, BigDecimal.ZERO);
        return mapper.selectOneByExample(example);
    }

    /**
     * 主键查询(过滤逻辑删除)
     * @param pk
     * @return 主键对应数据
     */
    @Override
    public T selectLogicByPrimaryKey(String pk){
        Example example = createExample();
        example.and().andEqualTo(T.DELETED, BigDecimal.ZERO).andEqualTo(T.GUID, pk);
        return mapper.selectOneByExample(example);
    }

    /**
     * 分页条件查询(所有数据)
     * @param pageDTO
     * @param example
     * @return 单页数据
     */
    @Override
    public PageInfo<T> selectPageByExample(PageDTO pageDTO, Example example) {
        startPage(pageDTO);
        return new PageInfo<>(selectByExample(example));
    }

    /**
     * 分页条件查询(过滤逻辑删除)
     * @param pageDTO
     * @param example
     * @return 单页数据
     */
    @Override
    public PageInfo<T> selectLogicPageByExample(PageDTO pageDTO, Example example) {
        startPage(pageDTO);
        return new PageInfo<>(selectLogicByExample(example));
    }

    /**
     * 创建查询构造器
     * @return
     */
    protected Example createExample(){
        return new Example(entityClass);
    }

    /**
     * 创建领域模型对象
     * @return
     */
    protected T createEntity(){
        return (T) ReflectUtil.newInstance(entityClass).init();
    }

}
