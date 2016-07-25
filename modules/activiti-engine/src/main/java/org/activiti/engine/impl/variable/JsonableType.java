package org.activiti.engine.impl.variable;

import java.util.Map;

public class JsonableType implements VariableType {

	@Override
	public String getTypeName() {
		return "jsonb";
	}

	@Override
	public boolean isCachable() {
		return true;
	}

	@Override
	public boolean isAbleToStore(Object value) {
		if (value == null) {
			return true;
		}
		if (Map.class.isAssignableFrom(value.getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void setValue(Object value, ValueFields valueFields) {
		valueFields.setJsonable(value);
	}

	@Override
	public Object getValue(ValueFields valueFields) {
		return valueFields.getJsonable();
	}

}
