package com.ershiyi.plugin.excel.processor;

import java.lang.reflect.Method;

public interface NameProcessor {
    String doDetermineName(Object[] args, Method method, String key);
}
