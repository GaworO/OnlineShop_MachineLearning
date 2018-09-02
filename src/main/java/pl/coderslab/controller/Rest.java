package pl.coderslab.controller;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

        import java.util.*;
        import java.io.*;
        import org.apache.http.HttpResponse;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.entity.StringEntity;
        import org.apache.http.impl.client.HttpClientBuilder;
        import org.apache.http.protocol.HTTP;
        import org.apache.http.util.*;


/**
 *
 * @author neerajkh
 */
public class Rest {
  // public static JSONObject inpParms;
  public static final String apikey = "4ytJZPtM5wbH/iwTnr0eKP+w2BePM6gQRn9O3j/WG6PfJhwRY16Xk1hqDwCwKDTducmednoXoycOZm48egZnCA==";
  public static String jsonBody;
  public static final String URL = "https://ussouthcentral.services.azureml.net/workspaces/a6971f6fdc3e4a5cad2a84c4459f5a1e/services/5f7f26de80024d6fa13d5191a07a7fe6/execute?api-version=2.0&details=true";


  /**
   * Read the JSON schema from the file rrsJson.json
   *
   * @param filename It expects a fully qualified file name that contains input JSON file
   */
  public static void readJson(InputStream inputStream) {
    try {
      Scanner sc = new Scanner(inputStream);
      jsonBody = "";
      while (sc.hasNext()) {
        jsonBody += sc.nextLine()+"\n";
      }
    }
    catch (Exception e){
      System.out.println(e.toString());
    }
  }

  /**
   * Read the API key and API URL of Azure ML request response REST API
   *
   * @param filename fully qualified file name that contains API key and API URL
   */
  public static void readApiInfo(InputStream inputStream) {

    try {
       Scanner sc = new Scanner(inputStream);

       sc.nextLine();
//      apikey = sc.nextLine();

    }
    catch (Exception e){
      System.out.println(e.toString());
    }

  }

  /**
   * Call REST API for retrieving prediction from Azure ML
   * @return response from the REST API
   */
  public  String rrsHttpPost(String jsonBody) {

    HttpPost post;
    HttpClient client;
    StringEntity entity;

    try {
      // create HttpPost and HttpClient object
      post = new HttpPost(URL);
      client = HttpClientBuilder.create().build();

      // setup output message by copying JSON body into
      // apache StringEntity object along with content type
      entity = new StringEntity(jsonBody, HTTP.UTF_8);
      entity.setContentEncoding(HTTP.UTF_8);
      entity.setContentType("text/json");

      // add HTTP headers
      post.setHeader("Accept", "text/json");
      post.setHeader("Accept-Charset", "UTF-8");

      // set Authorization header based on the API key
      post.setHeader("Authorization", ("Bearer "+apikey));
      post.setEntity(entity);

      // Call REST API and retrieve response content
      HttpResponse authResponse = client.execute(post);

      return EntityUtils.toString(authResponse.getEntity());

    }
    catch (Exception e) {

      return e.toString();
    }

  }
}