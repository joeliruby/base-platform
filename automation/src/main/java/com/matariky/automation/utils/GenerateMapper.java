package com.matariky.automation.utils;

import java.util.HashMap;
import java.util.Map;
import com.matariky.automation.bean.DbBean;

public class GenerateMapper {

	public static StringBuffer MAPPER_TEMPLATE = null;

	public static StringBuffer MAPPER_XML_TEMPLATE = null;

	static {
		MAPPER_TEMPLATE = new StringBuffer();
		MAPPER_TEMPLATE.append("package [package].mapper;\n\n");
		MAPPER_TEMPLATE.append("import java.util.List;\n");
		MAPPER_TEMPLATE.append("import java.util.Map;\n\n");
		MAPPER_TEMPLATE.append("import java.io.Serializable;\n\n");
		MAPPER_TEMPLATE.append("import com.baomidou.mybatisplus.core.toolkit.Constants;\n\n");
		MAPPER_TEMPLATE.append("import com.baomidou.mybatisplus.core.conditions.Wrapper;\n\n");
		MAPPER_TEMPLATE.append("import com.matariky.annotation.DataScope;\n\n");
		MAPPER_TEMPLATE.append("import java.util.Collection;\n\n");
		MAPPER_TEMPLATE.append("import com.baomidou.mybatisplus.core.metadata.IPage;\n\n");
		MAPPER_TEMPLATE.append("import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n\n");
		MAPPER_TEMPLATE.append("import com.github.pagehelper.Page;\n");
		MAPPER_TEMPLATE.append("import org.apache.ibatis.annotations.Param;\n");
		MAPPER_TEMPLATE.append("import [package].bean.[tablename];\n");

		MAPPER_TEMPLATE.append("/**\n");
		MAPPER_TEMPLATE.append("*PERSISTENCE INTERFACE\n");
		MAPPER_TEMPLATE.append("* @author AUTOMATION\n");
		MAPPER_TEMPLATE.append("*/\n");
		MAPPER_TEMPLATE.append("public interface [tablename]Mapper extends BaseMapper<[tablename]>{\n");
		MAPPER_TEMPLATE.append("\t//QUERY ALL PAGINATION\n");

		MAPPER_TEMPLATE.append("@DataScope(alias=\"t\")");
		MAPPER_TEMPLATE.append("\tpublic List<[tablename]> get[tablename]All(@Param(\"params\") [tablename] bean);\n");
		MAPPER_TEMPLATE.append("\t//QUERY COUNT ALL\n");
		MAPPER_TEMPLATE.append("\tpublic int get[tablename]AllCount();\n");
		MAPPER_TEMPLATE.append("\t//NEW \n");
		MAPPER_TEMPLATE.append("\tpublic int create[tablename]([tablename] bean);\n");
		MAPPER_TEMPLATE.append("\t//UPDATE \n");
		MAPPER_TEMPLATE.append("\tpublic int update[tablename](@Param(\"params\") [tablename] bean);\n");
		MAPPER_TEMPLATE.append("\t//DELETE\n");
		MAPPER_TEMPLATE.append("\tpublic int del[tablename]ById(int id);\n");
		MAPPER_TEMPLATE.append("\t//QUERY BY ID\n");
		MAPPER_TEMPLATE.append("\tpublic [tablename] get[tablename]ById(int id);\n");

		MAPPER_TEMPLATE.append("\t//INSERT ONE\n");
		MAPPER_TEMPLATE.append("\t@Override\n");
		MAPPER_TEMPLATE.append("\tint insert([tablename] record);\n");

		MAPPER_TEMPLATE.append("\t//DELETE BY ID\n");
		MAPPER_TEMPLATE.append("\t@Override\n");
		MAPPER_TEMPLATE.append("\tint deleteById(Serializable id);\n");

		MAPPER_TEMPLATE.append("\t//DELETE BY columnMap \n");
		MAPPER_TEMPLATE.append("\t@Override\n");
		MAPPER_TEMPLATE.append("\tint deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);\n");

		MAPPER_TEMPLATE.append("\t//DELETE BY ENTITY \n");
		MAPPER_TEMPLATE.append("\t@Override\n");
		MAPPER_TEMPLATE.append("\tint delete(@Param(Constants.WRAPPER) Wrapper<[tablename]> queryWrapper);\n");

		MAPPER_TEMPLATE.append("\t//DELETE BATCH BY IDS \n");
		MAPPER_TEMPLATE.append("\t@Override\n");
		MAPPER_TEMPLATE.append(
				"\tint deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);\n");

		MAPPER_TEMPLATE.append("\t//UPDATE BY ID\n");
		MAPPER_TEMPLATE.append("\t@Override\n");
		MAPPER_TEMPLATE.append("\tint updateById(@Param(Constants.ENTITY) [tablename] entity);\n");

		MAPPER_TEMPLATE.append("\t//UPDATE BY ENTITY\n");
		MAPPER_TEMPLATE.append("\t@Override\n");
		MAPPER_TEMPLATE.append(
				"\tint update(@Param(Constants.ENTITY) [tablename] entity, @Param(Constants.WRAPPER) Wrapper<[tablename]> updateWrapper);\n");

		MAPPER_TEMPLATE.append("\t//QUERY BY ID  \n");
		MAPPER_TEMPLATE.append("\t@Override\n");
		MAPPER_TEMPLATE.append("\t[tablename] selectById(Serializable id);\n");

		MAPPER_TEMPLATE.append("\t//QUERY BATCH BY IDS \n");
		MAPPER_TEMPLATE.append("\t@Override\n");
		MAPPER_TEMPLATE.append(
				"\tList<[tablename]> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);\n");

		MAPPER_TEMPLATE.append("\t//QUERY BY columMap\n");
		MAPPER_TEMPLATE.append("\t@Override\n");
		MAPPER_TEMPLATE.append(
				"\tList<[tablename]> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);\n");

		MAPPER_TEMPLATE.append("\t//SELECT ONE BY ENTITY\n");
		MAPPER_TEMPLATE.append("\t@Override\n");
		MAPPER_TEMPLATE
				.append("\t[tablename] selectOne(@Param(Constants.WRAPPER) Wrapper<[tablename]> queryWrapper);\n");

		MAPPER_TEMPLATE.append("\t//Query Count By Wrapper\n");
		MAPPER_TEMPLATE.append("\t@Override\n");
		MAPPER_TEMPLATE.append("\tLong selectCount(@Param(Constants.WRAPPER) Wrapper<[tablename]> queryWrapper);\n");

		MAPPER_TEMPLATE.append("\t//QUERY ALL BY ENTITY\n");
		MAPPER_TEMPLATE.append("\t@Override\n");
		MAPPER_TEMPLATE.append(
				"\tList<[tablename]> selectList(@Param(Constants.WRAPPER) Wrapper<[tablename]> queryWrapper);\n");

		MAPPER_TEMPLATE.append("\tQUERY ALL MAPS BY WRAPPER\n");
		MAPPER_TEMPLATE.append("\t@Override\n");
		MAPPER_TEMPLATE.append(
				"\tList<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<[tablename]> queryWrapper);\n");

		MAPPER_TEMPLATE.append("\t//QUERY ALL OBJECTS BY WRAPPER\n");
		MAPPER_TEMPLATE.append("\t@Override\n");
		MAPPER_TEMPLATE
				.append("\tList<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<[tablename]> queryWrapper);\n");

		MAPPER_TEMPLATE.append("\t//QUERY PAGINATION BY ENTITY\n");
		MAPPER_TEMPLATE.append(
				"\t Page<[tablename]> selectPage(Page<[tablename]> page, @Param(Constants.WRAPPER) Wrapper<[tablename]> queryWrapper);\n");

		MAPPER_TEMPLATE.append(
				"\tpublic List<[tablename]> get[tablename]DAC(@Param(\"params\") Map<String, Object> params);\n");
		MAPPER_TEMPLATE
				.append("\tpublic Long get[tablename]DACCount(@Param(\"params\") Map<String, Object> params);\n");

		MAPPER_TEMPLATE.append("\t//QUERY ALL MAP BY WRAPPER\n");
		MAPPER_TEMPLATE.append(
				"\tPage<Map<String, Object>> selectMapsPage(Page<[tablename]> page, @Param(Constants.WRAPPER) Wrapper<[tablename]> queryWrapper);\n\n\n");

		MAPPER_TEMPLATE.append("}\n");

		MAPPER_XML_TEMPLATE = new StringBuffer();
		MAPPER_XML_TEMPLATE.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		MAPPER_XML_TEMPLATE.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n");
		MAPPER_XML_TEMPLATE.append("\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
		MAPPER_XML_TEMPLATE.append("<mapper namespace=\"[package].mapper.[tablename]Mapper\">\n\n");

		MAPPER_XML_TEMPLATE.append("<sql id=\"baseQueryListSql\"> \n");
		MAPPER_XML_TEMPLATE.append(" SELECT [tle] \n");

		MAPPER_XML_TEMPLATE.append(" FROM\n");
		MAPPER_XML_TEMPLATE.append("    [dbtablename] t \n");
		MAPPER_XML_TEMPLATE.append(" WHERE \n");
		MAPPER_XML_TEMPLATE.append("    t.delete_time = 0 \n");
		MAPPER_XML_TEMPLATE.append(" </sql> \n");
		MAPPER_XML_TEMPLATE.append(" <sql id=\"baseQueryCountSql\"> \n");
		MAPPER_XML_TEMPLATE.append("   SELECT \n");
		MAPPER_XML_TEMPLATE.append("    count(1) \n");
		MAPPER_XML_TEMPLATE.append(" FROM \n");
		MAPPER_XML_TEMPLATE.append("    [dbtablename] t \n");
		MAPPER_XML_TEMPLATE.append(" WHERE \n");
		MAPPER_XML_TEMPLATE.append("    t.delete_time = 0 \n");
		MAPPER_XML_TEMPLATE.append(" </sql> \n");

		MAPPER_XML_TEMPLATE.append(
				"\t<select id=\"get[tablename]All\" parameterType=\"Map\" resultType=\"[package].bean.[tablename]\">\n");
		MAPPER_XML_TEMPLATE.append("        select [le] from [dbtablename] t where delete_time=0 \n");
		MAPPER_XML_TEMPLATE.append("\t</select>\n\n");
		MAPPER_XML_TEMPLATE
				.append("\t<select id=\"get[tablename]AllCount\" parameterType=\"Map\" resultType=\"int\">\n");
		MAPPER_XML_TEMPLATE.append("        select count(1) from [dbtablename] where delete_time=0 \n");
		MAPPER_XML_TEMPLATE.append("\t</select>\n\n");
		MAPPER_XML_TEMPLATE
				.append("\t<insert id=\"create[tablename]\" parameterType=\"[package].bean.[tablename]\">\n");
		MAPPER_XML_TEMPLATE.append("        insert into [dbtablename]([le]) \n");
		MAPPER_XML_TEMPLATE.append("        values([ile])\n");
		MAPPER_XML_TEMPLATE.append("\t</insert>\n\n");
		MAPPER_XML_TEMPLATE
				.append("\t<update id=\"update[tablename]\" parameterType=\"[package].bean.[tablename]\">\n");
		MAPPER_XML_TEMPLATE.append("        update [dbtablename] \n");
		MAPPER_XML_TEMPLATE.append("        set id=#{params.id} [upe]\n");
		MAPPER_XML_TEMPLATE.append("        where id = #{params.id} and delete_time=0 \n");
		MAPPER_XML_TEMPLATE.append("\t</update>\n\n");
		MAPPER_XML_TEMPLATE.append("\t<update id=\"del[tablename]ById\" parameterType=\"int\">\n");
		MAPPER_XML_TEMPLATE.append("        update  [dbtablename] set delete_time=now() where id=#{value}\n");
		MAPPER_XML_TEMPLATE.append("\t</update>\n\n");
		MAPPER_XML_TEMPLATE.append(
				"\t<select id=\"get[tablename]ById\"  parameterType=\"Integer\" resultType=\"[package].bean.[tablename]\" >\n");
		MAPPER_XML_TEMPLATE.append("        select [le] from [dbtablename] where id=#{value} and delete_time=0\n");
		MAPPER_XML_TEMPLATE.append("\t</select>\n\n");

		MAPPER_XML_TEMPLATE.append(
				"<select id=\"get[tablename]DACCount\" parameterType=\"java.util.Map\" resultType=\"java.lang.Long\">\n");
		MAPPER_XML_TEMPLATE.append("   <if test=\"params.strategyCode =='1'.toString()\">\n");

		MAPPER_XML_TEMPLATE.append("  <include refid=\"baseQueryCountSql\" />\n");

		MAPPER_XML_TEMPLATE.append("    <if test=\"params.selfOrgCode != null and params.selfOrgCode !=''\">\n");
		MAPPER_XML_TEMPLATE.append("       and  t.operator_self_org_code=#{params.selfOrgCode}\n");
		MAPPER_XML_TEMPLATE.append("    </if>\n");
		MAPPER_XML_TEMPLATE.append("   <if test=\"params.tenantId != null and params.tenantId !=''\">\n");
		MAPPER_XML_TEMPLATE.append("     and t.tenant_id  = #{params.tenantId}\n");
		MAPPER_XML_TEMPLATE.append(" </if>\n");
		MAPPER_XML_TEMPLATE.append(
				"       <if test=\"params.sharingSelfOrgCodes != null and params.sharingSelfOrgCodes.size() >0\">\n");
		MAPPER_XML_TEMPLATE.append("   or t.operator_self_org_code in\n");
		MAPPER_XML_TEMPLATE.append(
				"	 <foreach item=\"soc\" collection=\"params.sharingSelfOrgCodes\" open=\"(\" separator=\",\" close=\")\">\n");
		MAPPER_XML_TEMPLATE.append("        #{soc}\n");
		MAPPER_XML_TEMPLATE.append("    </foreach>\n");
		MAPPER_XML_TEMPLATE.append("    </if>\n");
		MAPPER_XML_TEMPLATE.append(
				"         <if test=\"params.sharingOrgCodes != null  and params.sharingOrgCodes.size()>0  \">\n");
		MAPPER_XML_TEMPLATE.append("        or   t.operator_org_code in \n");
		MAPPER_XML_TEMPLATE.append(
				"    	 <foreach item=\"oc\" collection=\"params.sharingOrgCodes\" open=\"(\" separator=\",\" close=\")\">\n");
		MAPPER_XML_TEMPLATE.append("            #{oc}\n");
		MAPPER_XML_TEMPLATE.append("        </foreach>\n");
		MAPPER_XML_TEMPLATE.append("    </if>\n");
		MAPPER_XML_TEMPLATE.append("</if>\n");
		MAPPER_XML_TEMPLATE.append("   <if test=\" params.strategyCode =='0'.toString()\">\n");

		MAPPER_XML_TEMPLATE.append("  <include refid=\"baseQueryCountSql\" />\n");

		MAPPER_XML_TEMPLATE.append("       <if test=\"params.tenantId != null and params.tenantId !=''\">\n");
		MAPPER_XML_TEMPLATE.append("           and t.tenant_id  = #{params.tenantId}\n");
		MAPPER_XML_TEMPLATE.append("       </if>\n");
		MAPPER_XML_TEMPLATE.append("   </if>\n");
		MAPPER_XML_TEMPLATE.append("   <if test=\" params.strategyCode =='2'.toString()\">\n");

		MAPPER_XML_TEMPLATE.append("  <include refid=\"baseQueryCountSql\" />\n");

		MAPPER_XML_TEMPLATE.append("       <if test=\"params.orgCode != null and params.orgCode !=''\">\n");
		MAPPER_XML_TEMPLATE.append("        and   t.operator_org_code=#{params.orgCode} \n");
		MAPPER_XML_TEMPLATE.append("    </if>\n");
		MAPPER_XML_TEMPLATE.append("   <if test=\"params.tenantId != null and params.tenantId !=''\">\n");
		MAPPER_XML_TEMPLATE.append("        and t.tenant_id  = #{params.tenantId}\n");
		MAPPER_XML_TEMPLATE.append("    </if>\n");
		MAPPER_XML_TEMPLATE.append(
				"   <if test=\"params.sharingSelfOrgCodes != null and params.sharingSelfOrgCodes.size() >0 \">\n");
		MAPPER_XML_TEMPLATE.append("            or  t.operator_self_org_code in\n");
		MAPPER_XML_TEMPLATE.append(
				"    	 <foreach item=\"soc\" collection=\"params.sharingSelfOrgCodes\" open=\"(\" separator=\",\" close=\")\">\n");
		MAPPER_XML_TEMPLATE.append("            #{soc}\n");
		MAPPER_XML_TEMPLATE.append("        </foreach>\n");
		MAPPER_XML_TEMPLATE.append("   </if>\n");
		MAPPER_XML_TEMPLATE
				.append("   <if test=\"params.sharingOrgCodes != null and params.sharingOrgCodes.size()>0\">\n");
		MAPPER_XML_TEMPLATE.append("             or   t.operator_org_code in\n");
		MAPPER_XML_TEMPLATE.append(
				"     	 <foreach item=\"oc\" collection=\"params.sharingOrgCodes\" open=\"(\" separator=\",\" close=\")\">\n");
		MAPPER_XML_TEMPLATE.append("             #{oc}\n");
		MAPPER_XML_TEMPLATE.append("         </foreach>\n");
		MAPPER_XML_TEMPLATE.append("         </if>\n");
		MAPPER_XML_TEMPLATE.append(" </if>\n");
		MAPPER_XML_TEMPLATE.append("   <if test=\" params.strategyCode =='3'.toString()\">\n");

		MAPPER_XML_TEMPLATE.append("  <include refid=\"baseQueryCountSql\" />\n");

		MAPPER_XML_TEMPLATE.append("        <if test=\"params.orgCode != null and params.orgCode !=''\">\n");
		MAPPER_XML_TEMPLATE.append("         and  t.operator_org_code like concat(#{params.orgCode}, '%') \n");
		MAPPER_XML_TEMPLATE.append("   		</if>\n");
		MAPPER_XML_TEMPLATE.append("       <if test=\"params.tenantId != null and params.tenantId !=''\">\n");
		MAPPER_XML_TEMPLATE.append("         and t.tenant_id  = #{params.tenantId}\n");
		MAPPER_XML_TEMPLATE.append("       </if>\n");
		MAPPER_XML_TEMPLATE.append(
				"   	   <if test=\"params.sharingSelfOrgCodes != null and params.sharingSelfOrgCodes.size() >0\">\n");
		MAPPER_XML_TEMPLATE.append("             or   t.operator_self_org_code in\n");
		MAPPER_XML_TEMPLATE.append(
				"     	 <foreach item=\"soc\" collection=\"params.sharingSelfOrgCodes\" open=\"(\" separator=\",\" close=\")\">\n");
		MAPPER_XML_TEMPLATE.append("             #{soc}\n");
		MAPPER_XML_TEMPLATE.append("         </foreach>\n");
		MAPPER_XML_TEMPLATE.append("        </if>\n");
		MAPPER_XML_TEMPLATE
				.append("   <if test=\"params.sharingOrgCodes != null and params.sharingOrgCodes.size()>0\">\n");
		MAPPER_XML_TEMPLATE.append("             or t.operator_org_code in\n");
		MAPPER_XML_TEMPLATE.append(
				"     	 <foreach item=\"oc\" collection=\"params.sharingOrgCodes\" open=\"(\" separator=\",\" close=\")\">\n");
		MAPPER_XML_TEMPLATE.append("             #{oc}\n");
		MAPPER_XML_TEMPLATE.append("         </foreach>\n");
		MAPPER_XML_TEMPLATE.append("         </if>\n");
		MAPPER_XML_TEMPLATE.append(" </if>\n");
		MAPPER_XML_TEMPLATE.append("</select>\n");

		MAPPER_XML_TEMPLATE.append(
				"<select id=\"get[tablename]DAC\" parameterType=\"java.util.Map\" resultType=\"[package].bean.[tablename]\">\n");
		MAPPER_XML_TEMPLATE.append("<if test=\"params.strategyCode =='1'.toString()\">\n");

		MAPPER_XML_TEMPLATE.append("  <include refid=\"baseQueryListSql\" />\n");

		MAPPER_XML_TEMPLATE.append("   <if test=\"params.selfOrgCode != null and params.selfOrgCode !=''\"> \n");
		MAPPER_XML_TEMPLATE.append("       and  t.operator_self_org_code=#{params.selfOrgCode} \n");
		MAPPER_XML_TEMPLATE.append("   </if>\n");
		MAPPER_XML_TEMPLATE.append("   <if test=\"params.tenantId != null and params.tenantId !=''\">\n");
		MAPPER_XML_TEMPLATE.append("       and t.tenant_id  = #{params.tenantId}\n");
		MAPPER_XML_TEMPLATE.append("   </if>\n");
		MAPPER_XML_TEMPLATE
				.append("   <if test=\"params.sharingSelfOrgCodes !=null and params.sharingSelfOrgCodes.size()>0\">\n");

		MAPPER_XML_TEMPLATE.append("      union  \n");
		MAPPER_XML_TEMPLATE.append("  <include refid=\"baseQueryListSql\" />\n");

		MAPPER_XML_TEMPLATE.append(
				"     <if test=\"params.sharingSelfOrgCodes != null and params.sharingSelfOrgCodes.size()>0\">  \n");
		MAPPER_XML_TEMPLATE.append("      and t.operator_self_org_code in\n");
		MAPPER_XML_TEMPLATE.append(
				"  	 <foreach item=\"soc\" collection=\"params.sharingSelfOrgCodes\" open=\"(\" separator=\",\" close=\")\">\n");
		MAPPER_XML_TEMPLATE.append("        #{soc}\n");
		MAPPER_XML_TEMPLATE.append("      </foreach>\n");
		MAPPER_XML_TEMPLATE.append("       </if>\n");
		MAPPER_XML_TEMPLATE.append("       <if test=\"params.tenantId != null and params.tenantId !=''\">\n");
		MAPPER_XML_TEMPLATE.append("           and t.tenant_id  = #{params.tenantId}\n");
		MAPPER_XML_TEMPLATE.append("       </if>\n");
		MAPPER_XML_TEMPLATE.append("  </if>\n");
		MAPPER_XML_TEMPLATE
				.append("  <if test=\"params.sharingOrgCodes !=null and params.sharingOrgCodes.size()>0\">\n");

		MAPPER_XML_TEMPLATE.append("      union  \n");
		MAPPER_XML_TEMPLATE.append("  <include refid=\"baseQueryListSql\" />\n");

		MAPPER_XML_TEMPLATE.append(
				"        <if test=\"params.sharingOrgCodes != null and params.sharingOrgCodes.size()>0  \"> \n");
		MAPPER_XML_TEMPLATE.append("         and   t.operator_org_code in\n");
		MAPPER_XML_TEMPLATE.append(
				"  	 <foreach item=\"oc\" collection=\"params.sharingOrgCodes\" open=\"(\" separator=\",\" close=\")\">\n");
		MAPPER_XML_TEMPLATE.append("          #{oc}\n");
		MAPPER_XML_TEMPLATE.append("       </foreach>\n");
		MAPPER_XML_TEMPLATE.append("       </if>\n");
		MAPPER_XML_TEMPLATE.append("       <if test=\"params.tenantId != null and params.tenantId !=''\">\n");
		MAPPER_XML_TEMPLATE.append("           and t.tenant_id  = #{params.tenantId}\n");
		MAPPER_XML_TEMPLATE.append("        </if>\n");
		MAPPER_XML_TEMPLATE.append("    </if>\n");
		MAPPER_XML_TEMPLATE.append("    <if test=\"params.orderField != null and params.orderField !=''\">\n");
		MAPPER_XML_TEMPLATE.append("    order by  #{params.orderField}\n");
		MAPPER_XML_TEMPLATE.append("     </if>\n");
		MAPPER_XML_TEMPLATE.append("    <if test=\"params.order != null and params.order !=''\">\n");
		MAPPER_XML_TEMPLATE.append("     #{params.order}\n");
		MAPPER_XML_TEMPLATE.append("   </if>\n");
		MAPPER_XML_TEMPLATE.append(" </if>\n");
		MAPPER_XML_TEMPLATE.append(" <if test=\" params.strategyCode =='0'.toString()\">\n");

		MAPPER_XML_TEMPLATE.append("  <include refid=\"baseQueryListSql\" />\n");

		MAPPER_XML_TEMPLATE.append("    <if test=\"params.tenantId != null and params.tenantId !=''\"> \n");
		MAPPER_XML_TEMPLATE.append("       and t.tenant_id  = #{params.tenantId}\n");
		MAPPER_XML_TEMPLATE.append("    </if>\n");
		MAPPER_XML_TEMPLATE.append("    <if test=\"params.orderField != null and params.orderField !=''\">\n");
		MAPPER_XML_TEMPLATE.append("    order by  #{params.orderField}\n");
		MAPPER_XML_TEMPLATE.append("    </if>\n");
		MAPPER_XML_TEMPLATE.append("    <if test=\"params.order != null and params.order !=''\">\n");
		MAPPER_XML_TEMPLATE.append("     #{params.order}\n");
		MAPPER_XML_TEMPLATE.append("    </if>\n");
		MAPPER_XML_TEMPLATE.append("</if>\n");
		MAPPER_XML_TEMPLATE.append(" <if test=\" params.strategyCode =='2'.toString()\">\n");

		MAPPER_XML_TEMPLATE.append("  <include refid=\"baseQueryListSql\" />\n");

		MAPPER_XML_TEMPLATE.append("   <if test=\"params.orgCode != null and params.orgCode !=''\"> \n");
		MAPPER_XML_TEMPLATE.append("        and   t.operator_org_code=#{params.orgCode} \n");
		MAPPER_XML_TEMPLATE.append("     </if>\n");
		MAPPER_XML_TEMPLATE.append("     <if test=\"params.tenantId != null and params.tenantId !=''\">\n");
		MAPPER_XML_TEMPLATE.append("        and t.tenant_id  = #{params.tenantId}\n");
		MAPPER_XML_TEMPLATE.append("    </if>\n");
		MAPPER_XML_TEMPLATE
				.append("     <if test=\"params.sharingSelfOrgCodes !=null and params.sharingSelfOrgCodes !=''\">\n");

		MAPPER_XML_TEMPLATE.append("      union  \n");
		MAPPER_XML_TEMPLATE.append("     <include refid=\"baseQueryListSql\" />\n");

		MAPPER_XML_TEMPLATE.append(
				"        <if test=\"params.sharingSelfOrgCodes != null and params.sharingSelfOrgCodes.size() >0 \">\n");
		MAPPER_XML_TEMPLATE.append("            and  t.operator_self_org_code in\n");
		MAPPER_XML_TEMPLATE.append(
				"    	 <foreach item=\"soc\" collection=\"params.sharingSelfOrgCodes\" open=\"(\" separator=\",\" close=\")\">\n");
		MAPPER_XML_TEMPLATE.append("           #{soc}\n");
		MAPPER_XML_TEMPLATE.append("        </foreach>\n");
		MAPPER_XML_TEMPLATE.append("        </if>\n");
		MAPPER_XML_TEMPLATE.append("        <if test=\"params.tenantId != null and params.tenantId !=''\">\n");
		MAPPER_XML_TEMPLATE.append("            and t.tenant_id  = #{params.tenantId}\n");
		MAPPER_XML_TEMPLATE.append("        </if>\n");
		MAPPER_XML_TEMPLATE.append("    </if>\n");
		MAPPER_XML_TEMPLATE.append("    <if test=\"params.sharingOrgCodes !=null and params.sharingOrgCodes !=''\">\n");

		MAPPER_XML_TEMPLATE.append("      union  \n");
		MAPPER_XML_TEMPLATE.append("     <include refid=\"baseQueryListSql\" />\n");

		MAPPER_XML_TEMPLATE
				.append("         <if test=\"params.sharingOrgCodes != null and params.sharingOrgCodes.size()>0\">\n");
		MAPPER_XML_TEMPLATE.append("            and   t.operator_org_code in \n");
		MAPPER_XML_TEMPLATE.append(
				"    	 <foreach item=\"oc\" collection=\"params.sharingOrgCodes\" open=\"(\" separator=\",\" close=\")\">\n");
		MAPPER_XML_TEMPLATE.append("            #{oc}\n");
		MAPPER_XML_TEMPLATE.append("        </foreach>\n");
		MAPPER_XML_TEMPLATE.append("        </if>\n");
		MAPPER_XML_TEMPLATE.append("        <if test=\"params.tenantId != null and params.tenantId !=''\">\n");
		MAPPER_XML_TEMPLATE.append("            and t.tenant_id  = #{params.tenantId}\n");
		MAPPER_XML_TEMPLATE.append("        </if>\n");
		MAPPER_XML_TEMPLATE.append("     </if>\n");
		MAPPER_XML_TEMPLATE.append("    <if test=\"params.orderField != null and params.orderField !=''\">\n");
		MAPPER_XML_TEMPLATE.append("    order by  #{params.orderField}\n");
		MAPPER_XML_TEMPLATE.append("    </if>\n");
		MAPPER_XML_TEMPLATE.append("    <if test=\"params.order != null and params.order !=''\">\n");
		MAPPER_XML_TEMPLATE.append("      #{params.order}\n");
		MAPPER_XML_TEMPLATE.append("    </if>\n");
		MAPPER_XML_TEMPLATE.append("</if>\n");
		MAPPER_XML_TEMPLATE.append("<if test=\" params.strategyCode =='3'.toString()\">\n");

		MAPPER_XML_TEMPLATE.append("  <include refid=\"baseQueryListSql\" />\n");

		MAPPER_XML_TEMPLATE.append("     <if test=\"params.orgCode != null and params.orgCode !=''\">\n");
		MAPPER_XML_TEMPLATE.append("        and  t.operator_org_code like concat(#{params.orgCode}, '%') \n");
		MAPPER_XML_TEMPLATE.append("    </if>\n");
		MAPPER_XML_TEMPLATE.append("    <if test=\"params.tenantId != null and params.tenantId !=''\">\n");
		MAPPER_XML_TEMPLATE.append("        and t.tenant_id  = #{params.tenantId}\n");
		MAPPER_XML_TEMPLATE.append("    </if>\n");
		MAPPER_XML_TEMPLATE
				.append("     <if test=\"params.sharingSelfOrgCodes !=null and params.sharingSelfOrgCodes !=''\">\n");

		MAPPER_XML_TEMPLATE.append("      union  \n");
		MAPPER_XML_TEMPLATE.append("      <include refid=\"baseQueryListSql\" />\n");

		MAPPER_XML_TEMPLATE.append(
				"        <if test=\"params.sharingSelfOrgCodes != null and params.sharingSelfOrgCodes.size() >0\"> \n");
		MAPPER_XML_TEMPLATE.append("            and   t.operator_self_org_code in\n");
		MAPPER_XML_TEMPLATE.append(
				"   	 <foreach item=\"soc\" collection=\"params.sharingSelfOrgCodes\" open=\"(\" separator=\",\" close=\")\">\n");
		MAPPER_XML_TEMPLATE.append("           #{soc}\n");
		MAPPER_XML_TEMPLATE.append("        </foreach>\n");
		MAPPER_XML_TEMPLATE.append("        </if>\n");
		MAPPER_XML_TEMPLATE.append("        <if test=\"params.tenantId != null and params.tenantId !=''\">\n");
		MAPPER_XML_TEMPLATE.append("            and t.tenant_id  = #{params.tenantId}\n");
		MAPPER_XML_TEMPLATE.append("        </if>\n");
		MAPPER_XML_TEMPLATE.append("    </if>\n");
		MAPPER_XML_TEMPLATE
				.append("     <if test=\"params.sharingOrgCodes !=null and params.sharingOrgCodes !=''\">\n");

		MAPPER_XML_TEMPLATE.append("      union  \n");
		MAPPER_XML_TEMPLATE.append("      <include refid=\"baseQueryListSql\" />\n");

		MAPPER_XML_TEMPLATE.append(
				"       		<if test=\"params.sharingOrgCodes != null and params.sharingOrgCodes.size()>0\">\n");
		MAPPER_XML_TEMPLATE.append("            and t.operator_org_code in \n");
		MAPPER_XML_TEMPLATE.append(
				"    	 <foreach item=\"oc\" collection=\"params.sharingOrgCodes\" open=\"(\" separator=\",\" close=\")\">\n");
		MAPPER_XML_TEMPLATE.append("            #{oc}\n");
		MAPPER_XML_TEMPLATE.append("        </foreach>\n");
		MAPPER_XML_TEMPLATE.append("        </if>\n");
		MAPPER_XML_TEMPLATE.append("        <if test=\"params.tenantId != null and params.tenantId !=''\">\n");
		MAPPER_XML_TEMPLATE.append("           and t.tenant_id  = #{params.tenantId}\n");
		MAPPER_XML_TEMPLATE.append("        </if>\n");
		MAPPER_XML_TEMPLATE.append("    </if>\n");
		MAPPER_XML_TEMPLATE.append("    <if test=\"params.orderField != null and params.orderField !=''\">\n");
		MAPPER_XML_TEMPLATE.append("    order by  #{params.orderField}\n");
		MAPPER_XML_TEMPLATE.append("    </if>\n");
		MAPPER_XML_TEMPLATE.append("    <if test=\"params.order != null and params.order !=''\">\n");
		MAPPER_XML_TEMPLATE.append("     #{params.order}\n");
		MAPPER_XML_TEMPLATE.append("   </if>\n");
		MAPPER_XML_TEMPLATE.append(" </if>\n");
		MAPPER_XML_TEMPLATE.append(" <if test=\"params.pageStart != null and params.pageSize != null  \">\n");
		MAPPER_XML_TEMPLATE.append("     limit #{params.pageStart}, #{params.pageSize}\n");
		MAPPER_XML_TEMPLATE.append("  </if>\n");
		MAPPER_XML_TEMPLATE.append("</select>\n");

		MAPPER_XML_TEMPLATE.append("</mapper>\n");
	}

	public static String get_mapper(DbBean model) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("tablename", GenerateJava.captureName(model.getTablename()));
		data.put("package", model.getCompages());
		data.put("dbtablename", model.getTablename().toLowerCase());
		return StringTemplateUtils.render(MAPPER_TEMPLATE.toString(), data);
	}

