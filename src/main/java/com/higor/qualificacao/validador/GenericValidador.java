package com.higor.qualificacao.validador;

import com.higor.qualificacao.exception.CustomRuntimeException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericValidador {

    private Map<String, Object> fieldValues;

    private List<String> invalidFields = new ArrayList<>();

    public void add(Object value, String fieldDescription) {
        if (fieldValues == null) {
            fieldValues = new HashMap<>();
        }

        fieldValues.put(fieldDescription, value);
    }

    public void validate() {
        for (Map.Entry<String, Object> entry : fieldValues.entrySet()) {
            final Object obj = entry.getValue();

            if (obj == null
                    || (obj instanceof String && ((String) obj).isEmpty())) {
                invalidFields.add(entry.getKey());
            }
        }

        if (invalidFields != null
                && !invalidFields.isEmpty()) {
            throw new CustomRuntimeException("Invalid Fields: " + StringUtils.join(invalidFields, ","));
        }
    }

}
