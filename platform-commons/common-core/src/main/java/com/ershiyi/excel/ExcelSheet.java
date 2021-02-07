package com.ershiyi.excel;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.excel.annotation.ExcelIgnore;
import com.ershiyi.excel.annotation.ExcelProperty;
import com.ershiyi.excel.annotation.ExcelTitle;
import com.ershiyi.utils.WebUtils;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Data
public class ExcelSheet<T> {

    private ExcelReader reader;

    private ExcelWriter writer;

    private Table table;

    private List<T> rows;

    public ExcelSheet(Class<T> beanClass, List<T> rows){
        this.writer = ExcelUtil.getWriter();
        this.rows = rows;
        this.table = new Table(beanClass);
    }

    public void write(OutputStream outputStream){
        try {
            writer.write(rows, true);
            writer.flush(outputStream,true);
        }catch (Exception e){
            RespEnum.EXCEL_OUT_ERROR.throwException(e);
        }
    }

    public void writeResponse(String fileName){
        try {
            HttpServletResponse httpServletResponse = WebUtils.getHttpServletResponse();
            String contentType = table.getTitle().isXlsx ?
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8" : "application/vnd.ms-excel;charset=utf-8";
            String content = StrUtil.format("attachment;filename={}.{}", fileName, table.getTitle().isXlsx ? "xlsx" : "xls");
            httpServletResponse.setContentType(contentType);
            httpServletResponse.setHeader("Content-Disposition", content);
            writer.write(rows, true);
            writer.flush(httpServletResponse.getOutputStream(),true);
        }catch (Exception e){
            RespEnum.EXCEL_OUT_ERROR.throwException(e);
        }
    }

    @Data
    public class Table {

        private Title title;

        public Title getTitle() {
            return title;
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public List<Property> getHeaders() {
            return headers;
        }

        public void setHeaders(List<Property> headers) {
            this.headers = headers;
        }

        private List<Property> headers;

        public Table (Class<?> beanClass){
            this.headers = headers(beanClass);
            this.title = title(beanClass, this.headers.size());
            addHeader();
            addTitle();
        }

        private Title title(Class<?> beanClass, int merge){
            ExcelTitle excelTitle = beanClass.getAnnotation(ExcelTitle.class);
            if ( ObjectUtil.isEmpty(excelTitle) ){
                writer = ExcelUtil.getWriter();
                return new Title(beanClass, merge);
            } else {
                writer = ExcelUtil.getWriter(excelTitle.isXlsx());
                return new Title(beanClass, excelTitle, merge);
            }
        }

        private List<Property> headers(Class<?> beanClass){
            List<Property> properties = Lists.newArrayList();
            Arrays.stream(beanClass.getDeclaredFields()).forEach( field -> {
                ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
                if( ObjectUtil.isNotNull(excelProperty) ){
                    ExcelIgnore ignore = field.getAnnotation(ExcelIgnore.class);
                    if( ObjectUtil.isEmpty(ignore) ){
                        properties.add(new Property(field, excelProperty, properties.size()));
                    }
                }
            });
            Collections.sort(properties, new Comparator<Property>() {
                @Override
                public int compare(Property o1, Property o2) {
                    int i = o1.getShowOrder() - o2.getShowOrder();
                    if(i == 0){
                        return o1.getDefaultOrder() - o2.getDefaultOrder();
                    }
                    return i;
                }
            });
            return properties;
        }

        private void addHeader(){
            headers.forEach( property -> {
                writer.addHeaderAlias(property.propertyName, property.aliasName);
            });
        }

        private void addTitle(){

           // writer.merge(title.getMerge(), title.getName());
        }
    }

    @Data
    protected class Title {
        private String name;
        private int merge;
        private boolean isXlsx = false;

        public Title(Class<?> beanClass, int merge){
            this.name = beanClass.getSimpleName();
            this.merge = merge;
        }

        public Title(Class<?> beanClass, ExcelTitle excelTitle, int merge){
            this.name = StringUtils.isEmpty(excelTitle.title()) ? beanClass.getSimpleName() : excelTitle.title();
            this.merge = excelTitle.merge() == 0 ? merge : excelTitle.merge();
            this.isXlsx = excelTitle.isXlsx();
        }
    }

    @Data
    protected class Property {
        private String aliasName;
        private String propertyName;
        private int showOrder;
        private int defaultOrder;

        public String getAliasName() {
            return aliasName;
        }

        public void setAliasName(String aliasName) {
            this.aliasName = aliasName;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public int getShowOrder() {
            return showOrder;
        }

        public void setShowOrder(int showOrder) {
            this.showOrder = showOrder;
        }

        public int getDefaultOrder() {
            return defaultOrder;
        }

        public void setDefaultOrder(int defaultOrder) {
            this.defaultOrder = defaultOrder;
        }

        public Property(Field field, ExcelProperty excelProperty, int defaultOrder){
            this.aliasName = StringUtils.isEmpty(excelProperty.aliasName()) ? field.getName() : excelProperty.aliasName();
            this.propertyName = StringUtils.isEmpty(excelProperty.propertyName()) ? field.getName() : excelProperty.propertyName();
            this.showOrder = excelProperty.showOrder();
            this.defaultOrder = defaultOrder;
        }
    }

}
