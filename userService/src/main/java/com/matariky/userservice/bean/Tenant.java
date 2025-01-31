package com.matariky.userservice.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName("user_tenant")
@Data
public class Tenant {

      /**
       * This field was generated by MyBatis Generator.
       * This field corresponds to the database column user_tenant.id
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */
      @TableId("id")
      private Long id;

      /**
       * This field was generated by MyBatis Generator.
       * This field corresponds to the database column user_tenant.tenant_code
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */
      private String tenantCode;

      /**
       * This field was generated by MyBatis Generator.
       * This field corresponds to the database column user_tenant.tenant_name
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */
      private String tenantName;

      /**
       * This field was generated by MyBatis Generator.
       * This field corresponds to the database column user_tenant.update_time
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */
      private Long updateTime;

      /**
       * This field was generated by MyBatis Generator.
       * This field corresponds to the database column user_tenant.delete_time
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */
      private Long deleteTime;

      /**
       * This field was generated by MyBatis Generator.
       * This field corresponds to the database column user_tenant.create_time
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */
      private Long createTime;

      /**
       * This field was generated by MyBatis Generator.
       * This field corresponds to the database column user_tenant.created_by
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */
      private Long createdBy;

      /**
       * This field was generated by MyBatis Generator.
       * This field corresponds to the database column user_tenant.updated_by
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */
      private Long updatedBy;

      /**
       * This field was generated by MyBatis Generator.
       * This field corresponds to the database column user_tenant.address
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */
      private String address;

      /**
       * This field was generated by MyBatis Generator.
       * This field corresponds to the database column user_tenant.liaison
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */
      private String liaison;

      /**
       * This field was generated by MyBatis Generator.
       * This field corresponds to the database column user_tenant.liaison_mobile
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */
      private String liaisonMobile;

      /**
       * This field was generated by MyBatis Generator.
       * This field corresponds to the database column user_tenant.is_active
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */
      private Boolean isActive;

      /**
       * This field was generated by MyBatis Generator.
       * This field corresponds to the database column user_tenant.domain_name
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */
      private String domainName;

      /**
       * This method was generated by MyBatis Generator.
       * This method returns the value of the database column user_tenant.id
       *
       * @return the value of user_tenant.id
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method sets the value of the database column user_tenant.id
       *
       * @param id the value for user_tenant.id
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method returns the value of the database column user_tenant.tenant_code
       *
       * @return the value of user_tenant.tenant_code
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method sets the value of the database column user_tenant.tenant_code
       *
       * @param tenantCode the value for user_tenant.tenant_code
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method returns the value of the database column user_tenant.tenant_name
       *
       * @return the value of user_tenant.tenant_name
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method sets the value of the database column user_tenant.tenant_name
       *
       * @param tenantName the value for user_tenant.tenant_name
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method returns the value of the database column user_tenant.update_time
       *
       * @return the value of user_tenant.update_time
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method sets the value of the database column user_tenant.update_time
       *
       * @param updateTime the value for user_tenant.update_time
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method returns the value of the database column user_tenant.delete_time
       *
       * @return the value of user_tenant.delete_time
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method sets the value of the database column user_tenant.delete_time
       *
       * @param deleteTime the value for user_tenant.delete_time
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method returns the value of the database column user_tenant.create_time
       *
       * @return the value of user_tenant.create_time
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method sets the value of the database column user_tenant.create_time
       *
       * @param createTime the value for user_tenant.create_time
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method returns the value of the database column user_tenant.created_by
       *
       * @return the value of user_tenant.created_by
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method sets the value of the database column user_tenant.created_by
       *
       * @param createdBy the value for user_tenant.created_by
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method returns the value of the database column user_tenant.updated_by
       *
       * @return the value of user_tenant.updated_by
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method sets the value of the database column user_tenant.updated_by
       *
       * @param updatedBy the value for user_tenant.updated_by
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method returns the value of the database column user_tenant.address
       *
       * @return the value of user_tenant.address
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method sets the value of the database column user_tenant.address
       *
       * @param address the value for user_tenant.address
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method returns the value of the database column user_tenant.liaison
       *
       * @return the value of user_tenant.liaison
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method sets the value of the database column user_tenant.liaison
       *
       * @param liaison the value for user_tenant.liaison
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method returns the value of the database column
       * user_tenant.liaison_mobile
       *
       * @return the value of user_tenant.liaison_mobile
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method sets the value of the database column user_tenant.liaison_mobile
       *
       * @param liaisonMobile the value for user_tenant.liaison_mobile
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method returns the value of the database column user_tenant.is_active
       *
       * @return the value of user_tenant.is_active
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method sets the value of the database column user_tenant.is_active
       *
       * @param isActive the value for user_tenant.is_active
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method returns the value of the database column user_tenant.domain_name
       *
       * @return the value of user_tenant.domain_name
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

      /**
       * This method was generated by MyBatis Generator.
       * This method sets the value of the database column user_tenant.domain_name
       *
       * @param domainName the value for user_tenant.domain_name
       *
       * @mbggenerated Fri Nov 27 16:22:43 CST 2020
       */

}