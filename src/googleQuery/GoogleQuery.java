/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleQuery;

/**
 *
 * @author 陳仲詠
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.json.JSONObject;
public class GoogleQuery 
{
    // Put your website here
    private final String HTTP_REFERER = "http://www.example.com/";
    public static void googleQuery(String query) 
    {
        //以下這段程式碼是標準使用 google api with ajax in java來讀取 query回傳的結果, 一次只能跑 64個 query
        //System.out.println("\nQuerying for " + query);
        try
        {
            // Convert spaces to +, etc. to make a valid URL
            query = URLEncoder.encode(query, "UTF-8");
            URL url = new URL("http://ajax.googleapis.com/ajax/services/search/web?start=0&rsz=large&v=1.0&q=" + query);
            URLConnection connection = url.openConnection();
            connection.addRequestProperty("Referer", HTTP_REFERER);
            // Get the JSON response
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while((line = reader.readLine()) != null) 
            {
                builder.append(line);
            }
            String response = builder.toString();
            //顯示 Google ajax search api 回傳的結果
            //System.out.println(response);
            //使用 JSON 來解析結果
            JSONObject json = new JSONObject(response);
            //Google搜尋頁面數
            double result=Double.valueOf(json.getJSONObject("responseData").getJSONObject("cursor").getString("estimatedResultCount"));
            //System.out.println(result);
            return result;
        }
        catch (Exception e) 
        {
            System.err.println("Something went wrong...");
            e.printStackTrace();
        }
        //======================================================================
    }
}
