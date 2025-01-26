package com.matariky.aop;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Method;

@Data
@AllArgsConstructor
public class LockPoint implements Serializable {

	private static final long serialVersionUID = 1L;

    private Object[] args;

    private Method method;
}
