package com.matariky.mybatis;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlUtils;
import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.matariky.annotation.DataScope;
import com.matariky.constant.PermissionConstant;
import com.matariky.model.QueryDataIsolation;
import com.matariky.utils.NumberUtils;
import com.matariky.utils.TokenUtils;

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;

public class MybatisPlusDataScopeInterceptor extends JsqlParserSupport implements InnerInterceptor {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds,
            ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        try {
            DataScope dataScope = getDataScopeAnnotation(ms, parameter);
            if (Objects.nonNull(dataScope)) {
                /** Extract isolation parameters **/
                QueryDataIsolation dataIsolation = getDataIsolation(parameter, dataScope);
                if (Objects.nonNull(dataIsolation) && StringUtils.isNotEmpty(dataIsolation.getStrategyCode())) {
                    Statement statement;
                    statement = CCJSqlParserUtil.parse(boundSql.getSql());
                    if (statement instanceof Select) {
                        Select selectStatement = (Select) statement;
                        PlainSelect plain = (PlainSelect) selectStatement.getSelectBody();
                        /** Assembly Isolation Filter SQL Conditions **/
                        StringBuffer dataIsolationWhereSql = getDataIsolationWhereSql(dataIsolation, dataScope);
                        if (dataIsolationWhereSql.length() > NumberUtils.INTEGER_ZERO) {
                            if (Objects.nonNull(plain.getWhere())) {
                                /** Merge Filter Criteria **/
                                dataIsolationWhereSql.append(" AND (" + plain.getWhere() + ")");
                            }
                            plain.setWhere(CCJSqlParserUtil.parseCondExpression(dataIsolationWhereSql.toString()));
                            PluginUtils.MPBoundSql mpBoundSql = PluginUtils.mpBoundSql(boundSql);
                            List<ParameterMapping> mappings = mpBoundSql.parameterMappings();
                            mpBoundSql.sql(selectStatement.toString());
                            mpBoundSql.parameterMappings(mappings);

                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    private StringBuffer getDataIsolationWhereSql(QueryDataIsolation dataIsolation, DataScope dataScope) {
        StringBuffer sql = new StringBuffer();
        switch (dataIsolation.getStrategyCode()) {
            case PermissionConstant.COMMON_DATA_ACCESS_ALL:
                sql.append(getCondition(null, SqlKeyword.EQ, dataScope, "tenant_id", dataIsolation.getTenantId()));
                break;
            case PermissionConstant.COMMON_DATA_ACCESS_LEVEL:
            case PermissionConstant.COMMON_DATA_ACCESS_ORG:
                sql.append(getCondition(null, SqlKeyword.LIKE, dataScope, "tenant_id",
                        SqlUtils.concatLike(dataIsolation.getTenantId(), SqlLike.RIGHT)));
                sql.append(" ").append(SqlKeyword.AND.getSqlSegment()).append(" ").append("(");
                if (PermissionConstant.COMMON_DATA_ACCESS_ORG.equals(dataIsolation.getStrategyCode())) {
                    sql.append(" ").append(getCondition(null, SqlKeyword.LIKE, dataScope, "operator_org_code",
                            SqlUtils.concatLike(dataIsolation.getOrgCode(), SqlLike.RIGHT)));
                } else {
                    sql.append(" ").append(getCondition(null, SqlKeyword.EQ, dataScope, "operator_org_code",
                            dataIsolation.getOrgCode()));
                }
                if (CollectionUtils.isNotEmpty(dataIsolation.getSharingSelfOrgCodes())) {
                    sql.append(" ").append(getCondition(SqlKeyword.OR, SqlKeyword.IN, dataScope,
                            "operator_self_org_code",
                            dataIsolation.getSharingSelfOrgCodes().stream().collect(Collectors.joining("','"))));
                }
                if (CollectionUtils.isNotEmpty(dataIsolation.getSharingOrgCodes())) {
                    sql.append(" ").append(getCondition(SqlKeyword.OR, SqlKeyword.IN, dataScope, "operator_org_code",
                            dataIsolation.getSharingOrgCodes().stream().collect(Collectors.joining("','"))));
                }
                sql.append(")");
                break;
            default:
                sql.append(getCondition(null, SqlKeyword.EQ, dataScope, "tenant_id", dataIsolation.getTenantId()));
                sql.append(" ").append(SqlKeyword.AND.getSqlSegment()).append(" ").append("(");
                sql.append(" ").append(getCondition(null, SqlKeyword.EQ, dataScope, "operator_self_org_code",
                        dataIsolation.getSelfOrgCode()));
                if (CollectionUtils.isNotEmpty(dataIsolation.getSharingSelfOrgCodes())) {
                    sql.append(" ").append(getCondition(SqlKeyword.OR, SqlKeyword.IN, dataScope,
                            "operator_self_org_code",
                            dataIsolation.getSharingSelfOrgCodes().stream().collect(Collectors.joining("','"))));
                }
                if (CollectionUtils.isNotEmpty(dataIsolation.getSharingOrgCodes())) {
                    sql.append(" ").append(getCondition(SqlKeyword.OR, SqlKeyword.IN, dataScope, "operator_org_code",
                            dataIsolation.getSharingOrgCodes().stream().collect(Collectors.joining("','"))));
                }
                sql.append(")");
                break;
        }
        return sql;
    }

    private String getCondition(SqlKeyword logicKeyword, SqlKeyword restrictionKeyword, DataScope dataScope,
            String column, String value) {
        StringBuilder conditionBuilder = new StringBuilder();
        if (Objects.nonNull(logicKeyword)) {
            conditionBuilder.append(logicKeyword.getSqlSegment());
        }
        if (StringUtils.isNotEmpty(dataScope.alias())) {
            conditionBuilder.append(" ").append(dataScope.alias()).append(".").append(column);
        } else {
            conditionBuilder.append(" ").append(column);
        }
        conditionBuilder.append(" ").append(restrictionKeyword.getSqlSegment());
        if (SqlKeyword.IN == restrictionKeyword) {
            conditionBuilder.append(" (");
        }
        conditionBuilder.append(" ").append("'").append(value).append("'");
        if (SqlKeyword.IN == restrictionKeyword) {
            conditionBuilder.append(" ) ");
        }
        return conditionBuilder.toString();
    }

    private QueryDataIsolation getDataIsolation(Object parameterObject, DataScope dataScope) {
        String rid = request.getHeader("id");
        if (StringUtils.isEmpty(rid)) {
            return null;
        }
        rid = rid.substring(0, rid.length() - 1);
        // shared to current User's org
        List<String> orgLst = jdbcTemplate
                .queryForList(
                        "select original_owner_org_code from common_sharing_pool where resource_id='" + rid
                                + "' and tenant_id='" + TokenUtils.extractTenantIdFromHttpReqeust(request)
                                + "' and receiving_org_code='" + TokenUtils.extractOrgCode(request) + "'",
                        String.class);
        // shared to the user's individual
        List<String> indList = jdbcTemplate
                .queryForList(
                        "select original_owner_org_code from common_sharing_pool where resource_id='" + rid
                                + "' and tenant_id='" + TokenUtils.extractTenantIdFromHttpReqeust(request)
                                + "' and receiving_org_code='" + TokenUtils.extractSelfOrgCode(request) + "'",
                        String.class);
        orgLst.addAll(indList);
        List<String> indL = new ArrayList<String>();
        List<String> orgL = new ArrayList<String>();
        for (String org : orgLst) {
            if (org.startsWith("org_"))
                orgL.add(org);
            if (org.startsWith("ind_"))
                indL.add(org);
        }
        if (Objects.nonNull(parameterObject)) {
            if (parameterObject instanceof QueryDataIsolation) {
                QueryDataIsolation qdi = (QueryDataIsolation) parameterObject;
                qdi.setSharingOrgCodes(orgL);
                qdi.setSharingSelfOrgCodes(indL);
                return qdi;
            } else if (parameterObject instanceof Map) {
                Map<?, ?> paramMap = (Map<?, ?>) parameterObject;
                Iterator<?> var2 = paramMap.entrySet().iterator();
                while (var2.hasNext()) {
                    Map.Entry<?, ?> entry = (Map.Entry<?, ?>) var2.next();
                    if (entry.getKey().equals(dataScope.paramName())) {
                        if (Objects.nonNull(entry.getValue())) {
                            if (entry.getValue() instanceof QueryDataIsolation) {
                                QueryDataIsolation qdi = (QueryDataIsolation) entry.getValue();
                                qdi.setSharingOrgCodes(orgL);
                                qdi.setSharingSelfOrgCodes(indL);
                                return qdi;
                            } else if (entry.getValue() instanceof Map) {
                                QueryDataIsolation qdi = new QueryDataIsolation();
                                qdi.setSharingOrgCodes(orgL);
                                qdi.setSharingSelfOrgCodes(indL);
                                BeanMap beanMap = BeanMap.create(qdi);
                                beanMap.putAll((Map<?, ?>) entry.getValue());
                                return qdi;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private DataScope getDataScopeAnnotation(MappedStatement mappedStatement, Object parameterObject)
            throws ClassNotFoundException {
        String id = mappedStatement.getId();
        /** Get mapper class name **/
        String className = id.substring(0, id.lastIndexOf("."));
        Class<?> clazz = Class.forName(className);
        /** Get mapper method name **/
        String methodName = id.substring(id.lastIndexOf(".") + 1).replace("_COUNT", "");
        /** Method of obtaining execution **/
        Method declaredMethod = getMethod(clazz, methodName, parameterObject);
        if (Objects.isNull(declaredMethod)) {
            return null;
        }
        return AnnotationUtils.getAnnotation(declaredMethod, DataScope.class);
    }

    private Method getMethod(Class<?> clazz, String methodName, Object parameterObject) {
        if (clazz.equals(EnhanceBaseMapper.class) && !isPlusDataIsolation(parameterObject)) {
            return null;
        }
        Method declaredMethod = Arrays.stream(clazz.getDeclaredMethods()).filter(it -> it.getName().equals(methodName))
                .findFirst().orElse(null);
        if (Objects.isNull(declaredMethod)) {
            Type[] genericInterfaces = clazz.getGenericInterfaces();
            if (Objects.nonNull(genericInterfaces) && genericInterfaces.length > NumberUtils.INTEGER_ZERO) {
                for (Type type : genericInterfaces) {
                    if (type instanceof ParameterizedType) {
                        Method method = getMethod((Class<?>) ((ParameterizedType) type).getRawType(), methodName,
                                parameterObject);
                        if (Objects.nonNull(method)) {
                            return method;
                        }
                    }
                }
            }
        }
        return declaredMethod;
    }

    private boolean isPlusDataIsolation(Object parameterObject) {
        if (Objects.nonNull(parameterObject)) {
            if (parameterObject instanceof Map) {
                Map<?, ?> parameterMap = (Map<?, ?>) parameterObject;
                return parameterMap.containsKey("dataIsolation") && parameterMap.containsKey("ew");
            }
        }
        return Boolean.FALSE;
    }
}
