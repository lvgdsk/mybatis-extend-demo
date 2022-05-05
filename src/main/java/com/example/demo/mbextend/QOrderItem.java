package com.example.demo.mbextend;

import com.example.demo.mbextend.sqlparts.SqlTaBle;
import java.util.Arrays;
import java.util.List;

public class QOrderItem implements SqlTaBle {
	private static final String tableName = "order_item";
	private String tableAlias;

    public QField id;
	public QField productId;
	public QField number;
	public QField price;
	public QField orderId;

    public QOrderItem() {
        this(null);
    }

	public QOrderItem(String columnPrefix) {
        this.id = QField.column("id",columnPrefix==null?null:columnPrefix+"id");
		this.productId = QField.column("product_id",columnPrefix==null?null:columnPrefix+"product_id");
		this.number = QField.column("number",columnPrefix==null?null:columnPrefix+"number");
		this.price = QField.column("price",columnPrefix==null?null:columnPrefix+"price");
		this.orderId = QField.column("order_id",columnPrefix==null?null:columnPrefix+"order_id");
    }

	@Override
	public String getTableName() {
		return QOrderItem.tableName;
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
        return Arrays.asList(id,productId,number,price,orderId);
    }
}
