package com.jumpserver.sdk.v2.model.entity;


import java.util.Collections;
import java.util.List;


public abstract class ListResult<T> implements ModelEntity, ListType {

	private static final long serialVersionUID = 1L;

	protected abstract List<T> value();

	public List<T> getList() {
		if (value() == null)
			return Collections.emptyList();
		return value();
	}


    public T first() {
    	return value().isEmpty() ? null : value().get(0);
    }
}
