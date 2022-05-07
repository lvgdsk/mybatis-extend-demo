package com.example.demo.mbextend.markentity;

import com.example.demo.mbextend.AliasWorker;
import com.example.demo.mbextend.SqlExpr;
import com.example.demo.mbextend.SqlTaBle;
import com.example.demo.mbextend.QColumn;
import java.util.Arrays;
import java.util.List;

public class QOrderItem extends SqlTaBle {
	private static final String tableName = "`order_item`";
	private final String tableAlias;

    public final QColumn id;
	public final QColumn productId;
	public final QColumn number;
	public final QColumn price;
	public final QColumn orderId;

    public QOrderItem() {
        this(null);
    }

	public QOrderItem(String columnPrefix) {
	    tableAlias = AliasWorker.getAlias();
        this.id = new QColumn(tableAlias,"id",columnPrefix==null?null:columnPrefix+"id");
		this.productId = new QColumn(tableAlias,"product_id",columnPrefix==null?null:columnPrefix+"product_id");
		this.number = new QColumn(tableAlias,"number",columnPrefix==null?null:columnPrefix+"number");
		this.price = new QColumn(tableAlias,"price",columnPrefix==null?null:columnPrefix+"price");
		this.orderId = new QColumn(tableAlias,"order_id",columnPrefix==null?null:columnPrefix+"order_id");
    }

	protected String getTableName() {
		return tableName;
	}

	protected String getTableAlias() {
		return tableAlias;
	}

    protected List<SqlExpr> getQColumns() {
        return Arrays.asList(id,productId,number,price,orderId);
    }
}
