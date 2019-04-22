import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    @SuppressWarnings("unchecked")
    public void readFile(){
        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader("src/main/resources/T_and_J.json")){

            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);

            //Iterate over employee array
            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
        }catch (IOException e){
            System.out.println();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseEmployeeObject(JSONObject TomAndJerry)
    {
        System.out.println("ok");
        JSONObject tAndJObject = (JSONObject) TomAndJerry.get("TomAndJerry");

        String day = (String) tAndJObject.get("day");
        System.out.println(day);

        String v_id = (String) tAndJObject.get("v_id");
        System.out.println(v_id);

        String title = (String) tAndJObject.get("title");
        System.out.println(title);

        String view = (String) tAndJObject.get("view");
        System.out.println(view);
    }
}
