package com.si.filter.services.implementations;

import com.si.filter.services.IFilterService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class FilterServiceImpl implements IFilterService {

    public String filterPermissions(String body) {
        JSONObject jsonBody = new JSONObject(body);
        String[] fields = jsonBody.get("fields").toString().trim().split(",");
        Object data = jsonBody.get("data");

        for (String field : fields)
            removeField(data, field.trim());

        System.out.println("SVE: " + System.lineSeparator() + jsonBody);
        System.out.println("FIELDS: " + System.lineSeparator() + Arrays.toString(fields));
        System.out.println("DATA: " + System.lineSeparator() + data);

        return data.toString();

    }

    private static void removeField(Object object, String field) throws JSONException {
        if (object instanceof JSONArray) {
            JSONArray array = (JSONArray) object;
            for (int i = 0; i < array.length(); ++i) removeField(array.get(i), field);
        } else if (object instanceof JSONObject) {
            JSONObject json = (JSONObject) object;
            JSONArray names = json.names();
            if (names == null) return;
            for (int i = 0; i < names.length(); ++i) {
                String key = names.getString(i);
                if (key.equals(field)) {
                    json.remove(key);
                } else {
                    removeField(json.get(key), field);
                }
            }
        }
    }

}
