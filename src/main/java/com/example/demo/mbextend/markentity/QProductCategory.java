package com.example.demo.mbextend.markentity;

import com.example.demo.mbextend.AliasWorker;
import com.example.demo.mbextend.SqlExpr;
import com.example.demo.mbextend.SqlTaBle;
import com.example.demo.mbextend.QColumn;
import java.util.Arrays;
import java.util.List;

public class QProductCategory extends SqlTaBle {
	private static final String tableName = "`product_category`";
	private final String tableAlias;

    public final QColumn id;
	public final QColumn name;
	public final QColumn desc;
	public final QColumn parentId;

    public QProductCategory() {
        this(null);
    }

	public QProductCategory(String columnPrefix) {
	    tableAlias = AliasWorker.getAlias();
        this.id = new QColumn(tableAlias,"id",columnPrefix==null?null:columnPrefix+"id");
		this.name = new QColumn(tableAlias,"name",columnPrefix==null?null:columnPrefix+"name");
		this.desc = new QColumn(tableAlias,"desc",columnPrefix==null?null:columnPrefix+"desc");
		this.parentId = new QColumn(tableAlias,"parent_id",columnPrefix==null?null:columnPrefix+"parent_id");
    }

	protected String getTableName() {
		return tableName;
	}

	protected String getTableAlias() {
		return tableAlias;
	}

    protected List<SqlExpr> getQColumns() {
        return Arrays.asList(id,name,desc,parentId);
    }
}
