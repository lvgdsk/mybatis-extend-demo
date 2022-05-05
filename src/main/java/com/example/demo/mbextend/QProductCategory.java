package com.example.demo.mbextend;

import com.example.demo.mbextend.sqlparts.SqlTaBle;
import java.util.Arrays;
import java.util.List;

public class QProductCategory implements SqlTaBle {
	private static final String tableName = "product_category";
	private String tableAlias;

    public QField id;
	public QField name;
	public QField desc;
	public QField parentId;

    public QProductCategory() {
        this(null);
    }

	public QProductCategory(String columnPrefix) {
        this.id = QField.column("id",columnPrefix==null?null:columnPrefix+"id");
		this.name = QField.column("name",columnPrefix==null?null:columnPrefix+"name");
		this.desc = QField.column("desc",columnPrefix==null?null:columnPrefix+"desc");
		this.parentId = QField.column("parent_id",columnPrefix==null?null:columnPrefix+"parent_id");
    }

	@Override
	public String getTableName() {
		return QProductCategory.tableName;
	}

    @Override
    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

	@Override
	public String getTableAlias() {
		return this.tableAlias;
	}

    @Override
    public List<QField> getQueryColumns() {
        return Arrays.asList(id,name,desc,parentId);
    }
}
