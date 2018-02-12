package com.qs.service.impl;


import com.qs.entity.BaseEntity;
import com.qs.service.HibernateBaseService;
import com.qs.service.base.BaseService;
import com.qs.util.HqlBuilder;
import com.qs.util.ReflectUtil;
import com.qs.util.SQLConstant;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@SuppressWarnings("unchecked")
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T>, SQLConstant {
    @Resource
    protected HibernateBaseService baseService;


    protected final Class<T> modelClass;

    //实例化时完成modelClass属性的赋值
    public BaseServiceImpl() {
        modelClass = ReflectUtil.getGenericParamClass(this.getClass());
    }

    //获取实体类名
    protected String getEntityName() {
        return ClassUtils.getShortClassName(modelClass.getName());
    }

    //获取criteria对象
    @Transactional
    public Criteria createCriteria() {
        return baseService.getSession().createCriteria(modelClass);
    }

    //指定实体，实例化获取criteria对象
    protected Criteria createCriteria(Class<T> clazz) {
        return baseService.getSession().createCriteria(clazz);
    }

    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public String save(T model) {
        Assert.notNull(model, "model is null!");
        return baseService.save(model);
    }

    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public String saveOrUpdate(T model) {
        Assert.notNull(model, "model is null!");
        String modelId = model.getId();
        if (StringUtils.isBlank(modelId)) {
            return baseService.save(model);
        }

        baseService.update(model);
        baseService.getSession().flush();
        return modelId;
    }

    @Transactional(readOnly = true)
    public T load(String modelId) {
        Assert.hasText(modelId, "modelId is empty!");
        return baseService.load(modelClass, modelId);
    }

    @Transactional(readOnly = false)
    public void update(T model) {
        baseService.getSession().update(model);
    }

    @Transactional(readOnly = false)
    public int update(String[] fields, Object[] values, T model) {
        Assert.notNull(model);
        Assert.notNull(model.getId());
        Assert.notEmpty(fields);
        Assert.notEmpty(values);

        HqlBuilder builder = new HqlBuilder();
        builder.append(UPDATE).append(model.getClass().getName()).append(SET);
        for (int i = 0; i < fields.length; i++) {
            builder.append(fields[i]).append(EQUAL).append("?");
            if (i == fields.length - 1) {
                builder.append(COMMA);
            }
        }

        builder.append(WHERE).append(BaseEntity._id).append(EQUAL).append("?");
        Query query = baseService.getSession().createQuery(builder.toString());
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }

        query.setParameter(values.length, model.getId());
        return query.executeUpdate();
    }

    @Transactional(readOnly = false)
    public int update(String field, Object value, T model) {
        Assert.notNull(model);
        Assert.notNull(model.getId());
        Assert.notNull(field);
        Assert.notNull(value);

        HqlBuilder builder = new HqlBuilder();
        builder.append(UPDATE).append(model.getClass().getSimpleName()).append(SET);
        builder.append(field).append(EQUAL).append("?");
        builder.append(WHERE).append(BaseEntity._id).append(EQUAL).append("?");
        Query query = baseService.getSession().createQuery(builder.toString());
        query.setParameter(0, value);
        query.setParameter(1, model.getId());
        return query.executeUpdate();
    }
}