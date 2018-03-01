package almoxarifadovirtual.servidor.controle;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Utils {

  static <T> ResponseEntity<T> generateResponse(T body){
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Access-Control-Allow-Origin", "https://xavierlr.github.io");

    return new ResponseEntity<T>(body, responseHeaders, HttpStatus.OK);
  }

}
