import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anaRose on 8/27/16.
 */
public class SubCaty {

    public static JSONArray subcategories;

    public static JSONArray getSubcategories() {
        return subcategories;
    }

    public static void setSubcategories(JSONArray subcategories) {
        SubCaty.subcategories = subcategories;
    }

    public static int getsubcategorylength() {
        return subcategories.length();
    }


}