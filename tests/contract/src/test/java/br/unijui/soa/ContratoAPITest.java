package br.unijui.soa;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ContratoAPITest {

  static String CATALOGO = System.getenv().getOrDefault("CATALOGO_URL","http://localhost:8081");
  static String PEDIDOS = System.getenv().getOrDefault("PEDIDOS_URL","http://localhost:8082");
  static String NOTIFS  = System.getenv().getOrDefault("NOTIFS_URL","http://localhost:8083");

  @BeforeAll
  static void info() {
    System.out.println("Catalogo: " + CATALOGO);
    System.out.println("Pedidos : " + PEDIDOS);
    System.out.println("Notifs  : " + NOTIFS);
  }

  @Test
  void contrato_catalogo_listar_produtos() {
    given().when().get(CATALOGO + "/api/produtos")
      .then().statusCode(200)
      .and().contentType(ContentType.JSON);
  }

  @Test
  void contrato_catalogo_obter_produto_por_id() {
    given().when().get(CATALOGO + "/api/produtos/P001")
      .then().statusCode(200)
      .and().contentType(ContentType.JSON);
  }

  @Test
  void contrato_pedidos_criar_e_notificar() {
    var body = "{ \"produtoId\": \"P001\", \"quantidade\": 2 }";
    given().contentType(ContentType.JSON)
      .body(body)
      .when().post(PEDIDOS + "/api/pedidos")
      .then().statusCode(200)
      .and().contentType(ContentType.JSON);

    given().when().get(NOTIFS + "/api/notificacoes")
      .then().statusCode(200)
      .and().contentType(ContentType.JSON);
  }
}