	public static String get_mapperXml(DbBean model) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("tablename", GenerateJava.captureName(model.getTablename()));
		data.put("package", model.getCompages());
		data.put("dbtablename", model.getTablename().toLowerCase());
		String le = "", ile = "", ule = "", tle = "", upe = "";
		String[] obj = model.getZdname().split(",");
		for (String value : obj) {
			String[] svalue = value.split("--");
			tle += "t." + svalue[0].toLowerCase() + ",";
			le += svalue[0].toLowerCase() + ",";
			ile += "#{" + GenerateJava.firstLetterLowerCase(GenerateJava.captureName(svalue[0].toLowerCase())) + "},";
			if (!"id".equals(svalue[0].toLowerCase())) {
				ule += svalue[0].toLowerCase() + "=#{"
						+ GenerateJava.firstLetterLowerCase(GenerateJava.captureName(svalue[0].toLowerCase())) + "},";
				upe += "\r\n<if test=\"params."
						+ GenerateJava.firstLetterLowerCase(GenerateJava.captureName(svalue[0].toLowerCase()))
						+ " != null \">\r\n" + "        	," + svalue[0].toLowerCase() + "=#{params."
						+ GenerateJava.firstLetterLowerCase(GenerateJava.captureName(svalue[0].toLowerCase())) + "}\r\n"
						+ "        </if>";
			}
		}
		le = le.substring(0, le.length() - 1);
		tle = tle.substring(0, tle.length() - 1);
		ile = ile.substring(0, ile.length() - 1);
		ule = ule.substring(0, ule.length() - 1);
		data.put("tle", tle);
		data.put("le", le);
		data.put("ile", ile);
		data.put("ule", ule);
		data.put("upe", upe);

		System.out.println(upe);
		return StringTemplateUtils.render(MAPPER_XML_TEMPLATE.toString(), data);
	}
}
