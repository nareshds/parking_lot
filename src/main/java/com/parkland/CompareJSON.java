
import com.google.gson.*;

import java.io.*;

public class CompareJSON {
    static String[] fields = {"_apiKey", "_mergeAddress", "_numOfDigits", "_sumOfDigits", "_numOfVowels", "_numOfSymbols"
            , "_numOfWords", "_numOfCommas", "_sequenceOfNum", "_totalOrderedItem", "_rto", "_delivered", "_return"
            , "_cancel", "_chargeback", "_hasRoadFields", "_hasSquareFields", "_hasHouseFields", "_haslandmarkFields"
            , "_hasHostelFields", "_hasBuildingFields", "_hasPointOfInterestFields", "_hasCollegeField"
            , "_hasHindiWords", "_numOfPrepaid", "_numOfCod", "_numOfOrderSameAddress"};
    public static void main(String[] args) throws IOException {
        BufferedReader leftFile = new BufferedReader(new FileReader(new File("/Users/dharmasothnareshnaik/Desktop/legacy_address_features.json")));
        BufferedReader rightFile = new BufferedReader(new FileReader(new File("/Users/dharmasothnareshnaik/Desktop/addressModelIntegration.json")));

        JsonObject leftObject = new JsonParser().parse(leftFile.readLine()).getAsJsonObject();
        JsonObject rightObject = new JsonParser().parse(rightFile.readLine()).getAsJsonObject();

        JsonArray innerLeftObject = leftObject.getAsJsonObject("hits").get("hits").getAsJsonArray();
        JsonArray innerRightObject = rightObject.getAsJsonObject("hits").get("hits").getAsJsonArray();

        JsonObject nonMatchesObj = new JsonObject();
        JsonArray nonMatches = new JsonArray();
        for(int i = 0; i < innerLeftObject.size(); i++) {
            JsonObject obj = innerLeftObject.get(i).getAsJsonObject();
            //System.out.println(obj.get("_id"));
            JsonObject objFound = search(innerRightObject, "_id", obj.get("_id"));
            if(objFound != null){
                JsonObject diff = searchNestedObj(objFound, "_source", (JsonObject) obj.get("_source"));
                if(diff != null){
                    nonMatches.add(diff);
                }
            } else {
                System.out.println("Object Not found");
            }
        }
        System.out.println(nonMatches);
    }

    private static JsonObject searchNestedObj(JsonObject objFound, String source, JsonObject source1) {
        JsonObject finalObj = null;
        JsonObject diffObj;
        JsonArray diffArray = null;
        JsonObject srcObj = (JsonObject) objFound.get(source);
        int j = 0;
        for(int i = 0; i < fields.length; i++){
            if(!srcObj.get(fields[i]).equals(source1.get(fields[i]))){
                diffObj = new JsonObject();
                diffObj.add(fields[i]+"_legacy", source1.get(fields[i]));
                diffObj.add(fields[i]+"_new", srcObj.get(fields[i]));
                if(diffArray == null) diffArray = new JsonArray();
                diffArray.add(diffObj);
                diffObj = null;
            }
            if(i == fields.length-1){
                if(diffArray != null){
                    finalObj = new JsonObject();
                    finalObj.add(source1.get("_mergeAddress").getAsString(), diffArray);
                    j++;
                }
            }
        }
        return finalObj;

    }


    private static JsonObject search(JsonArray innerRightObject, String id, JsonElement id1) {
        for(int i = 0; i < innerRightObject.size(); i++){
            if(innerRightObject.get(i).getAsJsonObject().get(id).equals(id1)){
                return innerRightObject.get(i).getAsJsonObject();
            }
        }
        return null;
    }
}
