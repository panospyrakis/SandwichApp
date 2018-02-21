package com.udacity.sandwichclub.utils;

import android.content.Context;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json, Context context) {
        try {
            JSONObject sandwichJSON = new JSONObject(json);

            Sandwich sandwich = new Sandwich();

            sandwich.setMainName(sandwichJSON.optJSONObject(context.getResources().getString(R.string.json_field_name)).getString(context.getResources().getString(R.string.json_field_main_name)));

            JSONArray alsoKnownArray = sandwichJSON.optJSONObject(context.getResources().getString(R.string.json_field_name)).optJSONArray(context.getResources().getString(R.string.json_field_also_known_as));

            List<String> alsoKnownList = new ArrayList<>();
            for (int i = 0; i < alsoKnownArray.length(); i++) {
                alsoKnownList.add(alsoKnownArray.getString(i));
            }
            sandwich.setAlsoKnownAs(alsoKnownList);

            sandwich.setPlaceOfOrigin(sandwichJSON.optString(context.getResources().getString(R.string.json_field_place_of_origin)));
            sandwich.setDescription(sandwichJSON.optString(context.getResources().getString(R.string.json_field_description)));
            sandwich.setImage(sandwichJSON.optString(context.getResources().getString(R.string.json_field_image)));

            JSONArray ingredientsArray = sandwichJSON.optJSONArray(context.getResources().getString(R.string.json_field_ingredients));
            List<String> ingredientsList = new ArrayList<>();

            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredientsList.add(ingredientsArray.getString(i));
            }
            sandwich.setIngredients(ingredientsList);

            return sandwich;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
