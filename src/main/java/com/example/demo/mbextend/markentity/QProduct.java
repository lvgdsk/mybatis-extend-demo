package com.example.demo.mbextend.markentity;

import com.example.demo.mbextend.AliasWorker;
import com.example.demo.mbextend.SqlExpr;
import com.example.demo.mbextend.SqlTaBle;
import com.example.demo.mbextend.QColumn;
import java.util.Arrays;
import java.util.List;

public class QProduct extends SqlTaBle {
	private static final String tableName = "`product`";
	private final String tableAlias;

    public final QColumn id;
	public final QColumn name;
	public final QColumn price;
	public final QColumn stock;
	public final QColumn categoryId;
	public final QColumn status;

    public QProduct() {
        this(null);
    }

	public QProduct(String columnPrefix) {
	    tableAlias = AliasWorker.getAlias();
        this.id = new QColumn(tableAlias,"id",columnPrefix==null?null:columnPrefix+"id");
		this.name = new QColumn(tableAlias,"name",columnPrefix==null?null:columnPrefix+"name");
		this.price = new QColumn(tableAlias,"price",columnPrefix==null?null:columnPrefix+"price");
		this.stock = new QColumn(tableAlias,"stock",columnPrefix==null?null:columnPrefix+"stock");
		this.categoryId = new QColumn(tableAlias,"category_id",columnPrefix==null?null:columnPrefix+"category_id");
		this.status = new QColumn(tableAlias,"status",columnPrefix==null?null:columnPrefix+"status");
    }

	protected String getTableName() {
		return tableName;
	}

	protected String getTableAlias() {
		return tableAlias;
	}

    protected List<SqlExpr> getQColumns() {
        return Arrays.asList(id,name,price,stock,categoryId,status);
    }
}
