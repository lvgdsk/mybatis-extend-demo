package com.example.demo.mbextend;

import com.example.demo.mbextend.sqlparts.SqlTaBle;
import java.util.Arrays;
import java.util.List;

public class QProduct implements SqlTaBle {
	private static final String tableName = "product";
	private String tableAlias;

    public QField id;
	public QField name;
	public QField price;
	public QField stock;
	public QField categoryId;

    public QProduct() {
        this(null);
    }

	public QProduct(String columnPrefix) {
        this.id = QField.column("id",columnPrefix==null?null:columnPrefix+"id");
		this.name = QField.column("name",columnPrefix==null?null:columnPrefix+"name");
		this.price = QField.column("price",columnPrefix==null?null:columnPrefix+"price");
		this.stock = QField.column("stock",columnPrefix==null?null:columnPrefix+"stock");
		this.categoryId = QField.column("category_id",columnPrefix==null?null:columnPrefix+"category_id");
    }

	@Override
	public String getTableName() {
		return QProduct.tableName;
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
        return Arrays.asList(id,name,price,stock,categoryId);
    }
}
