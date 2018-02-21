package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwichJSON = new JSONObject(json);

            Sandwich sandwich = new Sandwich();

            sandwich.setMainName(sandwichJSON.getJSONObject("name").getString("mainName"));

            JSONArray alsoKnownArray = sandwichJSON.getJSONObject("name").getJSONArray("alsoKnownAs");

            List<String> alsoKnownList = new ArrayList<>();
            for (int i = 0; i < alsoKnownArray.length(); i++) {
                alsoKnownList.add(alsoKnownArray.getString(i));
            }
            sandwich.setAlsoKnownAs(alsoKnownList);

            sandwich.setPlaceOfOrigin(sandwichJSON.getString("placeOfOrigin"));
            sandwich.setDescription(sandwichJSON.getString("description"));
            sandwich.setImage(sandwichJSON.getString("image"));

            JSONArray ingredientsArray = sandwichJSON.getJSONArray("ingredients");
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
